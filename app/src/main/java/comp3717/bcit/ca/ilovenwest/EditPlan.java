package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class EditPlan extends Activity {
    //public static Plan CURRENT_PLAN;
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
        //if(CURRENT_PLAN == null){
        //
        // }
    }

    public void onCancelClick(View view) {
        // Clear all activities an return to homepage
        Intent i = new Intent(getApplicationContext(), Homepage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
