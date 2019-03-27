package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScheduleActivitiesAdapter extends ArrayAdapter<Event> {
    Context _context;

    public ScheduleActivitiesAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final Activity activity = (Activity) _context;

        // Get the data item for this position
        Event event = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.schedule_acitivities_list, parent, false);
        }

        // Populate the event list
        TextView eventName = convertView.findViewById(R.id.eventName);
        eventName.setText(event.getName().substring(0, Math.min(event.getName().length(), 30)));
        TextView eventAddress = convertView.findViewById(R.id.eventAddress);
        eventAddress.setText(event.getAddress());
        convertView.setBackgroundResource(R.color.offWhite);

        // Return the completed view to render on screen
        return convertView;
    }
}