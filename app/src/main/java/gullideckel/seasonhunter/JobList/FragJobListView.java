package gullideckel.seasonhunter.JobList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.InputDeviceCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gullideckel.seasonhunter.CompanyInfo.FragCompanyInfo;
import gullideckel.seasonhunter.CostumLayouts.CostumLayoutManager;
import gullideckel.seasonhunter.Interfaces.IDocument;
import gullideckel.seasonhunter.Interfaces.IListPage;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;


public class FragJobListView extends Fragment
{
    protected List<CompanyDocument> docs;

    private boolean isListReady = false;

    private RecyclerView rcylListView;


    public static FragJobListView newInstance()
    {
        FragJobListView fragment = new FragJobListView();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_job_list, container, false);

        rcylListView = (RecyclerView) v.findViewById(R.id.rcylListView);
        rcylListView.setLayoutManager(new CostumLayoutManager(getContext()));

        isListReady = true;
        if(docs != null)
            InitList();

        return v;
    }

    public void InitDocuments(List<CompanyDocument> docs)
    {
        this.docs = docs;
        if(isListReady)
            InitList();
    }

    private void InitList()
    {
        rcylListView.setAdapter(new JobListViewAdapter(docs, (IDocument)getContext(), getActivity().getSupportFragmentManager()));
    }
}
