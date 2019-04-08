package gullideckel.seasonhunter.ActivitySignIn.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import gullideckel.seasonhunter.ActivitySignIn.OnClickSignInHunter.OnClickCreateAccount;
import gullideckel.seasonhunter.Interfaces.IFragmentHandler;
import gullideckel.seasonhunter.R;


public class FragCreateAccountHunter extends Fragment
{
    //TODO: No line breaks at Editboxes

    private IFragmentHandler mListener;
    private ImageButton imbHidePassword;
    private EditText edtCreatePassword;
    private boolean isHidden = true;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        view.setBackgroundColor(Color.WHITE);
        view.bringToFront();

        EditText edtCreateEmail = (EditText) view.findViewById(R.id.edtCreateEmail);
        edtCreatePassword = (EditText) view.findViewById(R.id.edtCreatePassword);
        imbHidePassword = (ImageButton) view.findViewById(R.id.imbCreatePasswordHide);

        edtCreatePassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        Button btnCreateAccount = (Button) view.findViewById(R.id.btnCreateNewAccount);
        imbHidePassword.setOnClickListener(PasswordHide);

        btnCreateAccount.setOnClickListener(new OnClickCreateAccount(edtCreateEmail,edtCreatePassword, getActivity()));

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


}
