package gullideckel.seasonhunter.CompanyInfo.CompanyInfoPages.CompanyReviews;

import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.Objects.Review.ReviewStarRating;
import gullideckel.seasonhunter.R;

public class StarRating
{
    private static final String TAG = "StarRating";

    private List<ImageView> lstEarnings;
    private List<ImageView> lstAtmosphere;
    private List<ImageView> lstOrganistion;

    public StarRating(View v)
    {
        lstEarnings = new ArrayList<>();

        lstEarnings.add((ImageView) v.findViewById(R.id.imgEarningsStarOne));
        lstEarnings.add((ImageView) v.findViewById(R.id.imgEarningsStarTwo));
        lstEarnings.add((ImageView) v.findViewById(R.id.imgEarningsStarThree));
        lstEarnings.add((ImageView) v.findViewById(R.id.imgEarningsStarFour));
        lstEarnings.add((ImageView) v.findViewById(R.id.imgEarningsStarFive));

        lstAtmosphere = new ArrayList<>();

        lstAtmosphere.add((ImageView) v.findViewById(R.id.imgAtmosphereStarOne));
        lstAtmosphere.add((ImageView) v.findViewById(R.id.imgAtmosphereStarTwo));
        lstAtmosphere.add((ImageView) v.findViewById(R.id.imgAtmosphereStarThree));
        lstAtmosphere.add((ImageView) v.findViewById(R.id.imgAtmosphereStarFour));
        lstAtmosphere.add((ImageView) v.findViewById(R.id.imgAtmosphereStarFive));

        lstOrganistion = new ArrayList<>();

        lstOrganistion.add((ImageView) v.findViewById(R.id.imgOrganicationStarOne));
        lstOrganistion.add((ImageView) v.findViewById(R.id.imgOrganicationStarTwo));
        lstOrganistion.add((ImageView) v.findViewById(R.id.imgOrganicationStarThree));
        lstOrganistion.add((ImageView) v.findViewById(R.id.imgOrganicationStarFour));
        lstOrganistion.add((ImageView) v.findViewById(R.id.imgOrganicationStarFive));
    }

    //Star count starts from 0 to 4 (5 stars)
    public void SetStars(ReviewStarRating stars)
    {
        if(stars.getEarnings() < 5)
            for(int i = 0; i <= stars.getEarnings(); i++)
                lstEarnings.get(i).setImageResource(R.drawable.staryellow);
        else
            Log.wtf(TAG, "SetStars Earnings: stars rating is by five and shouldn't be higher");

        if(stars.getAtmosphere() < 5)
            for(int i = 0; i <= stars.getEarnings(); i++)
                lstAtmosphere.get(i).setImageResource(R.drawable.staryellow);
        else
            Log.wtf(TAG, "SetStars Atmosphere: stars rating is by five and shouldn't be higher");

        if(stars.getOrganisation() < 5)
            for(int i = 0; i <= stars.getEarnings(); i++)
                lstOrganistion.get(i).setImageResource(R.drawable.staryellow);
        else
            Log.wtf(TAG, "SetStars Organisation: stars rating is by five and shouldn't be higher");
    }
}
