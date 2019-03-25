package comp3717.bcit.ca.ilovenwest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class ViewPlannedEvents extends AppCompatActivity {
    ListView lvEvents;
    Plan plan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        lvEvents = findViewById(R.id.eventPlanList);
        plan = getIntent().getParcelableExtra("plan");
    }

    @Override
    protected void onStart(){
        super.onStart();
        ViewPlannedEventsAdapter adapter = new ViewPlannedEventsAdapter(this, plan.getKey(), plan.getEvents());
        lvEvents.setAdapter(adapter);


    }
}
