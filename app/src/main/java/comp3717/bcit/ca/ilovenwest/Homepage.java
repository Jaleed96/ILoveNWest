package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.nav_logout){
                    signOut();
                }
                return false;
            }
        });
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

    private void signOut(){
        // Clear shared preferences
        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.clear();
        Ed.apply();

        // Return to login page
        Intent i = new Intent(this, login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
