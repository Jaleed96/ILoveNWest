package comp3717.bcit.ca.ilovenwest;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewPlans extends AppCompatActivity {
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
                    Plan plan = planSnapShot.getValue(Plan.class);
                    plan.setKey(planSnapShot.getKey());
                    planList.add(plan);
                }

                ViewPlansAdapter adapter = new ViewPlansAdapter(ViewPlans.this, planList);
                lvPlans.setAdapter(adapter);

                lvPlans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(ViewPlans.this, ViewPlannedEvents.class);
                        Plan plan = planList.get(position);
                        i.putExtra("plan", plan);
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
