package gullideckel.seasonhunter.JobRecruitment.CompanyDetails.D_CompanyContact;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gullideckel.seasonhunter.R;

public class CompanyContactAdapter extends RecyclerView.Adapter<CompanyContactViewHolderList>
{
    private List<String> items;
    private String hint;
    private int inputType;

    public CompanyContactAdapter(List<String> items, String hint,int inputType)
    {
        this.items = items;
        this.hint = hint;
        this.inputType = inputType;
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
        holder.getTxtContact().setText(hint);

        holder.getEdtContact().setInputType(inputType);

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
