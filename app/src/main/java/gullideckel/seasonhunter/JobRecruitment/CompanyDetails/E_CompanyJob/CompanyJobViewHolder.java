package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import gullideckel.seasonhunter.R;

public class CompanyJobViewHolder extends RecyclerView.ViewHolder
{
    private Button btnAddJobOffers;


    private RelativeLayout relJobs;

    private ConstraintLayout cnstAddJobOffers;
    private ConstraintLayout cnstJobSaved;

    private RecyclerView rcylJobSaved;
    private ImageButton iBtnJobEdit;

    public CompanyJobViewHolder(View v)
    {
        super(v);

        relJobs = (RelativeLayout) v.findViewById(R.id.relJobs);
        btnAddJobOffers = (Button) v.findViewById(R.id.btnAddJobOffers);

        relJobs = (RelativeLayout) v.findViewById(R.id.relJobs);

        cnstAddJobOffers = (ConstraintLayout) v.findViewById(R.id.cnstAddJobOffers);
        cnstJobSaved = (ConstraintLayout) v.findViewById(R.id.cnstJobSaved);
        rcylJobSaved = (RecyclerView) v.findViewById(R.id.rcylJobSaved);
        iBtnJobEdit = (ImageButton) v.findViewById(R.id.iBtnEditJob);
    }

    public Button getBtnAddJobOffers()
    {
        return btnAddJobOffers;
    }

    public RelativeLayout getRelJobs()
    {
        return relJobs;
    }

    public ConstraintLayout getCnstAddJobOffers()
    {
        return cnstAddJobOffers;
    }

    public ConstraintLayout getCnstJobSaved()
    {
        return cnstJobSaved;
    }

    public RecyclerView getRcylJobSaved()
    {
        return rcylJobSaved;
    }

    public ImageButton getiBtnJobEdit()
    {
        return iBtnJobEdit;
    }
}
