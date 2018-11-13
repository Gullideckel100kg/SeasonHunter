package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.C_CompanyAddress;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import gullideckel.seasonhunter.R;

public class CompanyAddressViewHolder extends RecyclerView.ViewHolder
{
    private TextView mTxtAddressHeadLine;
    private TextView mTxtAddress;
    private TextView mTxtCoordinates;
    private Button mBtnSave;
    private ImageButton mIBtnEdit;

    private RelativeLayout relSnapPic;

    public RelativeLayout getRelSnapPic()
    {
        return relSnapPic;
    }

    private ImageView mSnapAddress;
    private ImageView mSnapLogo;
    private AutoCompleteTextView mTxtAutoComplete;

    private MapView mMapView;
    private ImageView mLogo;
    private ConstraintLayout mCnstMapView;


    public CompanyAddressViewHolder(View v)
    {
        super(v);

        mTxtAddressHeadLine = v.findViewById(R.id.txtAddressHeadLine);
        mTxtAddress = v.findViewById(R.id.txtJobAddress);
        mTxtCoordinates = v.findViewById(R.id.txtCoordinates);

        mBtnSave = v.findViewById(R.id.btnSelectAddress);
        mIBtnEdit = v.findViewById(R.id.ibtnEditAddress);

        mTxtAutoComplete = v.findViewById(R.id.txtAutoComplete);

        relSnapPic = (RelativeLayout) v.findViewById(R.id.relSnapPic);
        mSnapAddress = v.findViewById(R.id.imgSnapAddress);
        mSnapLogo = v.findViewById(R.id.imgSnapLogo);

        mMapView = v.findViewById(R.id.mapView);
        mLogo = v.findViewById(R.id.imgJobMarker);
        mCnstMapView = v.findViewById(R.id.cnstMapView);

    }

    public TextView GetTxtAddressHeadLine()
    {
        return mTxtAddressHeadLine;
    }

    public TextView GetTxtAddress()
    {
        return mTxtAddress;
    }

    public TextView GetTxtCoordinates()
    {
        return mTxtCoordinates;
    }

    public Button GetBtnSave()
    {
        return mBtnSave;
    }

    public ImageButton GetIBtnEdit()
    {
        return mIBtnEdit;
    }

    public AutoCompleteTextView GetTxtAutoComplete()
    {
        return mTxtAutoComplete;
    }

    public ImageView GetSnapAddress()
    {
        return mSnapAddress;
    }

    public ImageView GetSnapLogo()  { return mSnapLogo; }

    public MapView GetMapView()
    {
        return mMapView;
    }

    public ImageView GetLogo()
    {
        return mLogo;
    }

    public ConstraintLayout GetCnstMapView()
    {
        return mCnstMapView;
    }
}
