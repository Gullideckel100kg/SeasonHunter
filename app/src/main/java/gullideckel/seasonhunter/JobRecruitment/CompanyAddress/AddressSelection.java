package gullideckel.seasonhunter.JobRecruitment.CompanyAddress;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import gullideckel.seasonhunter.ActivityMap.GeoCoding.CameraMove;
import gullideckel.seasonhunter.ActivityMap.GeoCoding.GeoMap;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class AddressSelection
{
    private ImageView mImgMarker;
    private Button mBtnSelectAddress;
    private TextView mTxtCompanyName;
    private TextView mTxtMapAddress;
    private TextView mTxtMapCoordinates;
    private LinearLayout mLinMapAddress;
    private LinearLayout mLinMapTopBar;
    private FrameLayout mFrmAutoPlace;

    public AddressSelection(View view)
    {
        mImgMarker = (ImageView) view.findViewById(R.id.imgMarker);
        mTxtCompanyName = (TextView) view.findViewById(R.id.txtCompanyName);
        mBtnSelectAddress = (Button) view.findViewById(R.id.btnSelectAddress);
        mTxtMapAddress = (TextView) view.findViewById(R.id.txtMapAddress);
        mTxtMapCoordinates = (TextView) view.findViewById(R.id.txtMapCoordinates);
        mLinMapAddress = (LinearLayout) view.findViewById(R.id.linMapAddress);
        mFrmAutoPlace= (FrameLayout) view.findViewById(R.id.frmAutoPlace);
        mLinMapTopBar = (LinearLayout) view.findViewById(R.id.linMapTopBar);
    }

    public void SetVisibilities(int visiTopBar, int visiAddress)
    {
        mLinMapTopBar.setVisibility(visiTopBar);
        mTxtCompanyName.setVisibility(visiAddress);
        mBtnSelectAddress.setVisibility(visiAddress);
        mImgMarker.setVisibility(visiAddress);
        mLinMapAddress.setVisibility(visiAddress);
        mFrmAutoPlace.setVisibility(visiAddress);
    }

    public void SetAddress(String companyName, Activity activity, GeoMap geoMap)
    {
        SetVisibilities(View.INVISIBLE, View.VISIBLE);
        mTxtCompanyName.setText(companyName);

        StaticMethod.RemoveKeyPad(activity);

        CameraMove cameraMove = new CameraMove(mTxtMapAddress, mTxtMapCoordinates, activity);
        cameraMove.Start(geoMap);
    }
}
