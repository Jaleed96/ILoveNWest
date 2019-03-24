package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void onClickCreatePlan(View v) {
        //Intent i = new Intent(this, ChooseActivity.class);
        Intent i = new Intent(this, CreatePlan.class);
        startActivity(i);
    }

    public void onClickViewPlans(View v) {
        Intent i = new Intent(this, ViewPlans.class);
        startActivity(i);
    }
}
