package gullideckel.seasonhunter.Firestore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import gullideckel.seasonhunter.Objects.User.Message;
import gullideckel.seasonhunter.Objects.User.UserInfo;
import gullideckel.seasonhunter.R;

public class UserMessage
{
    private static final String TAG = "UserMessage";

    private int count = 0;
    private FirebaseFirestore db;
    private Context context;
    private String uId;

    public UserMessage(Context context)
    {
        this.context = context;
        db = FirebaseFirestore.getInstance();
        uId = FirebaseAuth.getInstance().getUid();
    }

    //TODO Dont show messgas during advertising
    public void ShowMessages(List<Message> messages, int position)
    {
        if(messages != null && messages.size() > count)
        {
            if (messages.get(count).getPosition() == position && !messages.get(count).isHidden())
            {
                ShowDialog(messages, position);
            }
            else
            {
                count++;
                ShowMessages(messages, position);
            }
        }
    }

    private void ShowDialog(final List<Message> messages, final int position)
    {
        View checkBoxView = View.inflate(context, R.layout.view_checkbox_for_dialog, null);
        final CheckBox chkDontShow = (CheckBox) checkBoxView.findViewById(R.id.chkMesDontShow);

        chkDontShow.setText(context.getString(R.string.mes_dont_show));

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(messages.get(count).getTitle());
        builder.setMessage(messages.get(count).getMessage())
                .setView(checkBoxView)
                .setPositiveButton(context.getText(R.string.done), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(chkDontShow.isChecked())
                        {
                            db.collection(context.getString(R.string.db_user_info)).document(uId)
                                    .update("isHidden", true).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                        count++;
                        ShowMessages(messages, position);
                        dialog.dismiss();
                    }
                }).show();
    }
}
