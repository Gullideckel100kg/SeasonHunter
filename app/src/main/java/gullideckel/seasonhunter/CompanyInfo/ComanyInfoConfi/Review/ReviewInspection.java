package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Review;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import gullideckel.seasonhunter.Objects.Review.Review;
import gullideckel.seasonhunter.R;

public class ReviewInspection
{
    private String msgText = "";
    private Context context;

    private TextView workPositionHead;
    private TextView workTimeHead;
    private TextView earningsHead;
    private TextView atmosphereHead;
    private TextView organisationHead;

    public ReviewInspection(Context context, View v)
    {
        this.context = context;

        workPositionHead = v.findViewById(R.id.txtAddPositionHead);
        workTimeHead = v.findViewById(R.id.txtCalWorkTimeHead);
        earningsHead = v.findViewById(R.id.txtAddEarningsHead);
        atmosphereHead = v.findViewById(R.id.txtAddAtmosphereHead);
        organisationHead = v.findViewById(R.id.txtAddOrganisationHead);
    }

    public boolean Inspect(Review review)
    {
        boolean isCorrect = true;
        StringBuilder b = new StringBuilder();

        if(review.getWorkPosition().isEmpty()){
            BuildString(b, context.getString(R.string.no_work_position));
            isCorrect =false;
            workPositionHead.setTextColor(Color.RED);
        }else {
            workPositionHead.setTextColor(Color.BLACK);
        }

        if(review.getWorkTime() == null){
            BuildString(b, context.getString(R.string.no_work_time));
            isCorrect = false;
            workTimeHead.setTextColor(Color.RED);
        } else {
            workTimeHead.setTextColor(Color.BLACK);
        }

        if(review.getStarRating().getEarnings() == -1){
            BuildString(b, context.getString(R.string.no_rate_earnings));
            isCorrect = false;
            earningsHead.setTextColor(Color.RED);
        } else {
            earningsHead.setTextColor(Color.BLACK);
        }

        if(review.getStarRating().getAtmosphere() == -1){
            BuildString(b, context.getString(R.string.no_rate_atmosphere));
            isCorrect = false;
            atmosphereHead.setTextColor(Color.RED);
        } else {
            atmosphereHead.setTextColor(Color.BLACK);
        }

        if(review.getStarRating().getOrganisation() == -1){
            BuildString(b, context.getString(R.string.no_rate_organisation));
            isCorrect = false;
            organisationHead.setTextColor(Color.RED);
        } else {
            organisationHead.setTextColor(Color.BLACK);
        }

        if(!isCorrect){
            b.append("\n" + context.getString(R.string.fill_fields_review));
            msgText = b.toString();
        }

        return isCorrect;
    }

    public String getMessageWrong()
    {
        return msgText;
    }

    public String getMessageCorrect(Review review)
    {
        StringBuilder b = new StringBuilder();
        if(review.getSalary().isEmpty())
            BuildString(b, context.getString(R.string.no_wage_rate));

        if(review.getComment().isEmpty())
            BuildString(b, context.getString(R.string.no_comment));

        BuildString(b, context.getString(R.string.quest_send_review));

        return b.toString();
    }

    private void BuildString(StringBuilder b, String text)
    {
        if(b.toString().isEmpty())
            b.append(text);
        else
        {
            b.append("\n");
            b.append(text);
        }
    }

}
