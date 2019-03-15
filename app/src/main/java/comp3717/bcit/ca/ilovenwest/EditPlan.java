package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class EditPlan extends Activity {
    private ArrayList<Event> eventList;
    private ArrayList<Event> newEventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plan);

        newEventsList = getIntent().getParcelableArrayListExtra("newEventsList");

        for(Event item : newEventsList){
            Log.i("Debug", item.getName() + " was ADDED!");
        }
    }

    public void onConfirmClick(View view){

    }

    public void onCancelClick(View view){

    }
}
