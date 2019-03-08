package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Address;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Contact;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Courses;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Description;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Facilities;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Job;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Name;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas.Type;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;

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
        address = new Address(getLayoutInflater(), getChildFragmentManager(), doc.getAddress(), client, getContext());

        return v;
    }

    private AdapterView.OnItemSelectedListener Area = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            if(position == 0)
            {
                frmArea.removeAllViews();
                frmArea.addView(name.getView());
            }
            else if(position == 1)
            {
                frmArea.removeAllViews();
                frmArea.addView(type.getView());
            }
            else if(position == 2)
            {
                frmArea.removeAllViews();
                frmArea.addView(address.getView());
            }
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
            switch (spinArea.getSelectedItemPosition())
            {
                case 0:
                    name.Send();
                    break;
                case 1:
                    type.Send();
            }
        }
    };



}
