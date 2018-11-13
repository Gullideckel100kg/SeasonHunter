package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.E_CompanyJob;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import gullideckel.seasonhunter.R;

public class CompanyJobViewHolder extends RecyclerView.ViewHolder
{
    private ConstraintLayout cnstJobEdit;
    private ConstraintLayout cnstJobSaved;

    private RecyclerView rcylJobEdit;
    private RecyclerView rcylJobSaved;

    private ImageButton iBtnAddJob;

    private Button btnJobSave;
    private ImageButton iBtnJobEdit;

    public CompanyJobViewHolder(View v)
    {
        super(v);

        cnstJobEdit = (ConstraintLayout) v.findViewById(R.id.cnstJobEdit);
        cnstJobSaved = (ConstraintLayout) v.findViewById(R.id.cnstJobSaved);

        rcylJobEdit = (RecyclerView) v.findViewById(R.id.rcylJobEdit);
        rcylJobSaved = (RecyclerView) v.findViewById(R.id.rcylJobSaved);

        iBtnAddJob = (ImageButton) v.findViewById(R.id.iBtnAddJob);

        btnJobSave = (Button) v.findViewById(R.id.btnSaveJob);
        iBtnJobEdit = (ImageButton) v.findViewById(R.id.iBtnEditJob);
    }

    public ConstraintLayout getCnstJobEdit()
    {
        return cnstJobEdit;
    }

    public ConstraintLayout getCnstJobSaved()
    {
        return cnstJobSaved;
    }

    public RecyclerView getRcylJobEdit()
    {
        return rcylJobEdit;
    }

    public RecyclerView getRcylJobSaved()
    {
        return rcylJobSaved;
    }

    public ImageButton getiBtnAddJob()
    {
        return iBtnAddJob;
    }

    public Button getBtnJobSave()
    {
        return btnJobSave;
    }

    public ImageButton getiBtnJobEdit()
    {
        return iBtnJobEdit;
    }
}
