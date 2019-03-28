package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Job.CompanyDetails.E_CompanyJob.CompanyJobEdit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class CompanyJobEditViewHolder extends RecyclerView.ViewHolder
{
    private EditText edtJobTitle;

    private TextView txtNoPayment;

    private RadioGroup rdgPayment;
    private RadioButton rdbHourly;
    private RadioButton rdbPiece;
    private RadioButton rdbVolunteer;

    public RadioButton getRdbHourly()
    {
        return rdbHourly;
    }

    public RadioButton getRdbPiece()
    {
        return rdbPiece;
    }

    public RadioButton getRdbVolunteer()
    {
        return rdbVolunteer;
    }

    private TextView txtSpinStartHeadline;
    private Spinner spinStartDay;
    private Spinner spinStartMonth;
    private TextView txtSpinEndHeadline;
    private Spinner spinEndDay;
    private Spinner spinEndMonth;

    private EditText edtAdditionalInfo;

    private ImageButton iBtnRemoveJob;

    public ImageButton getiBtnRemoveJob()
    {
        return iBtnRemoveJob;
    }

    public EditText getEdtJobTitle()
    {
        return edtJobTitle;
    }

    public RadioGroup getRdgPayment()
    {
        return rdgPayment;
    }

    public EditText getEdtAdditionalInfo()
    {
        return edtAdditionalInfo;
    }

    public TextView getTxtNoPayment()
    {
        return txtNoPayment;
    }

    public TextView getTxtSpinStartHeadline()
    {
        return txtSpinStartHeadline;
    }

    public TextView getTxtSpinEndHeadline()
    {
        return txtSpinEndHeadline;
    }

    public CompanyJobEditViewHolder(View v)
    {
        super(v);

        edtJobTitle = (EditText) v.findViewById(R.id.edtJobTitle);

        txtNoPayment = (TextView) v.findViewById(R.id.txtNoPayment);

        rdgPayment = (RadioGroup) v.findViewById(R.id.rdgPayment);

        rdbHourly = (RadioButton) v.findViewById(R.id.rdbHourlyPaid);
        rdbPiece = (RadioButton) v.findViewById(R.id.rdbPieceWork);
        rdbVolunteer = (RadioButton) v.findViewById(R.id.rdbVolunteering);

        txtSpinStartHeadline = (TextView) v.findViewById(R.id.txtStartDateHeadLine);
        spinStartDay = (Spinner) v.findViewById(R.id.spinDayStart);
        spinStartMonth = (Spinner) v.findViewById(R.id.spinMonthStart);
        txtSpinEndHeadline = (TextView) v.findViewById(R.id.txtEndDateHeadLine);

        spinEndDay = (Spinner) v.findViewById(R.id.spinDayEnd);
        spinEndMonth = (Spinner) v.findViewById(R.id.spinMonthEnd);

        edtAdditionalInfo = (EditText) v.findViewById(R.id.edtAdditionalInfo);


        iBtnRemoveJob = (ImageButton) v.findViewById(R.id.iBtnRemoveJob);

    }

    public Spinner getSpinStartDay()
    {
        return spinStartDay;
    }

    public Spinner getSpinStartMonth()
    {
        return spinStartMonth;
    }

    public Spinner getSpinEndDay()
    {
        return spinEndDay;
    }

    public Spinner getSpinEndMonth()
    {
        return spinEndMonth;
    }
}
