package gullideckel.seasonhunter.SeasonHunterPages.NewCompany;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.CostumLayouts.PlaceAutoComplete.PlaceAutoCompleteTextView;
import gullideckel.seasonhunter.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyContact;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Job.CompanyExtras;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.SeasonHunterPages.NewCompany.CompanyCheck.FragCheckNewCompany;
import gullideckel.seasonhunter.SeasonHunterPages.NewCompany.Contact.NewContact;
import gullideckel.seasonhunter.SeasonHunterPages.NewCompany.Contact.NewEmail;
import gullideckel.seasonhunter.SeasonHunterPages.NewCompany.Contact.NewPhone;
import gullideckel.seasonhunter.SeasonHunterPages.NewCompany.Job.NewJob;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class FragNewCompany extends Fragment implements ICompanyAddress
{
    private static final String TAG = "FragNewCompany";

    private TextView txtTitle;
    private EditText edtName;
    private TextView txtType;
    private Spinner spinType;
    private TextView txtAddress;
    private AutoCompleteTextView edaAddress;
    private TextView txtContact;
    private CheckBox chkPhone;
    private ImageButton imbAddPhone;
    private LinearLayout linPhone;
    private CheckBox chkEmail;
    private ImageButton imbAddEmail;
    private LinearLayout linEmail;
    private CheckBox chkWebsite;
    private LinearLayout linWebsite;
    private EditText edtWebsite;
    private CheckBox chkOnlineRecruitment;
    private LinearLayout linJob;
    private ImageButton imbAddJob;
    private CheckBox chkFacilities;
    private EditText edtFacilities;
    private CheckBox chkCourses;
    private EditText edtCourses;
    private EditText edtDescription;
    private Button btnClear;
    private Button btnDone;

    private CompanyAddress address;

    private NewPhone newPhone;
    private NewEmail newEmail;

    private List<NewJob> jobs;

    protected GoogleApiClient client;

    public static FragNewCompany newInstance(GoogleApiClient client)
    {
        FragNewCompany fragment = new FragNewCompany();
        fragment.client = client;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_new_company, container, false);

        txtTitle = (TextView) v.findViewById(R.id.txtNewName);
        edtName = (EditText) v.findViewById(R.id.edtNewName);
        txtType = (TextView) v.findViewById(R.id.txtNewType);
        spinType = (Spinner) v.findViewById(R.id.spinNewType);
        txtAddress = (TextView) v.findViewById(R.id.txtNewAddress);
        edaAddress = (AutoCompleteTextView) v.findViewById(R.id.edaNewAddress);
        txtContact = (TextView) v.findViewById(R.id.txtNewContact);
        chkPhone = (CheckBox) v.findViewById(R.id.chkNewPhone);
        imbAddPhone = (ImageButton) v.findViewById(R.id.imbNewPlusPhone);
        linPhone = (LinearLayout) v.findViewById(R.id.linNewPhone);
        chkEmail = (CheckBox) v.findViewById(R.id.chkNewEmail);
        imbAddEmail = (ImageButton) v.findViewById(R.id.imbNewPlusEmail);
        linEmail = (LinearLayout) v.findViewById(R.id.linNewEmail);
        chkWebsite = (CheckBox) v.findViewById(R.id.chkNewWebsite);
        linWebsite = (LinearLayout) v.findViewById(R.id.linNewWebsite);
        edtWebsite = (EditText) v.findViewById(R.id.edtNewWebsite);
        chkOnlineRecruitment = (CheckBox) v.findViewById(R.id.chkNewOnlineRecruitment);
        imbAddJob = (ImageButton) v.findViewById(R.id.imbNewJobPlus);
        linJob = (LinearLayout) v.findViewById(R.id.linNewJobs);
        chkFacilities = (CheckBox) v.findViewById(R.id.chkNewFacilities);
        edtFacilities = (EditText) v.findViewById(R.id.edtNewFacilities);
        chkCourses = (CheckBox) v.findViewById(R.id.chkNewCourses);
        edtCourses = (EditText) v.findViewById(R.id.edtNewCourses);
        edtDescription = (EditText) v.findViewById(R.id.edtNewDescription);
        btnDone = (Button) v.findViewById(R.id.btnNewDone);
        btnClear = (Button) v.findViewById(R.id.btnNewClear);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.company_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinType.setAdapter(adapter);

        PlaceAutoCompleteTextView autoComplete = new PlaceAutoCompleteTextView(getContext(), edaAddress, client, this);
        autoComplete.Init();

        newPhone = new NewPhone(getLayoutInflater(), getContext(), linPhone);
        linPhone.addView(newPhone.createPhoneView());
        chkPhone.setOnCheckedChangeListener(phone);
        imbAddPhone.setOnClickListener(AddPhone);

        newEmail = new NewEmail(getLayoutInflater(), linEmail);
        linEmail.addView(newEmail.createEmailView());
        chkEmail.setOnCheckedChangeListener(Email);
        imbAddEmail.setOnClickListener(AddEmail);

        chkWebsite.setOnCheckedChangeListener(Website);

        jobs = new ArrayList<>();
        NewJob job = new NewJob(getLayoutInflater(), getContext(), linJob);
        linJob.addView(job.createJobView());
        jobs.add(job);
        imbAddJob.setOnClickListener(AddJob);

        chkFacilities.setOnCheckedChangeListener(Facilities);
        chkCourses.setOnCheckedChangeListener(Courses);

        btnDone.setOnClickListener(Done);
        btnClear.setOnClickListener(Clear);

        return v;
    }

    private View.OnClickListener Done = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(!isCorrect())
                StaticMethod.Toast(getContext().getString(R.string.new_toast_error), getContext());
            else
            {
                CompanyDocument doc = new CompanyDocument();
                doc.setName(edtName.getText().toString());
                List<String> types = new ArrayList<>();
                types.add(spinType.getSelectedItem().toString());
                doc.setTypes(types);
                doc.setAddress(address);

                CompanyContact contact = new CompanyContact();
                if(chkPhone.isChecked())
                    contact.setPhoneNumber(newPhone.getContacts());
                if(chkEmail.isChecked())
                    contact.setEmail(newEmail.getContacts());
                if(chkWebsite.isChecked())
                {
                    contact.setWebsite(edtWebsite.getText().toString());
                    contact.setOnlineRecruitment(chkOnlineRecruitment.isChecked());
                }
                doc.setContact(contact);

                List<CompanyJobs.CompanyJob> lstJobs = new ArrayList<>();

                for(NewJob job : jobs)
                    lstJobs.add(job.getCompanyJob());

                CompanyJobs cJobs = new CompanyJobs();
                cJobs.setCompanyJobs(lstJobs);
                doc.setJobs(cJobs);

                CompanyExtras extras = new CompanyExtras();

                if(chkFacilities.isChecked() && !edtFacilities.getText().toString().isEmpty())
                    extras.setFacilities(edtFacilities.getText().toString());

                if(chkCourses.isChecked() && !edtCourses.getText().toString().isEmpty())
                    extras.setCourses(edtCourses.getText().toString());

                if(!edtDescription.getText().toString().isEmpty())
                    extras.setDescription(edtDescription.getText().toString());

                doc.setExtras(extras);

                android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.add(R.id.frmCheckDocument, FragCheckNewCompany.newInstance(doc));
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
    };

    private boolean isCorrect()
    {
        boolean is = true;
        if(edtName.getText().toString().isEmpty())
        {
            txtTitle.setTextColor(Color.RED);
            is = false;
        }
        else
            txtTitle.setTextColor(Color.BLACK);

        if(spinType.getSelectedItem().toString().isEmpty())
        {
            txtType.setTextColor(Color.RED);
            is = false;
        }
        else
            txtType.setTextColor(Color.BLACK);

        if(edaAddress.getText().toString().isEmpty() || address == null)
        {
            txtAddress.setTextColor(Color.RED);
            is= false;
        }
        else
            txtAddress.setTextColor(Color.BLACK);


        boolean contact = false;
        if(chkPhone.isChecked() && newPhone.getContacts().size() > 0)
            contact = true;
        if(chkEmail.isChecked() && newEmail.getContacts().size() > 0)
            contact = true;
        if(chkWebsite.isChecked() && !edtWebsite.getText().toString().isEmpty())
            contact = true;

        if(!contact)
        {
            txtContact.setText(getContext().getString(R.string.new_contact) + "\n" +
                    getContext().getString(R.string.new_contact_error));
            txtContact.setTextColor(Color.RED);
            is = false;
        }
        else
        {
            txtContact.setText(getContext().getString(R.string.new_contact));
            txtContact.setTextColor(Color.BLACK);
        }

        for(int i = 0; i<jobs.size(); i++)
        {
            is = jobs.get(i).isCorrect();
        }

        return is;
    }

    private CompoundButton.OnCheckedChangeListener phone = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if(isChecked)
            {
                imbAddPhone.setVisibility(View.VISIBLE);
                linPhone.setVisibility(View.VISIBLE);
            }
            else
            {
                imbAddPhone.setVisibility(View.GONE);
                linPhone.setVisibility(View.GONE);
            }
        }
    };

    private View.OnClickListener AddPhone = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            linPhone.addView(newPhone.createPhoneView());
        }
    };

    private CompoundButton.OnCheckedChangeListener Email = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if(isChecked)
            {
                imbAddEmail.setVisibility(View.VISIBLE);
                linEmail.setVisibility(View.VISIBLE);
            }
            else
            {
                imbAddEmail.setVisibility(View.GONE);
                linEmail.setVisibility(View.GONE);
            }
        }
    };

    private View.OnClickListener AddEmail = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            linEmail.addView(newEmail.createEmailView());
        }
    };

    private CompoundButton.OnCheckedChangeListener Website = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if(isChecked)
                linWebsite.setVisibility(View.VISIBLE);
            else
                linWebsite.setVisibility(View.GONE);
        }
    };

    private View.OnClickListener AddJob = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            NewJob job = new NewJob(getLayoutInflater(), getContext(), linJob);
            linJob.addView(job.createJobView());
            jobs.add(job);
        }
    };

    private CompoundButton.OnCheckedChangeListener Facilities = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if(isChecked)
                edtFacilities.setVisibility(View.VISIBLE);
            else
                edtFacilities.setVisibility(View.GONE);
        }
    };

    private CompoundButton.OnCheckedChangeListener Courses = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if(isChecked)
                edtCourses.setVisibility(View.VISIBLE);
            else
                edtCourses.setVisibility(View.GONE);
        }
    };


    private View.OnClickListener Clear = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(getContext().getString(R.string.new_clear))
                    .setPositiveButton(getContext().getText(R.string.new_yes), dialogClickListener)
                    .setNegativeButton(getContext().getText(R.string.new_no), dialogClickListener).show();

        }
    };

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    edtName.setText("");
                    spinType.setSelection(0);
                    edaAddress.setText("");
                    address = null;

                    chkPhone.setChecked(false);
                    linPhone.removeAllViews();
                    linPhone.addView(newPhone.createPhoneView());

                    chkEmail.setChecked(false);
                    linEmail.removeAllViews();
                    linEmail.addView(newEmail.createEmailView());

                    chkWebsite.setChecked(false);
                    edtWebsite.setText("");
                    chkOnlineRecruitment.setChecked(false);

                    linJob.removeAllViews();
                    jobs.clear();
                    NewJob job = new NewJob(getLayoutInflater(), getContext(), linJob);
                    linJob.addView(job.createJobView());
                    jobs.add(job);

                    chkFacilities.setChecked(false);
                    edtFacilities.setText("");

                    chkCourses.setChecked(false);
                    edtCourses.setText("");

                    edtDescription.setText("");

                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }
    };



    @Override
    public void OnCompanyAddress(CompanyAddress companyAddress)
    {
        address = companyAddress;
    }
}
