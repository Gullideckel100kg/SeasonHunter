package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.R;

public class Type
{
    private LayoutInflater inflater;
    private String type;
    private Context context;

    private Spinner spinType;
    private ArrayAdapter<CharSequence> adapter;
    private TextView txtEdit;

    public Type(LayoutInflater inflater, String type, Context context)
    {
        this.inflater = inflater;
        this.type = type;
        this.context = context;
    }

    public View getView()
    {
        View v = inflater.inflate(R.layout.frag_edit_type, null);

        txtEdit = (TextView) v.findViewById(R.id.txtEditType);
        txtEdit.setText(type);

        spinType = (Spinner) v.findViewById(R.id.spinEditType);

        adapter = ArrayAdapter.createFromResource(context, R.array.company_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinType.setAdapter(adapter);

        for(int i = 0; i < adapter.getCount(); i++)
            if(adapter.getItem(i).toString().equals(type))
                spinType.setSelection(i);

        return v;
    }

    public List<String> getType()
    {
        List<String> types = new ArrayList<>();
        types.add(spinType.getSelectedItem().toString());

        if(spinType.getSelectedItem().toString().equals(txtEdit.getText().toString()))
            return null;
        else
            return types;
    }
}
