package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas;

import android.content.Context;
import android.location.GnssClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class Description
{
    private LayoutInflater inflater;
    private String description;
    private Context context;

    private TextView txtDescription;
    private Button btnEdit;
    private EditText edtDescription;

    public Description(LayoutInflater inflater, String description, Context context)
    {
        this.inflater = inflater;
        this.description = description;
        this.context = context;
    }

    public View getView()
    {
        View v = inflater.inflate(R.layout.frag_edit_description, null);

        txtDescription = (TextView) v.findViewById(R.id.txtEditDescription);
        btnEdit = (Button) v.findViewById(R.id.btnEditDescription);
        edtDescription = (EditText) v.findViewById(R.id.edtEditDescription);

        if(!description.isEmpty())
            txtDescription.setText(description);
        else
        {
            txtDescription.setText(context.getString(R.string.edit_no_description));
            btnEdit.setVisibility(View.GONE);
        }
        btnEdit.setOnClickListener(Edit);

        return v;
    }

    private View.OnClickListener Edit = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            edtDescription.setText(description);
        }
    };

    public String getDescription() { return edtDescription.getText().toString(); }
}
