package gullideckel.seasonhunter.Authentification;

import android.text.TextUtils;
import android.widget.EditText;

public class Validation
{
    public boolean ValidateForm(EditText signEmail, EditText signPassword) {
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
