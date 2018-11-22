package gullideckel.seasonhunter.ActivityMap.MapHunterClickListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.ActivitySignIn.SignInHunter;
import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress.Snapshot.MySnapshot;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.FragCompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.CompanyTypes;
import gullideckel.seasonhunter.R;

public class ButtonClicks
{
    private Activity mActivity;
    private Button mBtnAddCompany;
    private Button mBtnLogOut;
    private Button mBtnAddAddress;


    public ButtonClicks(View view, Activity activity)
    {
        mBtnAddCompany = (Button)  view.findViewById(R.id.btnAddCompany);
        mBtnLogOut = (Button) view.findViewById(R.id.btnLogout);
        mBtnAddAddress = (Button) view.findViewById(R.id.btnSelectAddress);
        mActivity = activity;
    }

    public void AddNewCompanyClickEvent()
    {
        mBtnAddCompany.setOnClickListener(ClickNewCompany);
    }

    public void AddNewLogOutClickEvent()
    {
        mBtnLogOut.setOnClickListener(ClickLockOut);
    }

    private GoogleMap mMap;
    private MySnapshot mMySnapshot;
//    public void AddNewAddressClickEvent(GoogleMap map, AddressSelection listener)
//    {
////        mMySnapshot = new MySnapshot(map, listener);
//        mMap = map;
//    }

    //TODO: Companytypes should be saved on Server
    private List<gullideckel.seasonhunter.Objects.JobInformation.CompanyTypes.CompanyType> CompanyTypes;

    private View.OnClickListener ClickNewCompany = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            CompanyTypes = new ArrayList<>();

            CompanyTypes.add(new CompanyTypes.CompanyType(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.farm), "Farm"));
            CompanyTypes.add(new CompanyTypes.CompanyType(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.packing), "Packhouse"));
            CompanyTypes.add(new CompanyTypes.CompanyType(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.fruit), "Fruit farm"));
            CompanyTypes.add(new CompanyTypes.CompanyType(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.chef), "Restaurant"));
            CompanyTypes.add(new CompanyTypes.CompanyType(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.tree), "Tree planting"));
            CompanyTypes.add(new CompanyTypes.CompanyType(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.factory), "Factory"));
            CompanyTypes.add(new CompanyTypes.CompanyType(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.christmas), "Christmas"));
            CompanyTypes.add(new CompanyTypes.CompanyType(BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.otherwork), "Others"));


            ((IReplaceFragment) mActivity).onReplaceFragment(FragCompanyDetails.NewInstance(CompanyTypes));
        }
    };

    private View.OnClickListener ClickLockOut = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(mActivity, SignInHunter.class);
            mActivity.startActivity(intent);
            mActivity.finish();
        }
    };

//    private View.OnClickListener ClickNewAddress = new View.OnClickListener() {
//        @Override
//        public void onClick(View v)
//        {
//           mMap.setOnMapLoadedCallback(mMySnapshot);
//        }
//    };
}
