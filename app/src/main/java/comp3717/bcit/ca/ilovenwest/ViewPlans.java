package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewPlans extends Activity {
    ListView lvPlans;
    ArrayList<Plan> planList;
    DatabaseReference databaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);
        lvPlans = findViewById(R.id.planList);
        planList = new ArrayList<>();
        databaseList = FirebaseDatabase.getInstance().getReference("plans");
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                planList.clear();
                for (DataSnapshot planSnapShot : dataSnapshot.getChildren()) {
                    //Event e = planSnapShot.getValue(Event.class);
                    Log.d("println", planSnapShot.toString());
                    Plan plan = planSnapShot.getValue(Plan.class);
                    Log.d("println", planSnapShot.getValue(Plan.class).toString());
                    planList.add(plan);
                }

                ViewPlansAdapter adapter = new ViewPlansAdapter(ViewPlans.this, planList);
                lvPlans.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
