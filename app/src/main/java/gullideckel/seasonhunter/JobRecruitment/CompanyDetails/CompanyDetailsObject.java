package gullideckel.seasonhunter.JobRecruitment.CompanyDetails;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.CostumLayoutManager;

public class CompanyDetailsObject
{

    private List<Object> items;
    private CostumLayoutManager layoutManager;
    private Context context;
    private Button btnPost;

    public CompanyDetailsObject(List<Object> items, CostumLayoutManager layoutManager, Context context, Button btnPost)
    {
        this.items = items;
        this.layoutManager = layoutManager;
        this.context = context;
        this.btnPost = btnPost;
    }

    public List<Object> getItems()
    {
        return items;
    }

    public CostumLayoutManager getLayoutManager()
    {

        return layoutManager;
    }

    public Context getContext()
    {
        return context;
    }

    public Button getBtnPost()
    {
        return btnPost;
    }
}
