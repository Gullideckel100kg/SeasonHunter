package gullideckel.seasonhunter.JobRecruitmentOld.Fragments.FragCompanyInfo;

import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitmentOld.Fragments.Adapters.AdapterAddedCompanyType;
import gullideckel.seasonhunter.JobRecruitmentOld.Fragments.Adapters.AdapterCompanyType;
import gullideckel.seasonhunter.Objects.CompanyTypeObject;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;

public class FillCompanyInfo
{
    public void FillCompanyInfo(JobInfoObject jobInfo, EditText companyName, CheckBox chkOrganic)
    {
        if(jobInfo != null && jobInfo.GetCompanyInfo() != null)
        {
            companyName.setText(jobInfo.GetCompanyInfo().GetCompanyName());
            chkOrganic.setChecked(jobInfo.GetCompanyInfo().GetOrganic());
        }
    }

    public void FillCompanyTypes(JobInfoObject jobInfo, List<CompanyTypeObject> companyTypes, AdapterCompanyType adapter, List<CompanyTypeObject> addedCompanyTypes, AdapterAddedCompanyType adapterAdded)
    {
        if(jobInfo.GetCompanyInfo() != null && jobInfo.GetCompanyInfo().GetCompanyTypes() != null)
        {
            for (String cmptype : jobInfo.GetCompanyInfo().GetCompanyTypes())
            {
                CompanyTypeObject cmpInfo = new CompanyTypeObject(cmptype, false);
                for (CompanyTypeObject obj: companyTypes)
                {
                    if(obj.GetCompanyType().contains(cmptype))
                    {
                        companyTypes.get(companyTypes.indexOf(obj)).SetChecked(true);
                        cmpInfo = obj;
                        adapter.notifyDataSetChanged();
                    }
                }
                addedCompanyTypes.add(cmpInfo);
                adapterAdded.notifyDataSetChanged();
            }
        }
    }

    public boolean IsCorrectFilled(EditText companyName, List<CompanyTypeObject> addedCompanyType, TextView txtNoCompanyName)
    {
        boolean isCorrectFilled = true;

        if(companyName.getText().toString().isEmpty())
        {
            companyName.setHint("Please insert the name of the company");
            companyName.setHintTextColor(Color.RED);
            isCorrectFilled = false;
        }
        if (addedCompanyType.size() == 0)
        {
            txtNoCompanyName.setVisibility(View.VISIBLE);
            isCorrectFilled = false;
        }

        return isCorrectFilled;
    }
}
