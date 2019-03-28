package gullideckel.seasonhunter.SeasonHunterPages.JobSettings.Message;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import gullideckel.seasonhunter.Objects.Message.Message;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;


public class FragJobSettingsMessage extends Fragment
{

    private EditText edtMessage;
    private Button btnSend;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public static FragJobSettingsMessage newInstance()
    {
        FragJobSettingsMessage fragment = new FragJobSettingsMessage();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_job_settings_message, container, false);

        v.setBackgroundColor(Color.WHITE);
        v.bringToFront();

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        edtMessage = (EditText) v.findViewById(R.id.edtMessage);
        btnSend = (Button) v.findViewById(R.id.btnMessageSend);

        btnSend.setOnClickListener(Send);

        return v;
    }

    private View.OnClickListener Send = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(!edtMessage.getText().toString().isEmpty())
            {
                Message message = new Message(auth.getUid(), edtMessage.getText().toString());
                db.collection(getContext().getString(R.string.db_message)).add(message).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        StaticMethod.Toast(getContext().getString(R.string.message_sent), getContext());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        StaticMethod.Toast(getContext().getString(R.string.message_not_sent), getContext());
                    }
                });
            }

        }
    };


}
