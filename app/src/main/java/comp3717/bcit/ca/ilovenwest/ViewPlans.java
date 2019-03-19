package comp3717.bcit.ca.ilovenwest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewPlans extends Activity {
    ListView lvPlans;
    ArrayList<Plan> eventList;
    DatabaseReference databaseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plan);
        lvPlans = findViewById(R.id.planList);
        eventList = new ArrayList<>();
        databaseList = FirebaseDatabase.getInstance().getReference("plans");
        dummyData();
    }
    public void dummyData(){
        String address = "123 main street";
        String name = "science world";
        Date date = new Date(2019, 11,12);

        String id = databaseList.push().getKey();
        Event e = new Event();
        e.setAddress(address);
        e.setName(name);

        Plan plan = new Plan(date, e);
        Task setValueTask = databaseList.child(id).setValue(plan);


    }

    @Override
    protected void onStart(){
        super.onStart();
        databaseList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventList.clear();
                for(DataSnapshot planSnapShot : dataSnapshot.getChildren()){
//                    Event e = planSnapShot.getValue(Event.class);
                    Log.d("println", planSnapShot.toString());
                    Plan plan = planSnapShot.getValue(Plan.class);
                    Log.d("println", planSnapShot.getValue(Plan.class).toString());
                    eventList.add(plan);
                }

                ViewPlansAdapter adapter = new ViewPlansAdapter(ViewPlans.this, eventList);
                lvPlans.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
