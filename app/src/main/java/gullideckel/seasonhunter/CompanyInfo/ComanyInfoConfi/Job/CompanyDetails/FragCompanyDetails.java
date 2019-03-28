package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.Interfaces.IPost;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.Objects.Job.CompanyName;
import gullideckel.seasonhunter.R;


public class FragCompanyDetails extends Fragment implements IPost, GoogleApiClient.OnConnectionFailedListener
{
    private static final String TAG = "FragCompanyDetails";

    private RecyclerView mRclyCompanyDetails;

    private CompanyDetails companyDetails;

    private CollectionReference mRef = FirebaseFirestore.getInstance().collection("companies");

    private GoogleApiClient googleApiClient;

    private IFragmentHandler listener;


    //TODO: List
    //TODO: Make a tree for Benefits in extra Fragment or Intent
    //TODO: Set limit for additional info(jobs)
    //TODO: Get Job Offers and Benefits in Extra Intent
    //TODO: Fix Layout with loading total list and switch between items with VISIBLE and GONE
    //TODO: Build in navigation between items, Scroll down or Sroll up bottoms or something like this
    //TODO: Fix GoogleMap Resizing Problem with KeyPad
    //TODO: Check KeyPad Bug at every item. Big test neccersarry
    //TODO: Fix Layout at item Contact. To much space if not filled out every Contact box
    //TODO: Fix Post Button after filled out every item. It shouldnt show up if a item is in edit mode
    //TODO: Remove all the useless code
    //TODO: After fixed this problems bring example for design to Adrian
    public static FragCompanyDetails NewInstance()
    {
        FragCompanyDetails fragment = new FragCompanyDetails();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        view.bringToFront();
        view.setBackgroundColor(Color.WHITE);

//        mRclyCompanyDetails = (RecyclerView) view.findViewById(R.id.rclyCompanyDetails);
//
//        CostumLayoutManager layoutManager = new CostumLayoutManager(getContext());
//        mRclyCompanyDetails.setLayoutManager(layoutManager);
//
//        Button btnPost = (Button) view.findViewById(R.id.btnPostJob);
//        btnPost.setOnClickListener(Post);
//
//        companyDetails = new CompanyDetails(getContext(), btnPost, mRclyCompanyDetails, googleApiClient);
//
//        ComplexCompanyDetailsAdapter adapter = new ComplexCompanyDetailsAdapter(companyDetails);
//        mRclyCompanyDetails.setAdapter(adapter);
    }


    private class TestObject
    {
        private TestObject() {}

        private int id;

        @Exclude
        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        private List<CompanyJobs.CompanyJob> jobs;
        private CompanyName companyName;

        public CompanyName getCompanyName()
        {
            return companyName;
        }


        public TestObject(List<CompanyJobs.CompanyJob> jobs, CompanyName companyName)
        {
            this.jobs = jobs;
            this.companyName = companyName;
        }

        public List<CompanyJobs.CompanyJob> getJobs()
        {
            return jobs;
        }
    }


    //TODO: Add SuccessListener and FailListener before closing Fragment or just be imagenativ
    private View.OnClickListener Post = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mRef.add(companyDetails);
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
//            {
//                @Override
//                public void onSuccess(DocumentReference documentReference)
//                {
//                    Toast.makeText(getContext(), getContext().getText(R.string.company_saved),Toast.LENGTH_LONG);
//                }
//            })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e)
//                    {
//                        Toast.makeText(getContext(), getContext().getText(R.string.company_saved_failed),Toast.LENGTH_LONG);
//                        Log.d(TAG, "onFailure: " + e);
//                    }
//                });
            listener.onReplaceFragment(null, IntFrag.POPSTACKCOMPLETLY);
        }
    };

//    private void SetDocument(String document, Object obj)
//    {
//        DocumentReference docRef = mRef.document(document);
//
//        docRef.set(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid)
//            {
//                Log.d(TAG, "Document has been saved");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e)
//            {
//                Log.w(TAG, "Document was not saved");
//            }
//        });
//
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_company_details, container, false);
    }

    @Override
    public void OnPost()
    {
        companyDetails.getBtnPost().setVisibility(View.VISIBLE);
    }

//    @Override
//    public void onAttach(Context context)
//    {
//        super.onAttach(context);
//        googleApiClient = new GoogleApiClient
//                .Builder(getContext())
//                .addApi(Places.GEO_DATA_API)
//                .addApi(Places.PLACE_DETECTION_API)
//                .enableAutoManage(getActivity(), this)
//                .build();
//
//        if (context instanceof IFragmentHandler)
//            listener = (IFragmentHandler) context;
//         else
//            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
//
//    }


//    @Override
//    public void onDetach()
//    {
//        super.onDetach();
//
//        googleApiClient.stopAutoManage(getActivity());
//        googleApiClient.disconnect();
//        listener = null;
//    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Log.d(TAG, "Connection Failed PlaceAutoComplete: " + connectionResult.getErrorMessage());
    }
}