package gullideckel.seasonhunter.ActivitySignIn.Authentification;

import android.text.TextUtils;
import android.widget.EditText;

public class Validation
{
    //TODO:Hide typed in Password!!!
    //This method checks the correctness of the typed in Email and Password!!!
    public static boolean ValidateForm(EditText signEmail, EditText signPassword) {
        boolean valid = true;

        String email = signEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            signEmail.setError("Required.");
            valid = false;
        } else {
            signEmail.setError(null);
        }

        String password = signPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            signPassword.setError("Required.");
            valid = false;
        } else {
            signPassword.setError(null);
        }

        return valid;
    }

}
