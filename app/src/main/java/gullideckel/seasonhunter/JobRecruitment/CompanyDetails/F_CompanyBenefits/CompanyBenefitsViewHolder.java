package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.F_CompanyBenefits;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gullideckel.seasonhunter.CostumLayouts.AutoFitGridRecyclerView;
import gullideckel.seasonhunter.R;

public class CompanyBenefitsViewHolder extends RecyclerView.ViewHolder
{
//    private RelativeLayout relBenefits;
//
//    private AutoFitGridRecyclerView rcylBenefitsSaved;
//    private RecyclerView rcylBenefitsEdit;
//
//    private ConstraintLayout cnstBenefitsSaved;
//    private ConstraintLayout cnstBenefitsEdit;
//
//    private Button btnSave;
//    private ImageButton iBtnEdit;
//
//    public RelativeLayout getRelBenefits()
//    {
//        return relBenefits;
//    }
//
//    public Button getBtnSave()
//    {
//        return btnSave;
//    }
//
//    public ImageButton getiBtnEdit()
//    {
//        return iBtnEdit;
//    }
//
//    public AutoFitGridRecyclerView getRcylBenefitsSaved()
//    {
//        return rcylBenefitsSaved;
//    }
//
//    public RecyclerView getRcylBenefitsEdit()
//    {
//        return rcylBenefitsEdit;
//    }
//
//    public ConstraintLayout getCnstBenefitsSaved()
//    {
//        return cnstBenefitsSaved;
//    }
//
//    public ConstraintLayout getCnstBenefitsEdit()
//    {
//        return cnstBenefitsEdit;
//    }

    private EditText edtFeatures;
    private ConstraintLayout cnstFeatures;

    public EditText getEdtFeatures()
    {
        return edtFeatures;
    }

    public ConstraintLayout getCnstFeatures()
    {
        return cnstFeatures;
    }

    public CompanyBenefitsViewHolder(View v)
    {
        super(v);

        edtFeatures = (EditText) v.findViewById(R.id.edtWorkFeatures);
        cnstFeatures = (ConstraintLayout) v.findViewById(R.id.cnstFeatures);

//        relBenefits = (RelativeLayout) v.findViewById(R.id.relBenefits);
//
//        rcylBenefitsEdit = (RecyclerView) v.findViewById(R.id.rcylBenefitsEdit);
//        rcylBenefitsSaved = (AutoFitGridRecyclerView) v.findViewById(R.id.rcylBenefitsSaved);
//
//        cnstBenefitsEdit = (ConstraintLayout) v.findViewById(R.id.cnstBenefitEdit);
//        cnstBenefitsSaved = (ConstraintLayout) v.findViewById(R.id.cnstBenefitSaved);
//
//
//        btnSave = (Button) v.findViewById(R.id.btnSaveBenefits);
//        iBtnEdit =(ImageButton) v.findViewById(R.id.iBtnEditBenefits);

    }
}
