package gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub;

import java.util.List;

public class CompanyAdditionalInfoObject
{

    private List<String> mLstProducts;
    private String mStrCompanyDescription;

    public CompanyAdditionalInfoObject(List<String> products, String companyDescription)
    {
        mLstProducts = products;
        mStrCompanyDescription = companyDescription;
    }

    public List<String> GetProducts()
    {
        return mLstProducts;
    }

    public String GetCompanyDescription()
    {
        return  mStrCompanyDescription;
    }

}
