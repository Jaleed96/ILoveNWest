package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewPlansAdapter extends ArrayAdapter<Plan> {
    Activity context;
    List<Plan> plans;

    public ViewPlansAdapter(Activity context, ArrayList<Plan> plans){
        super(context, 0, plans);
        this.context = context;
        this.plans = plans;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.view_plan_list, null, true);
        TextView date = listViewItem.findViewById(R.id.eventDate);

        Plan plan = plans.get(position);
        date.setText(plan.getDate().toString());

        return listViewItem;
    }
}
