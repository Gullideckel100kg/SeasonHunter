package gullideckel.seasonhunter.SeasonHunterPages.NewCompany.CompanyCheck;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class FragCheckNewCompany extends Fragment
{
    private static final String TAG = "FragCheckNewCompany";

    private TextView txtName;
    private TextView txtType;
    private TextView txtAddress;
    private TextView txtPhoneHead;
    private TextView txtPhone;
    private TextView txtEmailHead;
    private TextView txtEmail;
    private TextView txtWebsiteHead;
    private TextView txtWebsite;
    private LinearLayout linJobs;
    private TextView txtFacilitiesHead;
    private TextView txtFacilities;
    private TextView txtCoursesHead;
    private TextView txtCourses;
    private TextView txtDescriptionHead;
    private TextView txtDescription;
    private Button btnEdit;
    private Button btnSend;

    protected CompanyDocument doc;
    private FirebaseFirestore db;


    public static FragCheckNewCompany newInstance(CompanyDocument doc)
    {
        FragCheckNewCompany fragment = new FragCheckNewCompany();
        fragment.doc = doc;
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_new_company_preview, container, false);

        v.bringToFront();
        v.setBackgroundColor(Color.WHITE);

        txtName = (TextView) v.findViewById(R.id.txtCheckName);
        txtType = (TextView) v.findViewById(R.id.txtCheckType);
        txtAddress = (TextView) v.findViewById(R.id.txtCheckAddress);
        txtPhoneHead = (TextView) v.findViewById(R.id.txtCheckPhoneHead);
        txtPhone = (TextView) v.findViewById(R.id.txtCheckPhone);
        txtEmailHead = (TextView) v.findViewById(R.id.txtCheckEmailHead);
        txtEmail = (TextView) v.findViewById(R.id.txtCheckEmail);
        txtWebsiteHead = (TextView) v.findViewById(R.id.txtCheckWebsiteHead);
        txtWebsite = (TextView) v.findViewById(R.id.txtCheckWebsite);
        linJobs = (LinearLayout) v.findViewById(R.id.linCheckJobs);
        txtFacilitiesHead = (TextView) v.findViewById(R.id.txtCheckFacilitiesHead);
        txtFacilities = (TextView) v.findViewById(R.id.txtCheckFacilities);
        txtCoursesHead = (TextView) v.findViewById(R.id.txtCheckCoursesHead);
        txtCourses = (TextView) v.findViewById(R.id.txtCheckCourses);
        txtDescriptionHead = (TextView) v.findViewById(R.id.txtCheckDescriptionHead);
        txtDescription = (TextView) v.findViewById(R.id.txtCheckDescription);
        btnEdit = (Button) v.findViewById(R.id.btnCheckEdit);
        btnSend = (Button) v.findViewById(R.id.btnCheckSend);

        btnSend.setOnClickListener(Send);
        btnEdit.setOnClickListener(Edit);

        Init();

        db = FirebaseFirestore.getInstance();

        return v;
    }

    private View.OnClickListener Edit = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            getActivity().onBackPressed();
        }
    };

    private View.OnClickListener Send = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            db.collection(getContext().getString(R.string.db_new_company)).add(doc).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference)
                {
                    StaticMethod.Toast(getContext().getString(R.string.new_sent), getContext());
                    getActivity().onBackPressed();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e)
                {
                    StaticMethod.Toast(getContext().getString(R.string.new_not_sent), getContext());
                    Log.e(TAG, "onFailure:   ", e);
                }
            });
        }
    };

    private void Init()
    {
        txtName.setText(doc.getName());
        txtType.setText(doc.getTypes().get(0));
        txtAddress.setText(doc.getAddress().getAddress());


        if(doc.getContact().getPhoneNumber().size() > 0)
        {
            StringBuilder bp = new StringBuilder();
            txtPhoneHead.setVisibility(View.VISIBLE);
            txtPhone.setVisibility(View.VISIBLE);

            bp.append(doc.getContact().getPhoneNumber().get(0));
            if(doc.getContact().getPhoneNumber().size() > 1)
                for(String phone : doc.getContact().getPhoneNumber())
                    bp.append("\n" + phone);

            txtPhone.setText(bp.toString());
        }

        if(doc.getContact().getEmail().size() > 0)
        {
            StringBuilder bm = new StringBuilder();
            txtEmailHead.setVisibility(View.VISIBLE);
            txtEmail.setVisibility(View.VISIBLE);

            bm.append(doc.getContact().getEmail().get(0));
            if(doc.getContact().getEmail().size() > 1)
                for(String email : doc.getContact().getEmail())
                    bm.append("\n" + email);

            txtEmail.setText(bm.toString());
        }

        if(!doc.getContact().getWebsite().isEmpty())
        {
            txtWebsiteHead.setVisibility(View.VISIBLE);
            txtWebsite.setVisibility(View.VISIBLE);
            if(doc.getContact().isOnlineRecruitment())
                txtWebsite.setText(doc.getContact().getWebsite() + " (" + getContext().getString(R.string.check_online_recruitment) + ")");
            else
                txtWebsite.setText(doc.getContact().getWebsite());
        }

        CheckJobs checkJobs = new CheckJobs(getLayoutInflater());

        for(CompanyJobs.CompanyJob job : doc.getJobs().getCompanyJobs())
            linJobs.addView(checkJobs.FillContact(job));

        if(!doc.getExtras().getFacilities().isEmpty())
        {
            txtFacilitiesHead.setVisibility(View.VISIBLE);
            txtFacilities.setVisibility(View.VISIBLE);
            txtFacilities.setText(doc.getExtras().getFacilities());
        }

        if(!doc.getExtras().getCourses().isEmpty())
        {
            txtCoursesHead.setVisibility(View.VISIBLE);
            txtCourses.setVisibility(View.VISIBLE);
            txtCourses.setText(doc.getExtras().getCourses());
        }

        if(!doc.getExtras().getDescription().isEmpty())
        {
            txtDescriptionHead.setVisibility(View.VISIBLE);
            txtDescription.setVisibility(View.VISIBLE);
            txtDescription.setText(doc.getExtras().getDescription());
        }
    }
}
