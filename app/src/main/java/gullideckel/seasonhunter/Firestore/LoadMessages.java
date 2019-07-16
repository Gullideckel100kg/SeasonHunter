package gullideckel.seasonhunter.Firestore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.Message.ShowGeneralMessage;
import gullideckel.seasonhunter.Objects.GeneralMessage.GeneralMessage;
import gullideckel.seasonhunter.R;

public class LoadMessages
{
    private static final String TAG = "LoadMessages";

    private FirebaseFirestore db;
    private Context context;
    private List<GeneralMessage> messages;

    public LoadMessages(Context context)
    {
        db = FirebaseFirestore.getInstance();
        this.context = context;
        messages = new ArrayList<>();
    }

    //TODO now I show just messages at the start menu. It has to be changed soon so I can show messages at any screen
    public void Load()
    {
        db.collection(context.getString(R.string.db_messages)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                for(QueryDocumentSnapshot document : task.getResult())
                {
                    try
                    {
                        GeneralMessage message = document.toObject(GeneralMessage.class);
                        message.setId(document.getId());
                        messages.add(message);
                    }catch (ClassCastException e)
                    {
                        Log.e(TAG, "onComplete: ", e);
                    }
                }
                ShowGeneralMessage showMessages = new ShowGeneralMessage(context);
                showMessages.ShowMessages(messages, 0);
            }
        });

//                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task)
//            {
//                if(task.isSuccessful())
//                {
//                    for(QueryDocumentSnapshot document : task.getResult())
//                    {
//                        try
//                        {
//                            GeneralMessage message = document.toObject(GeneralMessage.class);
//                            message.setId(document.getId());
//                            messages.add(message);
//                        }catch (ClassCastException e)
//                        {
//                            Log.e(TAG, "onComplete: ", e);
//                        }
//                    }
//                    ShowGeneralMessage showMessages = new ShowGeneralMessage(context);
//                    showMessages.ShowMessages(messages, 0);
//                }
//                else
//                    Log.e(TAG, "onComplete: ", task.getException());
//            }
//        });
    }


}
