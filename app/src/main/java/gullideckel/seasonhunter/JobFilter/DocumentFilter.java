package gullideckel.seasonhunter.JobFilter;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class DocumentFilter
{
    private static final String TAG = "DocumentFilter";

    private List<CompanyDocument> docs;
    private Context context;

    public DocumentFilter(List<CompanyDocument> docs, Context context)
    {
        this.docs = docs;
        this.context = context;
    }

    public List<CompanyDocument> filterDocs(FilterObject filter)
    {
        List<List<CompanyDocument>> filteredDocs = new ArrayList<>();
        List<CompanyDocument> returnDocs = new ArrayList<>();

        if(!filter.getKeywordJob().isEmpty())
            filteredDocs.add(docsJob(filter.getKeywordJob()));

        if(!filter.getStartMonth().isEmpty() && !filter.getEndMonth().isEmpty())
            filteredDocs.add(docsDate(filter.getStartMonth(), filter.getEndMonth()));

        if(filter.getTypes() != null && filter.getTypes().size() > 0)
            filteredDocs.add(docsType(filter.getTypes()));

        if(filter.isPhone())
            filteredDocs.add(docsPhone());

        if(filter.isEmail())
            filteredDocs.add(docsEmail());

        if(filter.isWebSite())
            filteredDocs.add(docsWebSite());

        if(filter.isFacilities())
            filteredDocs.add(docsFacilities(filter.getKeywordFacilities()));

        if(filteredDocs.size() > 0)
        {
            returnDocs = filteredDocs.get(0);
            if(filteredDocs.size() > 1)
            {

                for(int i = 1; i < filteredDocs.size(); i++)
                {
                    List<CompanyDocument> newDocs = new ArrayList<>();
                    for(CompanyDocument doc : filteredDocs.get(i))
                    {
                        if(returnDocs.contains(doc))
                            newDocs.add(doc);
                    }
                    returnDocs = newDocs;
                }
            }

        }

        return returnDocs;
    }

    private List<CompanyDocument> docsJob(String keyword)
    {
        List<CompanyDocument> jobDocs = new ArrayList<>();

        for(CompanyDocument doc : docs)
            for(CompanyJobs.CompanyJob job : doc.getJobs().getCompanyJobs())
                if(job.getJobTitle().contains(keyword))
                    jobDocs.add(doc);

        return jobDocs;
    }

    private List<CompanyDocument> docsType(List<String> types)
    {
        List<CompanyDocument> docsType = new ArrayList<>();

        for(CompanyDocument doc : docs)
        {
            for(String typeDoc : doc.getTypes())
            {
                for(String type : types)
                {
                    if(typeDoc.equals(type))
                        if(!docsType.contains(doc))
                            docsType.add(doc);
                }
            }
        }

        return docsType;
    }

    private List<CompanyDocument> docsPhone()
    {
        List<CompanyDocument> phoneDoc = new ArrayList<>();

        for(CompanyDocument doc : docs)
            if(doc.getContact().getPhoneNumber() != null && doc.getContact().getPhoneNumber().size() > 0)
                phoneDoc.add(doc);

        return  phoneDoc;
    }

    private List<CompanyDocument> docsEmail()
    {
        List<CompanyDocument> emailDocs = new ArrayList<>();

        for(CompanyDocument doc : docs)
            if(doc.getContact().getEmail() != null && doc.getContact().getEmail().size() > 0)
                emailDocs.add(doc);


        return emailDocs;
    }

    private List<CompanyDocument> docsWebSite()
    {
        List<CompanyDocument> docsWebsite = new ArrayList<>();

        for(CompanyDocument doc : docs)
            if(doc.getContact().getWebsite() != null && !doc.getContact().getWebsite().isEmpty())
                docsWebsite.add(doc);

        return docsWebsite;
    }

    private List<CompanyDocument> docsFacilities(String facilites)
    {
        List<CompanyDocument> docsFalc = new ArrayList<>();

        for(CompanyDocument doc : docs)
            if(doc.getExtras().getFacilities() != null && !doc.getExtras().getFacilities().isEmpty())
                if(!facilites.isEmpty())
                    if(doc.getExtras().getFacilities().contains(facilites))
                        docsFalc.add(doc);
                else
                    docsFalc.add(doc);

        return  docsFalc;
    }

    private List<CompanyDocument> docsDate(String startMonth, String endMonth)
    {
        List<CompanyDocument> dateDocs = new ArrayList<>();

        Date monthOne = StaticMethod.getMonthFromStringDate(startMonth, context);
        Date monthTwo = StaticMethod.getMonthFromStringDate(endMonth, context);

        if(startMonth != null && endMonth != null)
            for(CompanyDocument doc : docs)
                for(CompanyJobs.CompanyJob job : doc.getJobs().getCompanyJobs())
                {
                    Date jobOne = StaticMethod.getMonthFromStringDate(job.getStartDate(), context);
                    Date jobTwo = StaticMethod.getMonthFromStringDate(job.getEndDate(), context);

                    try
                    {
                        if(monthOne.equals(jobOne) || monthOne.before(jobOne) && monthTwo.equals(jobTwo) || monthTwo.after(jobTwo))
                        {
                            if(!dateDocs.contains(doc))
                                dateDocs.add(doc);
                        }
                    }catch (NullPointerException e)
                    {
                        Log.wtf(TAG, "docsDate: Could not convert Month. Month has wrong format or written mistake in the database", e);

                    }
                }

        return dateDocs;
    }
}
