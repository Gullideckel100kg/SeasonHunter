package gullideckel.seasonhunter.Message;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import gullideckel.seasonhunter.Objects.GeneralMessage.GeneralMessage;
import gullideckel.seasonhunter.R;

public class ShowGeneralMessage
{
    private static final String TAG = "ShowGeneralMessage";

    private int count = 0;
    private FirebaseFirestore db;
    private Context context;
    private String uId;

    public ShowGeneralMessage(Context context)
    {
        this.context = context;
        db = FirebaseFirestore.getInstance();
        uId = FirebaseAuth.getInstance().getUid();
    }

    //TODO Dont show messgas during advertising
    public void ShowMessages(List<GeneralMessage> messages, int position)
    {
        for(GeneralMessage message : messages)
        {
            for(String user : message.getUserNotShown())
            {
                if(user.equals(uId))
                    return;
            }
            if(message.getPosition() == position)
                ShowDialog(message);
        }
    }

    private void ShowDialog(final GeneralMessage message)
    {
        View checkBoxView = View.inflate(context, R.layout.view_checkbox_for_dialog, null);
        final CheckBox chkDontShow = (CheckBox) checkBoxView.findViewById(R.id.chkMesDontShow);

        chkDontShow.setText(context.getString(R.string.mes_dont_show));

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(message.getTitle());
        builder.setMessage(message.getMessage())
                .setView(checkBoxView)
                .setPositiveButton(context.getText(R.string.done), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(chkDontShow.isChecked())
                        {
                            List<String> userNotShown = message.getUserNotShown();
                            if(userNotShown.contains(uId))
                                Log.wtf(TAG, "onClick: User should not be in the userNotShow list");
                            else
                                userNotShown.add(uId);
                            db.collection(context.getString(R.string.db_messages)).document(message.getId())
                                    .update("userNotShown", userNotShown).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if(task.isSuccessful())
                                        Log.i(TAG, "onComplete: Firestore update successfull. Message wont pop up anymore");
                                    else
                                        Log.e(TAG, "onComplete: update failed", task.getException());
                                }

                            });
                        }
                        dialog.dismiss();
                    }
                }).show();
    }
}
