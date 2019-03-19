package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class EditPlan extends AppCompatActivity {
    public static Plan CURRENT_PLAN;
    public static Date PLAN_DATE;
    public static ArrayList<Event> EVENT_LIST = new ArrayList<Event>();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plan);

        CURRENT_PLAN = getIntent().getParcelableExtra("plan");

        ArrayList<Event> newEventsList = getIntent().getParcelableArrayListExtra("newEventsList");

        for (Event item : newEventsList) {
            if (!EVENT_LIST.contains(item)) {
                EVENT_LIST.add(item);
            }
        }

        lv = findViewById(R.id.itemList);

        new EditPlan.GetContacts().execute();
    }

    public void onConfirmClick(View view) {
        PLAN_DATE = new Date();

        if (CURRENT_PLAN == null) {
            CURRENT_PLAN = new Plan(PLAN_DATE, EVENT_LIST);
        }

        DatabaseReference databaseList;
        databaseList = FirebaseDatabase.getInstance().getReference("plans");
        String id = databaseList.push().getKey();

        databaseList.child(id).setValue(CURRENT_PLAN);

        // Clear all activities and go to the view page
        Intent i = new Intent(getApplicationContext(), ViewPlans.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void onCancelClick(View view) {
        // Clear all activities an return to homepage
        Intent i = new Intent(getApplicationContext(), Homepage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void onAddClick(View view) {

    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            ScheduleActivitiesAdapter adapter = new ScheduleActivitiesAdapter(EditPlan.this, EVENT_LIST);
            lv.setAdapter(adapter);
        }
    }
}
