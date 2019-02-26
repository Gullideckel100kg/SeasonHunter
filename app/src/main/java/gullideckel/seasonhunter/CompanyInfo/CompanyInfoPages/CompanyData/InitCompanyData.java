package gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.CompanyData;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;
import gullideckel.seasonhunter.Statics.StaticTypes;

public class InitCompanyData
{
    private static final String TAG = "InitCompanyData";

    private Activity activity;

    private TextView txtName;
    private ImageView imgType;
    private TextView txtType;
    private TextView txtAddress;
    private LinearLayout linContact;
    private LinearLayout linJobs;
    private LinearLayout linExtras;

    public InitCompanyData(View v, Activity activity)
    {
        this.activity = activity;

        txtName = (TextView) v.findViewById(R.id.txtDetailsName);
        imgType = (ImageView) v.findViewById(R.id.imgDetailsType);
        txtType = (TextView) v.findViewById(R.id.txtDetailsType);
        txtAddress = (TextView) v.findViewById(R.id.txtDetailsAddress);
        linContact = (LinearLayout) v.findViewById(R.id.linContact);
        linJobs = (LinearLayout) v.findViewById(R.id.linJobs);
        linExtras = (LinearLayout) v.findViewById(R.id.linExtras);
    }

    public void Init(CompanyDocument doc)
    {
        txtName.setText(doc.getName());

        if(doc.getTypes().size() > 0)
        {
            imgType.setImageBitmap(StaticTypes.getLogo(doc.getTypes().get(0), activity));
            txtType.setText(StaticMethod.StringBuilder(doc.getTypes(), " + "));
        }
        else
        {
            imgType.setImageBitmap(StaticTypes.getLogo(activity.getString(R.string.other), activity));
            txtType.setText(activity.getText(R.string.other));
            Log.wtf(TAG, "Init: No CompanyType availibe \n Document: " + doc.getId());
        }


        txtAddress.setText(doc.getAddress().getAddress());

        final Uri geoLocation = Uri.parse("geo:" + doc.getAddress().getLatitude() + "," + doc.getAddress().getLongitude());

        txtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(geoLocation);
                if (intent.resolveActivity(activity.getPackageManager()) != null) {
                    activity.startActivity(intent);
                }
            }
        });

        LayoutInflater inflater = activity.getLayoutInflater();

        InitCompanyContacts contacts = new InitCompanyContacts(inflater, activity);
        contacts.InitContact(linContact, doc.getContact());

        InitCompanyJobs initJobs = new InitCompanyJobs(inflater);
        initJobs.InitJobs(linJobs, doc.getJobs());

        InitCompanyExtras initExtras = new InitCompanyExtras(inflater, activity);
        initExtras.InitExtras(linExtras, doc.getExtras());
    }





}
