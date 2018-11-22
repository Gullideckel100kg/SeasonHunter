package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import gullideckel.seasonhunter.R;

public class CompanyTypeViewHolderList extends RecyclerView.ViewHolder
{

    private TextView mTxtCompanyType;
    private ImageView mImgCompanyType;
    private RadioButton mRdbCompanyType;
    private View v;

    private int mLastCheckedPosition;

    public View getV()
    {
        return v;
    }

    public CompanyTypeViewHolderList(View v)
    {
        super(v);
        this.v = v;


        mTxtCompanyType = (TextView) v.findViewById(R.id.txtAddCompanyType);
        mImgCompanyType = (ImageView) v.findViewById(R.id.imgCompanyTypeLogo);
        mRdbCompanyType = (RadioButton) v.findViewById(R.id.rdbAddCompanyType);
    }

    public TextView GetTxtCompanyType()
    {
        return mTxtCompanyType;
    }

    public ImageView GetImgCompanyType()
    {
        return mImgCompanyType;
    }

    public RadioButton GetRdbCompanyType()
    {
        return mRdbCompanyType;
    }

}
