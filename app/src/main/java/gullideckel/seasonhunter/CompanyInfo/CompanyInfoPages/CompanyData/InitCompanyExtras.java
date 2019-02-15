package gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.CompanyData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import gullideckel.seasonhunter.Objects.Job.CompanyExtras;
import gullideckel.seasonhunter.R;

public class InitCompanyExtras
{
    private LayoutInflater inflater;
    private Context context;

    public InitCompanyExtras(LayoutInflater inflater, Context context)
    {
        this.inflater = inflater;
        this.context = context;
    }

    public void InitExtras(LinearLayout linExtras, CompanyExtras extras)
    {
        if(extras == null || extras.getCourses().isEmpty() && extras.getFacilities().isEmpty())
            linExtras.setVisibility(View.GONE);

        if(!extras.getCourses().isEmpty())
            linExtras.addView(getExtraView(context.getText(R.string.courses).toString(), extras.getCourses()));

        if(!extras.getFacilities().isEmpty())
            linExtras.addView(getExtraView(context.getText(R.string.facilities).toString(), extras.getFacilities()));
    }

    private View getExtraView(String strHeader, String strNotes)
    {
        View v = inflater.inflate(R.layout.frag_company_data_extras_view, null);

        TextView header = (TextView) v.findViewById(R.id.txtExtrasHead);
        TextView notes = (TextView) v.findViewById(R.id.txtExtrasNotes);

        header.setText(strHeader);
        notes.setText(strNotes);

        return v;
    }

}
