package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class Facilities
{
    private LayoutInflater inflater;
    private String facilities;
    private Context context;

    private TextView txtFacilities;
    private EditText edtFacilities;
    private Button btnEdit;

    private View v;

    public Facilities(LayoutInflater inflater, String facilities, Context context)
    {
        this.inflater = inflater;
        this.facilities = facilities;
        this.context = context;
    }

    public View getView()
    {
        View v = inflater.inflate(R.layout.frag_edit_facilities, null);

        txtFacilities = (TextView) v.findViewById(R.id.txtEditFacilities);
        edtFacilities = (EditText) v.findViewById(R.id.edtEditFacilities);
        btnEdit = (Button) v.findViewById(R.id.btnEditFacilities);

        btnEdit.setOnClickListener(Edit);

        if(!facilities.isEmpty())
            txtFacilities.setText(facilities);
        else
        {
            txtFacilities.setText(context.getString(R.string.edit_no_facilities));
            btnEdit.setVisibility(View.GONE);
        }

        return v;
    }

    private View.OnClickListener Edit = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            edtFacilities.setText(facilities);
        }
    };

    public String getFacilities()
    {
        return edtFacilities.getText().toString();
    }

}
