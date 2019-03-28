package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class Name
{
    private LayoutInflater inflater;
    private EditText edtName;
    private TextView txtName;
    private String name;

    public Name(LayoutInflater inflater, String name)
    {
        this.inflater = inflater;
        this.name = name;
    }

    public View getView()
    {
        View v = inflater.inflate(R.layout.frag_edit_name, null);

        edtName = (EditText) v.findViewById(R.id.edtEditName);
        txtName = (TextView) v.findViewById(R.id.txtEditName);

        txtName.setText(name);

        return v;
    }

    public String GetName()
    {
        if(edtName.getText().toString().isEmpty())
            return null;
        else
            return edtName.getText().toString();
    }
}
