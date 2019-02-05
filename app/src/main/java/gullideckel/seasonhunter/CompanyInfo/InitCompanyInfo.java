package gullideckel.seasonhunter.CompanyInfo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import gullideckel.seasonhunter.Objects.JobInformation.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class InitCompanyInfo
{
    private Context context;

    private TextView txtName;
    private TextView txtAddress;
    private TextView txtPhone;
    private TextView txtEmail;
    private TextView txtWebsite;
    private RecyclerView rcylJobs;
    private TextView txtWorkFeatures;

    public InitCompanyInfo(View v, Context context)
    {
        this.context = context;

        txtName = (TextView) v.findViewById(R.id.txtInfoName);
        txtAddress = (TextView) v.findViewById(R.id.txtInfoAddress);
        txtPhone = (TextView) v.findViewById(R.id.txtInfoPhone);
        txtEmail = (TextView) v.findViewById(R.id.txtInfoEmail);
        txtWebsite = (TextView) v.findViewById(R.id.txtInfoWebsite);
        rcylJobs = (RecyclerView) v.findViewById(R.id.rcylInfoJobs);
        txtWorkFeatures = (TextView) v.findViewById(R.id.txtInfoFeatures);
    }

    public void Init(CompanyDocument doc)
    {

        txtName.setText(doc.getName());
        txtAddress.setText( doc.getAddress().getAddress() + "\n" +
                            StaticMethod.GPSConvert(doc.getAddress().getLatitude(), doc.getAddress().getLongitude()));

        if(doc.getContact().getPhoneNumber().size() > 0)
            txtPhone.setText(ContactBuilder(doc.getContact().getPhoneNumber()));
        else
            txtPhone.setText(context.getText(R.string.no_phone));

        if(doc.getContact().getEmail().size() > 0)
            txtEmail.setText(ContactBuilder(doc.getContact().getEmail()));
        else
            txtEmail.setText(context.getText(R.string.no_email));

        if(doc.getContact().getWebsite().isEmpty())
            txtWebsite.setText(context.getText(R.string.no_website));
        else
        {
            if(doc.getContact().isOnlineRecruitment())
                txtWebsite.setText(doc.getContact().getWebsite() + "(" + context.getText(R.string.online_recruitment) + ")");
            else
                txtWebsite.setText(doc.getContact().getWebsite());
        }

        rcylJobs.setLayoutManager(new LinearLayoutManager(context));
        rcylJobs.setAdapter(new JobsAdapter(doc.getJobs().getCompanyJobs(), context));

//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < doc.getBenefits().size(); i++)
//        {
//            sb.append(doc.getBenefits().get(i).getBenefit());
//            if(doc.getBenefits().size() > 0 && i < doc.getBenefits().size() - 1)
//                sb.append(", ");
//        }
        txtWorkFeatures.setText(doc.getFeatures());
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
