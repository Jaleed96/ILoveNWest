package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    protected void onClickChooseActivity(View v) {
        Intent i = new Intent(this, ScheduleActivities.class);
        startActivity(i);
    }
}
