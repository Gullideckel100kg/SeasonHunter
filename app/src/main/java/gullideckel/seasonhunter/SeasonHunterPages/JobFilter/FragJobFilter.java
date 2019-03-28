package gullideckel.seasonhunter.SeasonHunterPages.JobFilter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.Date;
import java.util.List;

import gullideckel.seasonhunter.Interfaces.IFilteredDocuments;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;


public class FragJobFilter extends Fragment
{
    private static final String TAG = "FragJobFilter";

    private EditText edtTitle;
    private Button btnType;
    private LinearLayout linType;
    private CheckBox chkPhone;
    private CheckBox chkEmail;
    private CheckBox chkWebSite;
    private CheckBox chkFacilities;
    private CheckBox chkWorkMonths;
    private Spinner spinStartDate;

    private Button btnClear;
    private Button btnApply;

    private IFilteredDocuments listener;
    private List<CompanyDocument> docs;
    private FillTypes fillTypes;

    public static FragJobFilter newInstance(IFilteredDocuments listener)
    {
        FragJobFilter fragment = new FragJobFilter();
        fragment.listener = listener;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_job_filter, container, false);

        edtTitle = v.findViewById(R.id.edtFilterTitle);
        btnType = v.findViewById(R.id.btnFilterType);
        linType = v.findViewById(R.id.linFilterType);
        chkPhone = v.findViewById(R.id.chkFilterPhone);
        chkEmail = v.findViewById(R.id.chkFilterEmail);
        chkWebSite = v.findViewById(R.id.chkFilterWebsite);
        chkFacilities = v.findViewById(R.id.chkFilterFacilities);
        chkWorkMonths = v.findViewById(R.id.chkFilterMonth);
        spinStartDate = v.findViewById(R.id.spinFilterStartMonth);
        btnClear = v.findViewById(R.id.btnFilterClear);
        btnApply = v.findViewById(R.id.btnFilterApply);

        fillTypes = new FillTypes(linType, getContext());

        btnType.setOnClickListener(WorkType);

        chkWorkMonths.setOnCheckedChangeListener(Months);
        spinStartDate.setEnabled(false);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinStartDate.setAdapter(adapter);

        btnClear.setOnClickListener(Clear);
        btnApply.setOnClickListener(Apply);

        return v;
    }

    private View.OnClickListener Apply = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(docs == null)
                StaticMethod.Toast(getContext().getString(R.string.filter_docs_not_loaded), getContext());
            else
            {
                FilterObject filter = new FilterObject();
                filter.setKeywordJob(edtTitle.getText().toString());
                filter.setTypes(fillTypes.getSelectedTypes());
                filter.setPhone(chkPhone.isChecked());
                filter.setEmail(chkEmail.isChecked());
                filter.setWebSite(chkWebSite.isChecked());
                filter.setFacilities(chkFacilities.isChecked());
                if(chkWorkMonths.isChecked())
                    filter.setStartMonth(spinStartDate.getSelectedItem().toString());

                DocumentFilter docFilter = new DocumentFilter(docs,  getContext());
                listener.RecieveFilterdDocuments(docFilter.filterDocs(filter));
            }
        }
    };

    private View.OnClickListener Clear = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            edtTitle.setText("");
            fillTypes.Clear();
            chkPhone.setChecked(false);
            chkEmail.setChecked(false);
            chkWebSite.setChecked(false);
            chkFacilities.setChecked(false);
            chkWorkMonths.setChecked(false);
        }
    };


    private View.OnClickListener WorkType = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(btnType.getText().equals(getContext().getString(R.string.filter_company_type_plus)))
            {
                linType.setVisibility(View.VISIBLE);
                btnType.setText(getContext().getString(R.string.filter_compnay_type_minus));
            }
            else if(btnType.getText().equals(getContext().getString(R.string.filter_compnay_type_minus)))
            {
                linType.setVisibility(View.GONE);
                btnType.setText(getContext().getString(R.string.filter_company_type_plus));
            }

        }
    };


    private CompoundButton.OnCheckedChangeListener Months = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if(isChecked)
            {
                spinStartDate.setEnabled(true);
            }
            else
            {
                spinStartDate.setEnabled(false);
            }
        }
    };

    public void SetDocs(List<CompanyDocument> docs)
    {
        this.docs = docs;
        fillTypes.SetTypes(docs);
    }
}
