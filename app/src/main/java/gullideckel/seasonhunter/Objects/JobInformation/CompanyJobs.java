package gullideckel.seasonhunter.Objects.JobInformation;

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

    public static class CompanyJob
    {
        private String jobTitle;
        private boolean hourlyPaid = false;
        private boolean pieceWork = false;
        private boolean volunteering = false;


        private String startDay;
        private String startMonth;
        private String endDay;
        private String endMonth;
        private String addtionalInfo;

        public boolean isHourlyPaid()
        {
            return hourlyPaid;
        }

        public void setHourlyPaid(boolean hourlyPaid)
        {
            this.hourlyPaid = hourlyPaid;
        }

        public boolean isPieceWork()
        {
            return pieceWork;
        }

        public void setPieceWork(boolean pieceWork)
        {
            this.pieceWork = pieceWork;
        }

        public boolean isVolunteering()
        {
            return volunteering;
        }

        public void setVolunteering(boolean volunteering)
        {
            this.volunteering = volunteering;
        }

        public String getStartDay()
        {
            return startDay;
        }

        public void setStartDay(String startDay)
        {
            this.startDay = startDay;
        }

        public String getStartMonth()
        {
            return startMonth;
        }

        public void setStartMonth(String startMonth)
        {
            this.startMonth = startMonth;
        }

        public String getEndDay()
        {
            return endDay;
        }

        public void setEndDay(String endDay)
        {
            this.endDay = endDay;
        }

        public String getEndMonth()
        {
            return endMonth;
        }

        public void setEndMonth(String endMonth)
        {
            this.endMonth = endMonth;
        }

        public void setJobTitle(String jobTitle)
        {
            this.jobTitle = jobTitle;
        }

        public void setAddtionalInfo(String addtionalInfo)
        {
            this.addtionalInfo = addtionalInfo;
        }

        public String getJobTitle()
        {
            return jobTitle;
        }

        public String getAddtionalInfo()
        {
            return addtionalInfo;
        }
    }

}
