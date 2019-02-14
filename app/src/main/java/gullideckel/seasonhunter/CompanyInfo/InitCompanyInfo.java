package gullideckel.seasonhunter.CompanyInfo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import gullideckel.seasonhunter.Objects.JobInformation.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class InitCompanyInfo
{
    private Context context;

    private TextView txtName;
    private TextView txtAddress;

    private TextView txtPhoneHeader;
    private TextView txtPhone;

    private TextView txtEmailHeader;
    private TextView txtEmail;

    private TextView txtWebsiteHeader;
    private TextView txtWebsite;
    private RecyclerView rcylJobs;

    private TextView txtFacilitesHeader;
    private TextView txtFacilites;

    private TextView txtCoursesHeader;
    private TextView txtCourses;

    private TextView txtDescriptionHeader;
    private TextView txtDescription;

    public InitCompanyInfo(View v, Context context)
    {
        this.context = context;

//        txtName = (TextView) v.findViewById(R.id.txtInfoName);
//        txtAddress = (TextView) v.findViewById(R.id.txtInfoAddress);
//
//        txtPhoneHeader =(TextView) v.findViewById(R.id.txtInfoPhoneHead);
//        txtPhone = (TextView) v.findViewById(R.id.txtInfoPhone);
//
//        txtEmailHeader = (TextView) v.findViewById(R.id.txtInfoEmailHead);
//        txtEmail = (TextView) v.findViewById(R.id.txtInfoEmail);
//
//        txtWebsiteHeader = (TextView) v.findViewById(R.id.txtWebiteHead);
//        txtWebsite = (TextView) v.findViewById(R.id.txtInfoWebsite);
//
//        rcylJobs = (RecyclerView) v.findViewById(R.id.rcylInfoJobs);
//
//        txtFacilitesHeader = (TextView) v.findViewById(R.id.txtInfoFacilitiesHeader);
//        txtFacilites = (TextView) v.findViewById(R.id.txtInfoFacilities);
//
//        txtCoursesHeader = (TextView) v.findViewById(R.id.txtInfoCoursesHeader);
//        txtCourses = (TextView) v.findViewById(R.id.txtInfoCourses);
//
//        txtDescriptionHeader = (TextView) v.findViewById(R.id.txtInfoDescriptionHeader);
//        txtDescription = (TextView) v.findViewById(R.id.txtInfoDescription);
    }

    public void Init(CompanyDocument doc)
    {
        txtName.setText(doc.getName());
        txtAddress.setText( doc.getAddress().getAddress() + "\n" +
                            StaticMethod.GPSConvert(doc.getAddress().getLatitude(), doc.getAddress().getLongitude()));

        if(doc.getContact().getPhoneNumber().size() > 0)
            txtPhone.setText(ContactBuilder(doc.getContact().getPhoneNumber()));
        else
        {
            txtPhone.setVisibility(View.GONE);
            txtPhoneHeader.setVisibility(View.GONE);
        }

        if(doc.getContact().getEmail().size() > 0)
            txtEmail.setText(ContactBuilder(doc.getContact().getEmail()));
        else
        {
            txtEmail.setVisibility(View.GONE);
            txtEmailHeader.setVisibility(View.GONE);
        }

        if(doc.getContact().getWebsite().isEmpty())
        {
            txtWebsite.setVisibility(View.GONE);
            txtWebsiteHeader.setVisibility(View.GONE);
        }
        else
        {
            if(doc.getContact().isOnlineRecruitment())
                txtWebsite.setText(doc.getContact().getWebsite() + "(" + context.getText(R.string.online_recruitment) + ")");
            else
                txtWebsite.setText(doc.getContact().getWebsite());
        }

        rcylJobs.setLayoutManager(new LinearLayoutManager(context));
        rcylJobs.setAdapter(new JobsAdapter(doc.getJobs().getCompanyJobs(), context));

        if(doc.getExtras().getFacilities().isEmpty())
        {
            txtFacilitesHeader.setVisibility(View.GONE);
            txtFacilites.setVisibility(View.GONE);
        }
        else
            txtFacilites.setText(doc.getExtras().getFacilities());

        if(doc.getExtras().getCourses().isEmpty())
        {
            txtCoursesHeader.setVisibility(View.GONE);
            txtCourses.setVisibility(View.GONE);
        }
            txtCourses.setText(doc.getExtras().getCourses());

        if(doc.getExtras().getDescription().isEmpty())
        {
            txtDescriptionHeader.setVisibility(View.GONE);
            txtDescription.setVisibility(View.GONE);
        }
            txtDescription.setText(doc.getExtras().getDescription());
    }

    private String ContactBuilder(List<String> contacts)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < contacts.size(); i++)
        {
            sb.append(contacts.get(i));
            if(i > 0 && i < contacts.size() - 1)
                sb.append("\n");
        }
        return  sb.toString();
    }
}
