package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import gullideckel.seasonhunter.CostumLayouts.LocationPicker.FragLocationPicker;
import gullideckel.seasonhunter.CostumLayouts.LocationPicker.PickerTask;
import gullideckel.seasonhunter.CostumLayouts.PlaceAutoComplete.PlaceAutoCompleteTextView;
import gullideckel.seasonhunter.Interfaces.ICompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyAddress;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;

public class Address implements ICompanyAddress
{
    private LayoutInflater inflater;
    private FragmentManager manager;
    private CompanyAddress address;
    private GoogleApiClient client;
    private Context context;
    private View v;

    private TextView txtAddress;
    private AutoCompleteTextView edaAddress;
    private ImageButton imbPicker;

    public Address(LayoutInflater inflater, FragmentManager manager, CompanyAddress address, GoogleApiClient client, Context context)
    {
        this.inflater = inflater;
        this.manager = manager;
        this.address = address;
        this.client = client;
        this.context = context;
    }

    public View getView()
    {
        if(v == null)
        {
            v = inflater.inflate(R.layout.frag_edit_address, null);

            txtAddress = (TextView) v.findViewById(R.id.txtEditAddress);
            edaAddress = (AutoCompleteTextView) v.findViewById(R.id.edaEditAddress);
            imbPicker = (ImageButton) v.findViewById(R.id.imbEditAddress);

            txtAddress.setText(address.getAddress());

            imbPicker.setOnClickListener(Picker);

            PlaceAutoCompleteTextView autoComplete = new PlaceAutoCompleteTextView(context, edaAddress, client, this);
            autoComplete.Init();
        }

        return v;
    }



    private View.OnClickListener Picker = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.frmEditMapPicker, FragLocationPicker.newInstance(Address.this));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };

    @Override
    public void OnCompanyAddress(CompanyAddress companyAddress)
    {
        if(companyAddress != null)
        {
            edaAddress.setText(companyAddress.getAddress());
            this.address = companyAddress;
        }

    }

}
