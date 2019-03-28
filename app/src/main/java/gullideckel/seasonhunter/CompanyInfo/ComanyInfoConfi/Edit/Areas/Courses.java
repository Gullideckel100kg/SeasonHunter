package gullideckel.seasonhunter.CompanyInfo.ComanyInfoConfi.Edit.Areas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gullideckel.seasonhunter.R;

public class Courses
{
    private LayoutInflater inflater;
    private String courses;
    private Context context;

    private TextView txtCourses;
    private Button btnEdit;
    private EditText edtCourses;

    public Courses(LayoutInflater inflater, String courses, Context context)
    {
        this.inflater = inflater;
        this.courses = courses;
        this.context = context;
    }

    public View getView()
    {
        View v = inflater.inflate(R.layout.frag_edit_courses, null);

        txtCourses = (TextView) v.findViewById(R.id.txtEditCourses);
        btnEdit = (Button) v.findViewById(R.id.btnEditCourses);
        edtCourses = (EditText) v.findViewById(R.id.edtEditCourses);

        if(!courses.isEmpty())
            txtCourses.setText(courses);
        else
        {
            txtCourses.setText(context.getString(R.string.edit_no_courses));
            btnEdit.setVisibility(View.GONE);
        }
        btnEdit.setOnClickListener(Edit);

        return v;
    }

    private View.OnClickListener Edit = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            edtCourses.setText(courses);
        }
    };

    public String getCourses()
    {
        return edtCourses.getText().toString();
    }


}
