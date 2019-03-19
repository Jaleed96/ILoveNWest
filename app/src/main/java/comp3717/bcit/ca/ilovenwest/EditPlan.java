package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class EditPlan extends Activity {
    public static Plan CURRENT_PLAN;
    public static Date PLAN_DATE;
    public static ArrayList<Event> EVENT_LIST = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plan);

        ArrayList<Event> newEventsList = getIntent().getParcelableArrayListExtra("newEventsList");

        for (Event item : newEventsList) {
            if (!EVENT_LIST.contains(item)) {
                EVENT_LIST.add(item);
            }
        }

        ListView list = findViewById(R.id.itemList);
        //list.setAdapter();

    }

    public void onConfirmClick(View view) {
        PLAN_DATE = new Date();

        if(CURRENT_PLAN == null){
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
}
