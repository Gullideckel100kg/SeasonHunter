package gullideckel.seasonhunter.ActivitySignIn.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import gullideckel.seasonhunter.ActivitySignIn.Authentification.Validation;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.Interfaces.IntFrag;
import gullideckel.seasonhunter.Objects.User.UserInfo;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;


public class FragCreateAccountHunter extends Fragment
{

    private static final String TAG = "FragCreateAccountHunter";

    private ImageButton imbHidePassword;
    private EditText edtCreatePassword;
    private EditText edtCreateEmail;
    private Button btnCreateAccount;
    private boolean isHidden = true;
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        view.setBackgroundColor(Color.WHITE);
        view.bringToFront();

        edtCreateEmail = (EditText) view.findViewById(R.id.edtCreateEmail);
        edtCreatePassword = (EditText) view.findViewById(R.id.edtCreatePassword);
        imbHidePassword = (ImageButton) view.findViewById(R.id.imbCreatePasswordHide);

        edtCreatePassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        btnCreateAccount = (Button) view.findViewById(R.id.btnCreateNewAccount);
        imbHidePassword.setOnClickListener(PasswordHide);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        btnCreateAccount.setOnClickListener(CreateAccount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.frag_create_account_hunter, container, false);
    }

    private View.OnClickListener PasswordHide = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(isHidden)
            {
                edtCreatePassword.setInputType(InputType.TYPE_CLASS_TEXT);
                isHidden = false;
            }
            else
            {
                edtCreatePassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                isHidden = true;
            }

        }
    };

    public View.OnClickListener CreateAccount = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(edtCreatePassword.length() > 6)
                CreateAccount();
            else
                StaticMethod.Toast(getContext().getString(R.string.sign_password_short), getContext());
        }
    };

    private void OnCreateAccount(boolean isCreating)
    {
        btnCreateAccount.setEnabled(!isCreating);
        imbHidePassword.setEnabled(!isCreating);
        if(isCreating)
            btnCreateAccount.setText(getContext().getString(R.string.loading));
        else
            btnCreateAccount.setText(getContext().getString(R.string.login_create_account));
    }

    private void CreateAccount()
    {
        Log.d(TAG, "createAccount:" + edtCreateEmail.getText().toString());
        if (!Validation.ValidateForm(edtCreateEmail, edtCreatePassword)) {
            return;
        }

        OnCreateAccount(true);

        CheckEmailOnServer();

        auth.createUserWithEmailAndPassword(edtCreateEmail.getText().toString(), edtCreatePassword.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Log.d(TAG, "createUserWithEmail:success");

                            final String uId = task.getResult().getUser().getUid();
                            final String email = task.getResult().getUser().getEmail();

                            db.collection(getContext().getString(R.string.db_user_info)).document(uId).get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task)
                                        {
                                            if(task.isSuccessful())
                                            {
                                                DocumentSnapshot document = task.getResult();
                                                if(!document.exists())
                                                {
                                                    UserInfo info = new UserInfo(email, uId);

                                                    db.collection(getContext().getString(R.string.db_user_info)).document(uId)
                                                            .set(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task)
                                                        {
                                                            if(task.isSuccessful())
                                                            {
                                                                OnCreateAccount(false);
                                                                Log.i(TAG, "onComplete: New UserInfo document in Database");
                                                                ((IFragmentHandler) getContext()).onReplaceFragment(new FragEmailVerification(), IntFrag.REPLACE);

                                                            }
                                                            else
                                                            {
                                                                OnCreateAccount(false);
                                                                Log.e(TAG, "onComplete: ", task.getException());
                                                            }
                                                        }
                                                    });
                                                }
                                                else
                                                {
                                                    OnCreateAccount(false);
                                                    Log.d(TAG, "signInWithCredential:success");
                                                    OnCreateAccount(false);
                                                    ((IFragmentHandler) getContext()).onReplaceFragment(new FragEmailVerification(), IntFrag.REPLACE);

                                                }
                                            }
                                        }
                                    });

                        }
                        else
                        {
                            OnCreateAccount(false);
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            if(edtCreatePassword.getText().toString().length() < 8)
                                Toast.makeText(getContext(), "Password length has to contain at least 7 characters", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getContext(), "Could not create your account. May the email exist already", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void CheckEmailOnServer()
    {
        auth.fetchSignInMethodsForEmail(edtCreateEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task)
                    {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                        {
                            Log.w(TAG, "Invalid Email address");
                            Toast.makeText(getContext(), "Invalid Email address", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (task.isSuccessful())
                        {
                            if (task.getResult().getSignInMethods() != null && !task.getResult().getSignInMethods().isEmpty())
                            {
                                Toast.makeText(getContext(), "Email already exist", Toast.LENGTH_LONG).show();
                            }
                        } else if (task.getException() instanceof FirebaseNetworkException)
                        {
                            Log.w(TAG, "Firebase Network Exception");
                            Toast.makeText(getContext(), getContext().getString(R.string.login_network_exception), Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
    }


}
