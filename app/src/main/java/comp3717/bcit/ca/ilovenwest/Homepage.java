package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Homepage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    protected void onClickCreatePlan(View v) {
        Intent i = new Intent(this, ChooseActivity.class);
        startActivity(i);
    }
}
