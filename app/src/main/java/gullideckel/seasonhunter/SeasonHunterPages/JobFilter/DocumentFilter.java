package gullideckel.seasonhunter.SeasonHunterPages.JobFilter;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Job.CompanyJobs;
import gullideckel.seasonhunter.R;
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

        if(!filter.getStartMonth().isEmpty())
            filteredDocs.add(docsDate(filter.getStartMonth()));

        if(filter.getTypes() != null && filter.getTypes().size() > 0)
            filteredDocs.add(docsType(filter.getTypes()));

        if(filter.isPhone())
            filteredDocs.add(docsPhone());

        if(filter.isEmail())
            filteredDocs.add(docsEmail());

        if(filter.isWebSite())
            filteredDocs.add(docsWebSite());

        if(filter.isFacilities())
            filteredDocs.add(docsFacilities());

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

            return returnDocs;
        }
        return docs;

    }

    private List<CompanyDocument> docsJob(String keyword)
    {
        List<CompanyDocument> jobDocs = new ArrayList<>();

        for(CompanyDocument doc : docs)
            for(CompanyJobs.CompanyJob job : doc.getJobs().getCompanyJobs())
                if(job.getJobTitle().toLowerCase().contains(keyword.toLowerCase()))
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

    private List<CompanyDocument> docsFacilities()
    {
        List<CompanyDocument> docsFalc = new ArrayList<>();

        for(CompanyDocument doc : docs)
            if(doc.getExtras().getFacilities() != null && !doc.getExtras().getFacilities().isEmpty())
            {
                if(doc.getExtras().getFacilities().toLowerCase().contains("campground"))
                    docsFalc.add(doc);
//                else if(facilites.isEmpty())
//                    docsFalc.add(doc);
            }
        return  docsFalc;
    }

    private List<CompanyDocument> docsDate(String startMonth)
    {
        List<CompanyDocument> dateDocs = new ArrayList<>();
        List<String> months = Arrays.asList(context.getResources().getStringArray(R.array.months));

        if(startMonth != null)
            for(CompanyDocument doc : docs)
                for(CompanyJobs.CompanyJob job : doc.getJobs().getCompanyJobs())
                    if(isInYear(job,months, startMonth) && !dateDocs.contains(doc))
                        dateDocs.add(doc);

                    return dateDocs;
    }


    //TODO Extend filter for Start and End date
    //The time filter filters the work outside and inside the time indication
    private boolean isInYear(CompanyJobs.CompanyJob job, List<String> months, String startMonth)
    {
        int startDate = -1;

        int start =  months.indexOf(startMonth);

        for (int i = 0; i < months.size(); i++)
        {
            if (job.getStartDate().contains(months.get(i)))
                startDate = i;
        }

        if(start ==  startDate)
            return true;

        return false;
    }
}
