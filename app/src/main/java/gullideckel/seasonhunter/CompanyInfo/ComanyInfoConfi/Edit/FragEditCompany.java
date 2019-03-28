package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Address;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Contact.Contact;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Courses;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Description;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Facilities;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Job.Job;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Name;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Type;
import gullideckel.seasonhunter.Objects.Edit.DocEditValue;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class FragEditCompany extends Fragment
{
    private static final String TAG = "FragEditCompany";

    private FrameLayout frmArea;
    private Spinner spinArea;
    private Button btnSend;

    private ArrayAdapter<CharSequence> adapter;

    private Name name;
    private Type type;
    private Address address;
    private Contact contact;
    private Job job;
    private Facilities facilities;
    private Courses courses;
    private Description description;

    protected CompanyDocument doc;
    protected GoogleApiClient client;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    public static FragEditCompany newInstance(CompanyDocument doc, GoogleApiClient client)
    {
        FragEditCompany fragment = new FragEditCompany();
        fragment.doc = doc;
        fragment.client = client;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_edit_company, container, false);

        v.bringToFront();
        v.setBackgroundColor(Color.WHITE);

        frmArea = (FrameLayout) v.findViewById(R.id.frmEditArea);
        spinArea = (Spinner) v.findViewById(R.id.spinEditArea);
        btnSend = (Button) v.findViewById(R.id.btnEditSend);

        adapter = ArrayAdapter.createFromResource(getContext(), R.array.edit_areas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinArea.setAdapter(adapter);

        spinArea.setOnItemSelectedListener(Area);
        btnSend.setOnClickListener(Send);

        name = new Name(getLayoutInflater(), doc.getName());
        type = new Type(getLayoutInflater(), doc.getTypes().get(0), getContext());
        address = new Address(getLayoutInflater(), getChildFragmentManager(), doc.getAddress(), client, getActivity());
        contact = new Contact(getLayoutInflater(), doc.getContact());
        job = new Job(getLayoutInflater(), getContext(), doc.getJobs());
        facilities = new Facilities(getLayoutInflater(), doc.getExtras().getFacilities(), getContext());
        courses = new Courses(getLayoutInflater(), doc.getExtras().getCourses(), getContext());
        description = new Description(getLayoutInflater(), doc.getExtras().getDescription(), getContext());

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        return v;
    }

    private AdapterView.OnItemSelectedListener Area = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            frmArea.removeAllViews();
            if(position == 0)
                frmArea.addView(name.getView());
            else if(position == 1)
                frmArea.addView(type.getView());
            else if(position == 2)
                frmArea.addView(address.getView());
            else if(position == 3)
                frmArea.addView(contact.getView());
            else if(position == 4)
                frmArea.addView(job.getView());
            else if(position == 5)
                frmArea.addView(facilities.getView());
            else if(position == 6)
                frmArea.addView(courses.getView());
            else if(position == 7)
                frmArea.addView(description.getView());

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {

        }
    };

    private View.OnClickListener Send = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            DocEditValue val = new DocEditValue(doc.getId(), auth.getUid());

            switch (spinArea.getSelectedItemPosition())
            {
                case 0:
                    val.setObj(name.GetName());
                    break;
                case 1:
                    val.setObj(type.getType());
                    break;
                case 2:
                    val.setObj(address.getAddress());
                    break;
                case 3:
                    val.setObj(contact.getContact());
                    break;
                case 4:
                    val.setObj(job.getJobs());
                    break;
                case 5:
                    val.setObj(facilities.getFacilities());
                    break;
                case 6:
                    val.setObj(courses.getCourses());
                    break;
                case 7:
                    val.setObj(description.getDescription());
                    break;
            }

            if(val.getObj() != null)
            {
                btnSend.setEnabled(false);
                Send(val);
            }
            else
                StaticMethod.Toast(getContext().getString(R.string.edit_no_changes), getContext());

        }
    };

    private void Send(DocEditValue edit)
    {
        db.collection(getContext().getString(R.string.db_edit)).add(edit).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference)
            {
                StaticMethod.Toast(getContext().getString(R.string.edit_sent), getContext());
                getActivity().onBackPressed();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                StaticMethod.Toast(getContext().getString(R.string.edit_not_sent), getContext());
                getActivity().onBackPressed();
            }
        });
    }
}
