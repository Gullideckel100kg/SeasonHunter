package gullideckel.seasonhunter.SeasonHunterPages.JobFilter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;

public class FillTypes
{
    private List<String> selectedTypes;
    private List<String> types;
    private LinearLayout linTypes;
    private Context context;

    public FillTypes(LinearLayout linTypes, Context context)
    {
        types = new ArrayList<>();
        this.linTypes = linTypes;
        this.context = context;
    }

    private void AddTypes()
    {
        selectedTypes = new ArrayList<>();

        for(String type : types)
            linTypes.addView(AddCheckBox(context, type));
    }

    private CheckBox AddCheckBox(Context context, String type)
    {
        CheckBox chkType = new CheckBox(context);
        chkType.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        chkType.setText(type);
        chkType.setOnCheckedChangeListener(SelectType);

        return chkType;
    }

    private CompoundButton.OnCheckedChangeListener SelectType = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            if(isChecked)
                if(!selectedTypes.contains(buttonView.getText().toString()))
                    selectedTypes.add(buttonView.getText().toString());
            else
                if(selectedTypes.contains(buttonView.getText().toString()))
                    selectedTypes.remove(buttonView.getText().toString());
        }
    };

    public List<String> getSelectedTypes()
    {
        return selectedTypes;
    }

    public void Clear()
    {
        linTypes.removeAllViews();
        AddTypes();
    }

    public void SetTypes(List<CompanyDocument> docs)
    {
        for(CompanyDocument doc : docs)
            if (doc.getTypes() != null && doc.getTypes().size() > 0)
                if(!types.contains(doc.getTypes().get(0)))
                    types.add(doc.getTypes().get(0));
        AddTypes();
    }
}
