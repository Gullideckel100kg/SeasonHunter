package gullideckel.seasonhunter.Interfaces;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Review.Review;

public interface IDocumentReview
{
    void onRecieveReview(Review review);
}
