package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

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
    int menuPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Instantiate variables
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);
        planList = new ArrayList<>();

        // Setup database listener
        databaseList = FirebaseDatabase.getInstance().getReference("plans");
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        // List View and its handlers
        lvPlans = findViewById(R.id.planList);
        lvPlans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ViewPlans.this, ViewPlannedEvents.class);
                Plan plan = planList.get(position);
                i.putExtra("plan", plan);
                startActivity(i);
            }
        });

        lvPlans.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu menu = new PopupMenu(ViewPlans.this, view);
                menu.getMenuInflater().inflate(R.menu.plans_popup, menu.getMenu());
                menuPosition = position;
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("Edit")) {
                            Intent i = new Intent(ViewPlans.this, EditPlan.class);
                            Plan plan = planList.get(menuPosition);
                            i.putExtra("plan", plan);
                            startActivity(i);
                        } else if (item.getTitle().equals("Delete")) {
                            databaseList.child(planList.get(menuPosition).getKey()).removeValue();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });
    }
}
