package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.Interfaces.ICompanyType;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyType;

public class CompanyTypeConfi
{
    private CompanyTypeViewHolder mHolder;
    private ICompanyType mListener;
    private List<CompanyType> mItmes;
    private Context mContext;
    private CompanyTypeAdapter mAdapter;

    public CompanyTypeConfi(CompanyTypeViewHolder holder, ICompanyType listener, List<CompanyType> items, Context context)
    {
        mHolder = holder;
        mListener = listener;
        mItmes = items;
        mContext = context;
    }

    public void Confi()
    {
        mAdapter = new CompanyTypeAdapter(mItmes);
        mHolder.GetRclyCompanyType().setLayoutManager(new LinearLayoutManager(mContext));
        mHolder.GetRclyCompanyType().setAdapter(mAdapter);

        mHolder.GetBtnSave().setOnClickListener(SaveCompanyType);
        mHolder.GetIBtnEdit().setOnClickListener(EditCompanyType);
    }

    private View.OnClickListener SaveCompanyType = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(mAdapter.GetCompanyType() != null)
            {
                mHolder.GetCnstEdit().setVisibility(View.VISIBLE);
                mHolder.GetCnstSave().setVisibility(View.GONE);
                mHolder.GetImgCompanyLogo().setImageBitmap(mAdapter.GetCompanyType().GetLogo());
                mHolder.GetTxtCompanyType().setText(mAdapter.GetCompanyType().GetCompanyType());
                mListener.OnCompanyType(mAdapter.GetCompanyType());
            }
            else
                mHolder.GetSelectCompanyType().setTextColor(Color.RED);
        }
    };

    private View.OnClickListener EditCompanyType = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mHolder.GetSelectCompanyType().setTextColor(Color.BLACK);
            mHolder.GetCnstEdit().setVisibility(View.GONE);
            mHolder.GetCnstSave().setVisibility(View.VISIBLE);
        }
    };
}
