package gullideckel.seasonhunter.Firestore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import gullideckel.seasonhunter.Interfaces.IUserInfo;
import gullideckel.seasonhunter.Objects.User.UserInfo;
import gullideckel.seasonhunter.R;

public class ReadUserInfo
{
    private static final String TAG = "ReadUserInfo";
    private FirebaseFirestore db;
    private String uId;
    private Context context;
    private IUserInfo listener;

    public ReadUserInfo(Context context, IUserInfo listener)
    {
        db = FirebaseFirestore.getInstance();
        uId = FirebaseAuth.getInstance().getUid();
        this.context = context;
        this.listener = listener;
    }

    public void ReadUserInfo()
    {
        db.collection(context.getString(R.string.db_user_info)).document(uId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if(task.isSuccessful())
                {
                    UserInfo userInfo = task.getResult().toObject(UserInfo.class);
                    String id = task.getResult().getId();
                    listener.OnUserInfo(userInfo);
                }
                else
                {
                    Log.wtf(TAG, "Error getting user information: ", task.getException());
                }
            }
        });
    }
}
