package gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Review;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import gullideckel.seasonhunter.Interfaces.IWorkTime;
import gullideckel.seasonhunter.Objects.Review.CompanyWorkTime;
import gullideckel.seasonhunter.R;



public class FragAddReview extends Fragment implements IWorkTime
{
    private EditText edtWorkPosition;
    private Button btnWorkTime;
    private TextView txtWorkTime;
    private RadioGroup rdgWage;
    private EditText edtComment;
    private Button btnCancel;
    private Button btnDone;

    public static FragAddReview newInstance()
    {
        FragAddReview fragment = new FragAddReview();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.frag_add_review, container, false);
        v.setBackgroundColor(Color.WHITE);
        v.bringToFront();

        edtWorkPosition = (EditText) v.findViewById(R.id.edtAddWorkPosition);
        btnWorkTime = (Button) v.findViewById(R.id.btnAddWorkTime);
        txtWorkTime = (TextView) v.findViewById(R.id.txtAddWorkTime);
        rdgWage = (RadioGroup) v.findViewById(R.id.rdgAddWageType);
        edtComment = (EditText) v.findViewById(R.id.edtAddComment);
        btnCancel = (Button) v.findViewById(R.id.btnCancelReview);
        btnDone = (Button) v.findViewById(R.id.btnAddReview);

        btnWorkTime.setOnClickListener(OpenCalender);

        return v;
    }

    private View.OnClickListener OpenCalender = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.frmCalenderReview, FragWorkTimeCalender.newInstance(FragAddReview.this));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };


    //TODO Convert Date to (ex. 2th of January 2018). Just looks nicer
    @Override
    public void RevieveWorkTime(CompanyWorkTime workTime)
    {
    txtWorkTime.setText(workTime.getStartDay() + "/" + workTime.getStartMonth() + "/" + workTime.getStartYear() + " - " +
                        workTime.getEndDay() + "/" + workTime.getEndMonth() + "/" + workTime.getEndYear());
    }
}
