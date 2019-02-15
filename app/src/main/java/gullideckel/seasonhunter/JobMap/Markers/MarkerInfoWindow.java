package gullideckel.seasonhunter.JobMap.Markers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import gullideckel.seasonhunter.Objects.Job.CompanyDocument;
import gullideckel.seasonhunter.R;

public class MarkerInfoWindow implements GoogleMap.InfoWindowAdapter
{
    private Context context;

    public MarkerInfoWindow(Context context)
    {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker)
    {
        return null;
    }


    @Override
    public View getInfoContents(Marker marker)
    {
        View v = ((Activity)context).getLayoutInflater().inflate(R.layout.job_marker_info_window, null);

        TextView txtName = (TextView) v.findViewById(R.id.txtInfoName);
        TextView txtType = (TextView) v.findViewById(R.id.txtInfoType);
        final TextView txtMoreDetails = (TextView) v.findViewById(R.id.txtInfoMoreDetails);

        CompanyDocument doc = (CompanyDocument) marker.getTag();

        txtName.setText(doc.getName());
        txtType.setText(doc.getType());

        txtMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                txtMoreDetails.setText("More Details >>> (to be continued...)");
            }
        });

        return v;
    }
}
