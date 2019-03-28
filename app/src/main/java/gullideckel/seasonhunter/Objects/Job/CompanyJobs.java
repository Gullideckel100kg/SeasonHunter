package gullideckel.seasonhunter.Objects.Job;

import java.util.ArrayList;
import java.util.List;

public class CompanyJobs
{
    private List<CompanyJob> companyJobs;

    public CompanyJobs()
    {
        companyJobs = new ArrayList<>();
    }

    public List<CompanyJob> getCompanyJobs()
    {
        return companyJobs;
    }

    public void setCompanyJobs(List<CompanyJob> companyJobs)
    {
        this.companyJobs = companyJobs;
    }

    public void addCompanyJob(CompanyJob job) { companyJobs.add(job); }

    public static class CompanyJob
    {
        private String jobTitle;

        private String startDate;
        private String endDate;
        private String notes = "";

        private String fullDate;

        public String getFullDate()
        {
            return fullDate;
        }

        public void setFullDate(String fullDate)
        {
            this.fullDate = fullDate;
        }

        public String getStartDate()
        {
            return startDate;
        }

        public void setStartDate(String startDate)
        {
            this.startDate = startDate;
        }

        public String getEndDate()
        {
            return endDate;
        }

        public void setEndDate(String endDate)
        {
            this.endDate = endDate;
        }

        public String getNotes()
        {
            return notes;
        }

        public void setNotes(String notes)
        {
            this.notes = notes;
        }



        public String getJobTitle()
        {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle)
        {
            this.jobTitle = jobTitle;
        }
    }

}
