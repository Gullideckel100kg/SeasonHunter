package gullideckel.seasonhunter.Objects.JobInformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.firebase.firestore.Exclude;

import gullideckel.seasonhunter.R;

public class CompanyType
{
    private String companyType;
    private Context context;

    public CompanyType(String companyType, Context context)
    {
        this.companyType = companyType;
        this.context = context;
    }

    public String getCompanyType()
    {
        return companyType;
    }

    @Exclude
    public Bitmap getLogo()
    {
        if (companyType.equals(context.getString(R.string.farm)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.farm);
        else if (companyType.equals(context.getString(R.string.orchard)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.fruit);
        else if (companyType.equals(context.getString(R.string.packhouse)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.packing);
        else if (companyType.equals(context.getString(R.string.vineyard)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.wine);
        else if (companyType.equals(context.getString(R.string.tree_planting)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.tree);
        else if (companyType.equals(context.getString(R.string.restaurant)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.chef);
        else if (companyType.equals(context.getString(R.string.factory)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.factory);
        else if (companyType.equals(context.getString(R.string.christmas)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.christmas);
        else if (companyType.equals(context.getString(R.string.resort)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.resort);
        else if (companyType.equals(context.getString(R.string.sport)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.sport);
        else if (companyType.equals(context.getString(R.string.national_park)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.nationpark);
        else if (companyType.equals(context.getString(R.string.winter_job)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.winter);
        else if (companyType.equals(context.getString(R.string.other)))
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.otherwork);

        return BitmapFactory.decodeResource(context.getResources(), R.drawable.otherwork);
    }
}
