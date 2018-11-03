package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import gullideckel.seasonhunter.JobRecruitment.CompanyDetails.B_CompanyType.CompanyTypeViewHolderList;
import gullideckel.seasonhunter.Objects.JobInformation.JobInformationSub.CompanyContact;
import gullideckel.seasonhunter.R;

public class CompanyContactAdapter extends RecyclerView.Adapter<CompanyContactViewHolderList>
{
    private List<String> items;
    private String type;

    public CompanyContactAdapter(List<String> items, String type)
    {
        this.items = items;
        this.type = type;
    }

    @NonNull
    @Override
    public CompanyContactViewHolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_company_contact_viewholder_list, parent, false);
        CompanyContactViewHolderList viewHolder = new CompanyContactViewHolderList(v);
        return viewHolder;
    }


    //TODO: Change Input type of Editbox correctly: Email input type email and so on
    @Override
    public void onBindViewHolder(@NonNull CompanyContactViewHolderList holder, final int position)
    {
        holder.getTxtContact().setText(type);

        if(position == 0)
            holder.getEdtContact().setInputType(InputType.TYPE_CLASS_PHONE);
        else if (position == 1)
            holder.getEdtContact().setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        holder.getEdtContact().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                System.out.print("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                System.out.print("");
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                items.set(position,s.toString());
            }
        });
    }



    @Override
    public int getItemCount()
    {
        return items.size();
    }
}
