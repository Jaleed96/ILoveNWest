package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    public void onClickHistory(View v) {
//        ImageButton historyBtn = findViewById(R.id.historyBtn);
//        historyBtn.setBackgroundColor(getResources().getColor(R.color.orange));
        Intent i = new Intent(this, ScheduleActivities.class);
        startActivity(i);
    }
    public void onClickArt(View v) {
        Intent i = new Intent(this, ScheduleActivities.class);
        startActivity(i);
    }
    public void onClickCulture(View v) {
        Intent i = new Intent(this, ScheduleActivities.class);
        startActivity(i);
    }
    public void onClickMusic(View v) {
        Intent i = new Intent(this, ScheduleActivities.class);
        startActivity(i);
    }
}
