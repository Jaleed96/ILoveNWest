package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewPlannedEvents extends AppCompatActivity {
    ListView lvEvents;
    Plan plan;
    ArrayList<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        // Get plans and populate the event list
        plan = getIntent().getParcelableExtra("plan");
        eventList = plan.getEvents();

        // List view and adapters
        lvEvents = findViewById(R.id.eventPlanList);
        ViewPlannedEventsAdapter adapter = new ViewPlannedEventsAdapter(this, plan.getKey(), plan.getEvents());
        lvEvents.setAdapter(adapter);
        lvEvents.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, ViewEventDetails.class);
            i.putExtra("eventObj", eventList.get(position));
            startActivity(i);
        });
    }

    public void onEdit(View view) {
        Intent i = new Intent(this, EditPlan.class);
        i.putExtra("plan", plan);
        startActivity(i);
    }
}
