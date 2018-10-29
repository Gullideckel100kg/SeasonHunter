package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class CostumLayoutManager extends LinearLayoutManager
{
    private boolean isScrollEnabled = true;

    public CostumLayoutManager(Context context)
    {
        super(context);
    }

    public void setScrollEnabled(boolean flag)
    {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically()
    {
        return isScrollEnabled && super.canScrollVertically();
    }
}
