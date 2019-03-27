package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.Date;

public class CreatePlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
    }

    public void onCreatePlan(View view) {
        Intent i = new Intent(this, ChooseActivity.class);

        // Grab Input Values
        EditText name = findViewById(R.id.planName);
        CalendarView date = findViewById(R.id.planDate);

        // Set this to be the new current plan values
        EditPlan.PLAN_NAME = name.getText().toString();
        EditPlan.PLAN_DATE = new Date(date.getDate());

        startActivity(i);
    }
}
