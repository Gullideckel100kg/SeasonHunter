package gullideckel.seasonhunter.JobList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class JobListViewHolder extends RecyclerView.ViewHolder
{

    private TextView txtListName;
    private TextView txtSowMore;
    private ImageView imgType;
    private LinearLayout linJobs;


    public JobListViewHolder(View v)
    {
        super(v);

        txtListName = (TextView) v.findViewById(R.id.txtListCompanyName);
        txtSowMore = (TextView) v.findViewById(R.id.txtListShowMore);
        imgType = (ImageView) v.findViewById(R.id.imgListType);
        linJobs = (LinearLayout) v.findViewById(R.id.linListJobs);
    }

    public TextView getTxtListName()
    {
        return txtListName;
    }


    public TextView getTxtSowMore()
    {
        return txtSowMore;
    }

    public ImageView getImgType()
    {
        return imgType;
    }

    public LinearLayout getLinJobs()
    {
        return linJobs;
    }


}
