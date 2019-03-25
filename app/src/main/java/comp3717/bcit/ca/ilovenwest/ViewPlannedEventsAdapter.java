package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewPlannedEventsAdapter extends ArrayAdapter<Event> {
    Activity context;
    String dbKey;
    List<Event> events;

    public ViewPlannedEventsAdapter(Activity context, String dbKey, ArrayList<Event> events){
        super(context, 0, events);
        this.context = context;
        this.events = events;
        this.dbKey = dbKey;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.view_event_list, null, true);
        TextView name = listViewItem.findViewById(R.id.eventListName);
        TextView address = listViewItem.findViewById(R.id.eventListAddress);

        Event event = events.get(position);
        name.setText(event.getName());
        address.setText(event.getAddress());
        return listViewItem;
    }
}
