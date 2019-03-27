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
        lvEvents = findViewById(R.id.eventPlanList);
        plan = getIntent().getParcelableExtra("plan");
        eventList = plan.getEvents();
    }

    public void onEdit(View view){
        Intent i = new Intent(this, EditPlan.class);
        i.putExtra("plan", plan);
        startActivity(i);
    }
    @Override
    protected void onStart(){
        super.onStart();
        ViewPlannedEventsAdapter adapter = new ViewPlannedEventsAdapter(this, plan.getKey(), plan.getEvents());
        lvEvents.setAdapter(adapter);

        lvEvents.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, ViewEventDetails.class);
            i.putExtra("eventObj", eventList.get(position));
            startActivity(i);
        });
    }
}
