package gullideckel.seasonhunter.Firestore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.Interfaces.IDocumentList;
import gullideckel.seasonhunter.Interfaces.IReviewList;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Review.CompanyReview;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class LoadAllReviews
{
    private static final String TAG = "LoadAllReviews";

    private Context context;
    private IReviewList listener;

    public  LoadAllReviews(Context context, IReviewList listener)
    {
        this.context = context;
        this.listener = listener;
    }

    public void InitDocuments(int countryPart)
    {
        switch (countryPart)
        {
            case -1:
                LoadDocuments(context.getString(R.string.all_reviews));
                break;
        }
    }

    private void LoadDocuments(String collectionPath)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(collectionPath).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                List<CompanyReview> reviews = new ArrayList<>();
                for(DocumentSnapshot document : task.getResult())
                {
                    try
                    {
                        CompanyReview doc = document.toObject(CompanyReview.class);
                        doc.setId(document.getId());
                        reviews.add(doc);
                    }catch (ClassCastException e)
                    {
                        Log.e(TAG, "onComplete: ", e);
                    }
                }
                listener.RecieveReviews(reviews);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Log.w(TAG, "onFailure: Error getting documents. ", e);
                StaticMethod.Toast(context.getString(R.string.error_loading_firestore), context);
            }
        });
    }
}
