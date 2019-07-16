package gullideckel.seasonhunter.CompanyInfo.Share;

import android.content.Context;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;

public class ShareString
{
    public static String GetText(CompanyDocument doc, Context context)
    {
        StringBuilder b = new StringBuilder();

        b.append(context.getString(R.string.share_name) + " ");
        b.append(doc.getName() + "\n\n");

        if(doc.getTypes() != null && doc.getTypes().size() > 0)
        {
            b.append(context.getString(R.string.share_type) + " ");

            b.append(doc.getTypes().get(0));

            if(doc.getTypes().size() > 1)
            {
                for(int i = 1; i < doc.getTypes().size(); i++)
                {
                    b.append(", " + doc.getTypes().get(i));
                }
            }

            b.append("\n\n");
        }
        else if (!doc.getType().isEmpty())
        {
            b.append(context.getString(R.string.share_type) + " " + doc.getType() + "\n\n");
        }

        b.append(context.getString(R.string.share_address) + " " + doc.getAddress().getAddress() + "\n\n");

        b.append(context.getString(R.string.share_contacts) + " " + "\n");

        if(doc.getContact().getEmail().size() > 0)
        {
            b.append(context.getString(R.string.share_email) + " " + doc.getContact().getEmail().get(0));

            if(doc.getContact().getEmail().size() > 1)
            {
                for(int i = 1; i < doc.getContact().getEmail().size(); i++)
                {
                    b.append(", " + doc.getContact().getEmail().get(i));
                }
            }
            b.append("\n");
        }

        if(doc.getContact().getPhoneNumber().size() > 0)
        {
            b.append(context.getString(R.string.share_phone) + " " + doc.getContact().getPhoneNumber().get(0));

            if(doc.getContact().getPhoneNumber().size() > 1)
            {
                for(int i = 1; i < doc.getContact().getPhoneNumber().size(); i++)
                {
                    b.append(", " + doc.getContact().getPhoneNumber().get(i));
                }
            }
            b.append("\n");
        }

        if(!doc.getContact().getWebsite().isEmpty())
        {
            b.append(context.getString(R.string.share_website) + " " + doc.getContact().getWebsite());
            if(doc.getContact().isOnlineRecruitment())
                b.append(" " + context.getString(R.string.share_online_recruitment));
            b.append("\n");
        }

        b.append("\n");

        if(doc.getJobs().getCompanyJobs().size() > 0)
        {
            b.append(context.getString(R.string.share_jobs) + "\n");

            for(CompanyJobs.CompanyJob job : doc.getJobs().getCompanyJobs())
            {
                b.append("\n");
                b.append(context.getString(R.string.share_job_title) + " " + job.getJobTitle() + "\n");
                b.append(context.getString(R.string.share_job_date) + " " + job.getStartDate() + " - " + job.getEndDate() + "\n");
                if(!job.getNotes().isEmpty())
                    b.append(context.getString(R.string.share_job_notes) + " " + job.getNotes() + "\n");
            }
        }

        if(!doc.getExtras().getCourses().isEmpty())
            b.append("\n" + context.getString(R.string.share_courses) + " " + doc.getExtras().getCourses() + "\n");

        if(!doc.getExtras().getFacilities().isEmpty())
            b.append("\n" + context.getString(R.string.share_facilities) + " " + doc.getExtras().getFacilities() + "\n");

        if(!doc.getExtras().getDescription().isEmpty())
            b.append("\n" + context.getString(R.string.share_description) + " " + doc.getExtras().getDescription() + "\n");

        b.append("\n" + context.getString(R.string.app_name) + "\n" + context.getString(R.string.app_store_link));

        return b.toString();
    }
}
