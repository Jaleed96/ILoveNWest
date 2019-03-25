package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;


public class EditPlan extends AppCompatActivity {
    public static Plan CURRENT_PLAN;
    public static String PLAN_NAME;
    public static Date PLAN_DATE;
    public static ArrayList<Event> EVENT_LIST = new ArrayList<Event>();
    private ArrayList<Event> selectedEvents = new ArrayList<Event>();
    private ListView lv;
    private boolean removeMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plan);

        // If our current plan hasn't been populated already, try to check Extras
        if (CURRENT_PLAN == null) {
            CURRENT_PLAN = getIntent().getParcelableExtra("plan");
            if (CURRENT_PLAN != null) {
                PLAN_NAME = CURRENT_PLAN.getName();
                PLAN_DATE = new Date(CURRENT_PLAN.getLong());
                EVENT_LIST = CURRENT_PLAN.getEvents();
            }
        }

        // Set default input values
        EditText name = findViewById(R.id.planName);
        name.setText(PLAN_NAME);
        EditText date = findViewById(R.id.planDate);
        date.setText(PLAN_DATE.toString());

        // Get the list of new events to be added to the plan
        ArrayList<Event> newEventsList = getIntent().getParcelableArrayListExtra("newEventsList");

        // Add the new events the plan
        if (newEventsList != null && newEventsList.size() > 0) {
            for (Event item : newEventsList) {
                if (!EVENT_LIST.contains(item)) {
                    EVENT_LIST.add(item);
                }
            }
        }

        // Populate the list view
        lv = findViewById(R.id.itemList);
        new EditPlan.GetContacts().execute();
    }

    public void onConfirmClick(View view) {
        // Get database reference
        DatabaseReference databaseList;
        databaseList = FirebaseDatabase.getInstance().getReference("plans");

        // Grab inputs
        EditText planName = findViewById(R.id.planName);
        EditText planDate = findViewById(R.id.planDate);

        // Create or Update current plan
        if (CURRENT_PLAN == null) {
            // Create a new plan based on inputs
            CURRENT_PLAN = new Plan(planName.getText().toString(), new Date(planDate.getText().toString()), EVENT_LIST);

            // Save to the database
            String id = databaseList.push().getKey();
            databaseList.child(id).setValue(CURRENT_PLAN);
        } else {
            // Update current plan values
            CURRENT_PLAN.setName(planName.getText().toString());
            CURRENT_PLAN.setDate(new Date(planDate.getText().toString()));
            CURRENT_PLAN.setEvent(EVENT_LIST);

            // TODO: untest logic for updating
            databaseList.child(CURRENT_PLAN.getKey()).setValue(CURRENT_PLAN);
        }

        // Zero-out the current plan
        clearData();

        // Clear all activities and go to the view page
        Intent i = new Intent(getApplicationContext(), ViewPlans.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void onCancelClick(View view) {
        // Zero-out the current plan
        clearData();

        // Clear all activities an return to homepage
        Intent i = new Intent(getApplicationContext(), Homepage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void onFloatButtonClick(View view){
        if(!removeMode){
            // Start choosing a new activity and adding its events
            Intent i = new Intent(this, ChooseActivity.class);
            startActivity(i);
        }
        else{
            // Remove the event from the main list
            for(Event item : selectedEvents){
                EVENT_LIST.remove(item);
            }

            // Clear the selected events
            selectedEvents = new ArrayList<Event>();
            setAddMode();

            // Update the adapter
            ScheduleActivitiesAdapter adapter = new ScheduleActivitiesAdapter(EditPlan.this, EVENT_LIST);
            lv.setAdapter(adapter);
        }
    }

    private void clearData() {
        CURRENT_PLAN = null;
        PLAN_NAME = null;
        PLAN_DATE = null;
    }

    private void setAddMode(){
        removeMode = false;
        FloatingActionButton floatBtn = findViewById(R.id.addEntryButton);
        floatBtn.setImageResource(android.R.drawable.ic_input_add);
    }

    private void setRemoveMode(){
        removeMode = true;
        FloatingActionButton floatBtn = findViewById(R.id.addEntryButton);
        floatBtn.setImageResource(android.R.drawable.ic_delete);
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            // Set the adapter for the list view
            ScheduleActivitiesAdapter adapter = new ScheduleActivitiesAdapter(EditPlan.this, EVENT_LIST);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Event selectedEvent = EVENT_LIST.get(position);
                    if(!selectedEvents.contains(selectedEvent)){
                        selectedEvents.add(selectedEvent);
                        view.setBackgroundResource(R.color.colorListItem1);
                    }
                    else{
                        selectedEvents.remove(selectedEvent);
                        view.setBackgroundResource(R.color.colorListItem2);
                    }

                    if(selectedEvents.size() > 0){
                        setRemoveMode();
                    }
                    else{
                        setAddMode();
                    }
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        }
    }
}
