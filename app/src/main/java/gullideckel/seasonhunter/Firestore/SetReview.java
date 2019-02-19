package gullideckel.seasonhunter.Firestore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Review.Review;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class SetReview
{
    private static final String TAG = "SetReview";

    private Context context;
    private FirebaseFirestore db;

    public SetReview(Context context, FirebaseFirestore db)
    {
        this.context = context;
        this.db = db;
    }

    //TODO: Save Review in an extra collection so it can be checked before publishing. Has to build in in the admin tool
    public void Set(CompanyDocument doc)
    {
        db = FirebaseFirestore.getInstance();
        db.collection(context.getString(R.string.db_all_companies)).document(doc.getId()).set(doc)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        Log.d(TAG, "onSuccess: Review has been sent successfull");
                        StaticMethod.Toast(context.getString(R.string.review_sent), context);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Log.e(TAG, "onFailure: Review hasn't been sent successfull, ", e);
                StaticMethod.Toast(context.getString(R.string.review_not_sent), context);
            }
        });
    }
}
