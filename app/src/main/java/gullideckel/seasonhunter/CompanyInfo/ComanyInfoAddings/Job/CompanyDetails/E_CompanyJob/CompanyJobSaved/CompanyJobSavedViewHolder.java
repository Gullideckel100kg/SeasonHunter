package gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Job.CompanyDetails.E_CompanyJob.CompanyJobSaved;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class CompanyJobSavedViewHolder extends RecyclerView.ViewHolder
{
    private TextView txtJobTitle;
    private TextView txtPayment;
    private TextView txtDuration;
    private TextView txtAdditionalInfo;

    public TextView getTxtJobTitle()
    {
        return txtJobTitle;
    }

    public TextView getTxtPayment()
    {
        return txtPayment;
    }

    public TextView getTxtDuration()
    {
        return txtDuration;
    }

    public TextView getTxtAdditionalInfo()
    {
        return txtAdditionalInfo;
    }

    public CompanyJobSavedViewHolder(View v)
    {
        super(v);

        txtJobTitle = (TextView) v.findViewById(R.id.txtJobTitle);
        txtPayment = (TextView) v.findViewById(R.id.txtPayKind);
        txtDuration = (TextView) v.findViewById(R.id.txtDuration);
        txtAdditionalInfo = (TextView) v.findViewById(R.id.txtAdditionInfo);

    }
}
