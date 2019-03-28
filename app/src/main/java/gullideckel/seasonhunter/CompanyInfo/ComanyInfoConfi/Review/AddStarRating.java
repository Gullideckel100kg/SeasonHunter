package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Review;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.Objects.Review.ReviewStarRating;
import gullideckel.seasonhunter.R;

public class AddStarRating
{
    private List<ImageView> lstEarnings;
    private List<ImageView> lstAtmosphere;
    private List<ImageView> lstOrganisation;

    private Context context;
    private int starsEarnings = -1;
    private int starsAtmosphere = -1;
    private int starsOrganisation = -1;


    public AddStarRating(View v, Context context)
    {
        this.context = context;

        lstEarnings = new ArrayList<>();

        lstEarnings.add((ImageView) v.findViewById(R.id.imgAddEarningsOne));
        lstEarnings.add((ImageView) v.findViewById(R.id.imgAddEarningsTwo));
        lstEarnings.add((ImageView) v.findViewById(R.id.imgAddEarningsThree));
        lstEarnings.add((ImageView) v.findViewById(R.id.imgAddEarningsFour));
        lstEarnings.add((ImageView) v.findViewById(R.id.imgAddEarningsFive));

        lstAtmosphere = new ArrayList<>();

        lstAtmosphere.add((ImageView) v.findViewById(R.id.imgAddAtmosphereOne));
        lstAtmosphere.add((ImageView) v.findViewById(R.id.imgAddAtmosphereTwo));
        lstAtmosphere.add((ImageView) v.findViewById(R.id.imgAddAtmosphereThree));
        lstAtmosphere.add((ImageView) v.findViewById(R.id.imgAddAtmosphereFour));
        lstAtmosphere.add((ImageView) v.findViewById(R.id.imgAddAtmosphereFive));

        lstOrganisation = new ArrayList<>();

        lstOrganisation.add((ImageView) v.findViewById(R.id.imgAddOrganisationOne));
        lstOrganisation.add((ImageView) v.findViewById(R.id.imgAddOrganisationTwo));
        lstOrganisation.add((ImageView) v.findViewById(R.id.imgAddOrganisationThree));
        lstOrganisation.add((ImageView) v.findViewById(R.id.imgAddOrganisationFour));
        lstOrganisation.add((ImageView) v.findViewById(R.id.imgAddOrganisationFive));
    }

    public void Init()
    {
        for(ImageView imgEarnings : lstEarnings)
            imgEarnings.setOnClickListener(Earnings);

        for(ImageView imgAtmosphere : lstAtmosphere)
            imgAtmosphere.setOnClickListener(Atmosphere);

        for(ImageView imgOrganisation : lstOrganisation)
            imgOrganisation.setOnClickListener(Organisation);
    }

    private View.OnClickListener Earnings = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            int stars = lstEarnings.indexOf(v);
            SetStarsValue(stars, lstEarnings);
            starsEarnings = stars;
        }
    };

    private View.OnClickListener Atmosphere = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            int stars = lstAtmosphere.indexOf(v);
            SetStarsValue(stars, lstAtmosphere);
            starsAtmosphere = stars;
        }
    };

    private View.OnClickListener Organisation = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            int stars = lstOrganisation.indexOf(v);
            SetStarsValue(stars, lstOrganisation);
            starsOrganisation = stars;
        }
    };

    private void SetStarsValue(int stars, List<ImageView> lstStars)
    {
        for(int i = 0; i < lstStars.size(); i++)
        {
            if(lstStars.get(i).getDrawable().equals(ResourcesCompat.getDrawable(context.getResources(), R.drawable.staryellow, null)))
            {
                if(i < stars)
                    lstStars.get(i).setImageResource(R.drawable.staryellow);
                else
                    lstStars.get(i).setImageResource(R.drawable.stargrey);
            }
            else
            {
                if(i <= stars)
                    lstStars.get(i).setImageResource(R.drawable.staryellow);
                else
                    lstStars.get(i).setImageResource(R.drawable.stargrey);
            }

        }
    }

    public ReviewStarRating getStars()
    {
        ReviewStarRating starRating = new ReviewStarRating();

        starRating.setEarnings(starsEarnings);
        starRating.setAtmosphere(starsAtmosphere);
        starRating.setOrganisation(starsOrganisation);

        return starRating;
    }
}
