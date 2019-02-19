package gullideckel.seasonhunter.CompanyInfo.ComanyInfoAddings.Review;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import gullideckel.seasonhunter.Interfaces.IDocument;
import gullideckel.seasonhunter.Interfaces.IWorkTime;
import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.Objects.Review.Review;
import gullideckel.seasonhunter.Objects.Review.ReviewWorkTime;
import gullideckel.seasonhunter.R;
import gullideckel.seasonhunter.Statics.StaticMethod;
import gullideckel.seasonhunter.Statics.StaticVariabels;


public class FragAddReview extends Fragment implements IWorkTime
{
    protected IDocument listener;
    protected CompanyDocument doc;

    private View v;

    private Review review;

    private ScrollView scrollReview;
    private EditText edtWorkPosition;
    private Button btnWorkTime;
    private TextView txtWorkTime;
    private RadioGroup rdgWage;
    private EditText edtOtherPayment;
    private EditText edtSalary;
    private EditText edtComment;
    private Button btnCancel;
    private Button btnDone;

    private ReviewWorkTime workTime;
    private AddStarRating addStarRating;

    private volatile boolean isBlockedScrollView = false;


    public static FragAddReview newInstance(IDocument listener, CompanyDocument doc)
    {
        FragAddReview fragment = new FragAddReview();
        fragment.listener = listener;
        fragment.doc = doc;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.frag_add_review, container, false);
        v.setBackgroundColor(Color.WHITE);
        v.bringToFront();

        scrollReview = (ScrollView) v.findViewById(R.id.scrollAddReview);
        edtWorkPosition = (EditText) v.findViewById(R.id.edtAddWorkPosition);
        btnWorkTime = (Button) v.findViewById(R.id.btnAddWorkTime);
        txtWorkTime = (TextView) v.findViewById(R.id.txtAddWorkTime);
        rdgWage = (RadioGroup) v.findViewById(R.id.rdgAddWageType);
        edtOtherPayment = (EditText) v.findViewById(R.id.edtAddOtherPayment);
        edtSalary = (EditText) v.findViewById(R.id.edtAddSalary);
        edtComment = (EditText) v.findViewById(R.id.edtAddComment);
        btnCancel = (Button) v.findViewById(R.id.btnCancelReview);
        btnDone = (Button) v.findViewById(R.id.btnAddReview);

        scrollReview.setOnTouchListener(TouchScroll);

        btnWorkTime.setOnClickListener(OpenCalender);

        rdgWage.setOnCheckedChangeListener(CheckPaymentMethod);
        rdgWage.check(R.id.rdbAddHourly);
        edtOtherPayment.setVisibility(View.GONE);

        addStarRating = new AddStarRating(v, getContext());
        addStarRating.Init();

        btnCancel.setOnClickListener(Cancel);
        btnDone.setOnClickListener(Done);

        return v;
    }

    private View.OnClickListener OpenCalender = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            StaticMethod.RemoveKeyPad(getActivity());
            focusOnView();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.frmCalenderReview, FragWorkTimeCalender.newInstance(FragAddReview.this));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };

    private final void focusOnView(){
        scrollReview.post(new Runnable() {
            @Override
            public void run() {
                scrollReview.scrollTo(0, txtWorkTime.getBottom());
                isBlockedScrollView = true;
            }
        });
    }

    private RadioGroup.OnCheckedChangeListener CheckPaymentMethod = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            if(checkedId == R.id.rdbAddOtherPayment)
                edtOtherPayment.setVisibility(View.VISIBLE);
            else
                edtOtherPayment.setVisibility(View.GONE);
        }
    };

    //TODO Convert Date to (ex. 2th of January 2018). Just looks nicer
    @Override
    public void RevieveWorkTime(ReviewWorkTime workTime)
    {
        this.workTime = workTime;
        txtWorkTime.setText(workTime.getStartDay() + "/" + workTime.getStartMonth() + "/" + workTime.getStartYear() + " - " +
                            workTime.getEndDay() + "/" + workTime.getEndMonth() + "/" + workTime.getEndYear() + "  " +
                            workTime.getDuration() + " " + getContext().getText(R.string.cal_duration_days));
        isBlockedScrollView = false;
    }

    private View.OnTouchListener TouchScroll = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            return isBlockedScrollView;
        }
    };

    //TODO Make it for user possible to save it as draft
    private View.OnClickListener Cancel = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            getActivity().onBackPressed();
        }
    };

    private View.OnClickListener Done = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            review = new Review();

            review.setWorkPosition(edtWorkPosition.getText().toString());
            review.setWorkTime(workTime);

            if(rdgWage.getCheckedRadioButtonId() == R.id.rdbAddHourly)
                review.setWage(StaticVariabels.HOURLY);
            else if(rdgWage.getCheckedRadioButtonId() == R.id.rdbAddPiece)
                review.setWage(StaticVariabels.PIECE_RATE);
            else if (rdgWage.getCheckedRadioButtonId() == R.id.rdbAddOtherPayment)
            {
                review.setWage(StaticVariabels.OTHER_PAYMENT);
                review.setOtherPayment(edtOtherPayment.getText().toString());
            }

            review.setSalary(edtSalary.getText().toString());

            review.setStarRating(addStarRating.getStars());
            review.setComment(edtComment.getText().toString());

            ReviewInspection inspection = new ReviewInspection(getContext(), v);

            if(inspection.Inspect(review))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(inspection.getMessageCorrect(review)).setPositiveButton("Send", dialogClickListener)
                                                                        .setNegativeButton("Edit", dialogClickListener).show();
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(inspection.getMessageWrong()).setNegativeButton("Edit", dialogClickListener).show();
            }
        }
    };

    private DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE: ;
                    doc.addReview(review);
                    listener.RecieveDocument(doc);
                    getActivity().onBackPressed();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.dismiss();
                    break;
            }
        }
    };
}
