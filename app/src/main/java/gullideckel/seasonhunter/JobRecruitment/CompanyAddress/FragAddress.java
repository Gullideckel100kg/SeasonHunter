package gullideckel.seasonhunter.JobRecruitment.CompanyAddress;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import gullideckel.seasonhunter.Interfaces.IAddressHandler;
import gullideckel.seasonhunter.Interfaces.IReplaceFragment;
import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.FragCompanyDetails;
import gullideckel.seasonhunter.Objects.JobInformation.JobInfoObject;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.StaticMethods.StaticMethod;

public class FragAddress extends Fragment
{

    protected JobInfoObject mJobInfo;
    protected Bitmap mMapShot;

    private IReplaceFragment mListener;
    private IAddressHandler mAddrssListener;

    public static FragAddress newInstance(JobInfoObject jobInfo, Bitmap mapShot)
    {
        FragAddress fragment = new FragAddress();
        fragment.mJobInfo = jobInfo;
        fragment.mMapShot = mapShot;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        ImageView imgMapShot = view.findViewById(R.id.imgIsAddress);
        TextView txtAddress = view.findViewById(R.id.txtIsAddress);
        TextView txtCoordinates = view.findViewById(R.id.txtIsCoordinates);
        Button btnChange = view.findViewById(R.id.btnAddressChange);
        Button btnSelect = view.findViewById(R.id.btnAddressSelect);

        imgMapShot.setImageBitmap(mMapShot);
        txtAddress.setText(mJobInfo.GetCompanyAddress().GetAddress());
        txtCoordinates.setText(StaticMethod.GPSConvert(mJobInfo.GetCompanyAddress().GetLatitude(), mJobInfo.GetCompanyAddress().GetLongitude()));

        btnChange.setOnClickListener(ChangeAddress);
        btnSelect.setOnClickListener(SelectAddress);
    }

    private View.OnClickListener ChangeAddress = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mAddrssListener.SetAddress(mJobInfo.GetCompanyName().GetCompanyName());
        }
    };

    private View.OnClickListener SelectAddress = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
//            mListener.onReplaceFragment(FragCompanyDetails.newInstance(mJobInfo, mMapShot));
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_address, container, false);
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof IReplaceFragment)
        {
            mListener = (IReplaceFragment) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement IReplaceFragment");
        }

        if (context instanceof IAddressHandler)
        {
            mAddrssListener = (IAddressHandler) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement IAddressHandler");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
        mAddrssListener = null;
    }
}
