package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    public void onClickChooseActivity(View v) {
        Intent i = new Intent(this, ScheduleActivities.class);
        startActivity(i);
    }
}
