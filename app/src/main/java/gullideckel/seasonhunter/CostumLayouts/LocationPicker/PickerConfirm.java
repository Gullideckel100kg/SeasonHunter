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
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.Marker;

import gullideckel.seasonhunter.Interfaces.IAddressSelected;
import gullideckel.seasonhunter.Interfaces.ICancelCommand;
import gullideckel.seasonhunter.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class PickerConfirm
{
    private Marker marker;
    private CompanyAddress address;
    private Bitmap bitmap;
    private LayoutInflater inflater;
    private FrameLayout frm;
    private IAddressSelected listener;

    private TextView txtCoordinates;
    private TextView txtAddress;
    private ImageView imgMap;
    private Button btnChange;
    private Button btnSelect;

    private Button select;


    public PickerConfirm(Button select, IAddressSelected listener, Marker marker, CompanyAddress address, Bitmap bitmap, LayoutInflater inflater, FrameLayout frm)
    {
        this.marker = marker;
        this.address = address;
        this.bitmap = bitmap;
        this.inflater = inflater;
        this.frm = frm;
        this.listener = listener;
        this.select = select;
    }

    public View getView()
    {
        View v = inflater.inflate(R.layout.frag_location_picker_confirm, null);

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
            frm.removeAllViews();
            select.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener Select = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            marker.remove();
            listener.OnSelected();
            select.setVisibility(View.VISIBLE);
        }
    };
}
