package gullideckel.seasonhunter.JobFilter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;

import java.util.Date;
import java.util.List;

import gullideckel.seasonhunter.CostumLayouts.CostumRangeCalender.FragCostumRangeCalender;
import gullideckel.seasonhunter.Interfaces.IDocumentList;
import gullideckel.seasonhunter.Interfaces.IFilteredDocuments;
import gullideckel.seasonhunter.Interfaces.ISelectedDate;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;


public class FragJobFilter extends Fragment implements ISelectedDate
{
    private static final String TAG = "FragJobFilter";

    private EditText edtTitle;
    private Button btnType;
    private LinearLayout linType;
    private CheckBox chkPhone;
    private CheckBox chkEmail;
    private CheckBox chkWebSite;
    private CheckBox chkFacilities;
    private EditText edtFacilities;
    private Button btnDate;
    private TextView txtDate;
    private Button btnClear;
    private Button btnApply;

    private IFilteredDocuments listener;
    private List<CompanyDocument> docs;
    private Date startDate;
    private Date endDate;
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
        edtFacilities = v.findViewById(R.id.edtFilterFacilites);
        btnDate = v.findViewById(R.id.btnFilterDate);
        txtDate = v.findViewById(R.id.txtFilterDate);
        btnClear = v.findViewById(R.id.btnFilterClear);
        btnApply = v.findViewById(R.id.btnFilterApply);

        fillTypes = new FillTypes(linType, getContext());

        chkFacilities.setOnCheckedChangeListener(Facilities);
        btnType.setOnClickListener(WorkType);
        btnDate.setOnClickListener(WorkDate);
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
                List<String> types = fillTypes.getSelectedTypes();
                Log.e(TAG, "onClick: " );
            }
        }
    };

    private View.OnClickListener Clear = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            edtTitle.setText("");
            txtDate.setText("");
            startDate = null;
            endDate = null;
            fillTypes.Clear();
            chkPhone.setChecked(false);
            chkEmail.setChecked(false);
            chkWebSite.setChecked(false);
            chkFacilities.setChecked(false);
            edtFacilities.setText("");
        }
    };

    private View.OnClickListener WorkDate = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.frmFilterCalender, FragCostumRangeCalender.newInstance(FragJobFilter.this));
            transaction.addToBackStack(null);
            transaction.commit();
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

    public void SetDocs(List<CompanyDocument> docs)
    {
        this.docs = docs;
        fillTypes.SetTypes(docs);
    }

    @Override
    public void RecieveDates(Date startDate, Date endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        if(startDate != null)
        {
            if(endDate != null)
                txtDate.setText(StaticMethod.getDate(startDate, "dd/MM/yyyy") + " - " + StaticMethod.getDate(endDate, "yy/MM/yyyy"));
            else
                txtDate.setText(StaticMethod.getDate(startDate, "dd/MM/yyyy"));

        }
    }
}
