package gullideckel.seasonhunter.Objects.JobInformation;

import java.util.List;

public class CompanyDocument
{
    private String name;
    private String type;
    private CompanyAddress address;
    private CompanyContact contact;
    private CompanyJobs jobs;
    private List<CompanyBenefits.CompanyBenefit> benefits;

    public CompanyDocument() {}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public CompanyAddress getAddress()
    {
        return address;
    }

    public void setAddress(CompanyAddress address)
    {
        this.address = address;
    }

    public CompanyContact getContact()
    {
        return contact;
    }

    public void setContact(CompanyContact contact)
    {
        this.contact = contact;
    }

    public CompanyJobs getJobs()
    {
        return jobs;
    }

    public void setJobs(CompanyJobs jobs)
    {
        this.jobs = jobs;
    }

    public List<CompanyBenefits.CompanyBenefit> getBenefits()
    {
        return benefits;
    }

    public void setBenefits(List<CompanyBenefits.CompanyBenefit> benefits)
    {
        this.benefits = benefits;
    }
}
