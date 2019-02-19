package gullideckel.seasonhunter.Objects.Review;

import com.google.firebase.firestore.Exclude;

public class Review
{
    private String id;
    private String uid = "";
    private String workPosition = "";
    private ReviewWorkTime workTime;
    private int wage;
    private String salary = "";
    private String otherPayment = "";
    private ReviewStarRating starRating;
    private String comment = "";

    @Exclude
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getWorkPosition()
    {
        return workPosition;
    }

    public void setWorkPosition(String workPosition)
    {
        this.workPosition = workPosition;
    }

    public ReviewWorkTime getWorkTime()
    {
        return workTime;
    }

    public void setWorkTime(ReviewWorkTime workTime)
    {
        this.workTime = workTime;
    }

    public int getWage()
    {
        return wage;
    }

    public void setWage(int wage)
    {
        this.wage = wage;
    }

    public String getSalary()
    {
        return salary;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    public String getOtherPayment()
    {
        return otherPayment;
    }

    public void setOtherPayment(String otherPayment)
    {
        this.otherPayment = otherPayment;
    }

    public ReviewStarRating getStarRating()
    {
        return starRating;
    }

    public void setStarRating(ReviewStarRating starRating)
    {
        this.starRating = starRating;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
