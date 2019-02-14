package gullideckel.seasonhunter.DocumentsFirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.Interfaces.IDocumentList;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.Objects.JobInformation.CompanyDocument;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;

public class LoadAllDocuments
{
    private static final String TAG = "LoadAllDocuments";

    private Context context;
    private IDocumentList listener;

    public  LoadAllDocuments(Context context, IDocumentList listener)
    {
        this.context = context;
        this.listener = listener;
    }

    public void InitDocuments(int countryPart)
    {
        switch (countryPart)
        {
            case -1:
                LoadDocuments(context.getString(R.string.all_companies));
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
                        List<CompanyDocument> docs = new ArrayList<>();
                        for(DocumentSnapshot document : task.getResult())
                        {
                            try
                            {
                                docs.add(document.toObject(CompanyDocument.class));
                            }catch (ClassCastException e)
                            {
                                Log.e(TAG, "onComplete: ", e);
                            }
                        }
                        listener.recievedDocuments(docs);
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
