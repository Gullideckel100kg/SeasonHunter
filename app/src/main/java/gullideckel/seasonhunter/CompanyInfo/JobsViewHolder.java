package gullideckel.seasonhunter.CompanyInfo;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import gullideckel.seasonhunter.R;

public class JobsViewHolder extends RecyclerView.ViewHolder
{
    private TextView txtJobTitle;
    private TextView txtDuration;
    private TextView txtAdditionalInfo;

    public TextView getTxtJobTitle()
    {
        return txtJobTitle;
    }

    public TextView getTxtDuration()
    {
        return txtDuration;
    }

    public TextView getTxtAdditionalInfo()
    {
        return txtAdditionalInfo;
    }

    public JobsViewHolder(View v)

    {
        super(v);

        txtJobTitle = (TextView) v.findViewById(R.id.txtJobTitle);
        txtDuration = (TextView) v.findViewById(R.id.txtDuration);
        txtAdditionalInfo = (TextView) v.findViewById(R.id.txtAdditionInfo);
    }
}
