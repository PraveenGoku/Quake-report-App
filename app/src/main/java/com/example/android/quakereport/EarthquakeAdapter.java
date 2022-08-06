package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }
        Earthquake currentitem = getItem(position);



        TextView magnitude = (TextView)listItemView.findViewById(R.id.magnitude);

        DecimalFormat formatter = new DecimalFormat("0.0"); // To make display only 1 digit after decimal in magnitude
        String newmag = formatter.format(currentitem.getMagnitude());

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentitem.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        magnitude.setText(newmag);

        String locationToSplit = currentitem.getLocation();
        if(locationToSplit.contains("of"))
        {
            String[] parts = locationToSplit.split("(?<=of)");
            String Offset = parts[0];
            String primary = parts[1];
            TextView offset = (TextView) listItemView.findViewById(R.id.location_offset);
            offset.setText(Offset);

            TextView location = (TextView) listItemView.findViewById(R.id.primary_location);
            location.setText(primary.substring(1));//To get rid of the Space in the primary location part
        }
        else{
            String Offset = "Near the";
            TextView offset = (TextView) listItemView.findViewById(R.id.location_offset);
            offset.setText(Offset);

            TextView location = (TextView) listItemView.findViewById(R.id.primary_location);
            location.setText(currentitem.getLocation());

        }


        Date dateObject = new Date(currentitem.getmTimeInMilliSeconds());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        date.setText(formattedDate);

        TextView time = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        time.setText(formattedTime);



        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch(magnitudeFloor){
            case 0:
            case 1:
                    magnitudeColorResourceId = R.color.magnitude1;
                    break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
