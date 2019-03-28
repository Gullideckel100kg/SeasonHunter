package gullideckel.seasonhunter.CostumLayouts.LocationPicker;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;

import gullideckel.seasonhunter.Interfaces.ICancelCommand;
import gullideckel.seasonhunter.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class PickerConfirm extends Fragment
{
    protected ICompanyAddress listener;
    protected Marker marker;
    protected CompanyAddress address;
    protected Bitmap bitmap;

    private TextView txtCoordinates;
    private TextView txtAddress;
    private ImageView imgMap;
    private Button btnChange;
    private Button btnSelect;


    public static PickerConfirm newInstance(ICompanyAddress listener, Marker marker, CompanyAddress  address, Bitmap bitmap)
    {
        PickerConfirm fragment = new PickerConfirm();
        fragment.listener = listener;
        fragment.marker = marker;
        fragment.bitmap = bitmap;
        fragment.address = address;
        return  fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_location_picker_confirm, container, false);

        v.bringToFront();
        v.setBackgroundColor(Color.WHITE);

        txtCoordinates = (TextView) v.findViewById(R.id.txtPickerCoordinates);
        txtAddress = (TextView) v.findViewById(R.id.txtPickerAddress);
        imgMap = (ImageView) v.findViewById(R.id.imgPickerMap);
        btnChange = (Button) v.findViewById(R.id.btnPickerChange);
        btnSelect = (Button) v.findViewById(R.id.btnPickerSelect);

        txtCoordinates.setText(StaticMethod.GPSConvert(address.getLatitude(), address.getLongitude()));
        txtAddress.setText(address.getAddress());

        imgMap.setImageBitmap(bitmap);
        btnSelect.setOnClickListener(Select);
        btnChange.setOnClickListener(Change);


        return v;
    }


    private View.OnClickListener Change =new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            marker.remove();
            getActivity().onBackPressed();
        }
    };

    private View.OnClickListener Select = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            listener.OnCompanyAddress(address);
            marker.remove();
            getActivity().onBackPressed();
        }
    };
}
