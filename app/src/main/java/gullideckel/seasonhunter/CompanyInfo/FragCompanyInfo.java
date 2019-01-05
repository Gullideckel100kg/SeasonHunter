package gullideckel.seasonhunter.CompanyInfo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Objects.JobInformation.CompanyDocument;
import gullideckel.seasonhunter.R;

public class FragCompanyInfo extends Fragment
{
    private IFragmentHandler listener;
    protected CompanyDocument doc;

    public static FragCompanyInfo NewInstance(CompanyDocument doc)
    {
        FragCompanyInfo frag = new FragCompanyInfo();
        frag.doc = doc;
        return frag;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        view.bringToFront();
        view.setBackgroundColor(Color.WHITE);

        InitCompanyInfo compInit = new InitCompanyInfo(view, getContext());

        compInit.Init(doc);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_company_info, container, false);
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof IFragmentHandler)
            listener = (IFragmentHandler) context;
        else
            throw new RuntimeException(context.toString() + " must implement IFragmentHandler");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        listener = null;
    }

}
