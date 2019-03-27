package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    private DatabaseReference databaseList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get database reference
        databaseList = FirebaseDatabase.getInstance().getReference("users");

        // Save to the database
        String id = databaseList.push().getKey();
        User guest = new User(id, "andy93@gmail.com", "password");
        databaseList.child(id).setValue(guest);
    }

    public void onLogin(View v){
        EditText email = findViewById(R.id.emailInput);
        EditText password = findViewById(R.id.passwordInput);

        if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
            Query test = databaseList.orderByChild("email").startAt(email.getText().toString());
            test.addValueEventListener( new ValueEventListener(){
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot post : dataSnapshot.getChildren() ){
                        System.out.print(post);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });
            //processLogin(id, email.getText().toString());
        }
    }

    public void onSignUp(View v){
        EditText email = findViewById(R.id.emailInput);
        EditText password = findViewById(R.id.passwordInput);

        if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
            // Save to the database
            String id = databaseList.push().getKey();
            User guest = new User(id, email.getText().toString(), password.getText().toString());
            databaseList.child(id).setValue(guest);

            processLogin(id, email.getText().toString());
        }
    }

    private void processLogin(String userId, String email){
        SharedPreferences sp=getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor Ed=sp.edit();
        Ed.putString("userId", userId);
        Ed.putString("email", email);
        Ed.apply();

        Intent i = new Intent(this, Homepage.class);
        startActivity(i);
        finish();
    }
}
