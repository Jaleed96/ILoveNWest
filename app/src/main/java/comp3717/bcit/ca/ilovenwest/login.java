package comp3717.bcit.ca.ilovenwest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    private DatabaseReference databaseList;
    private User attempt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Get database reference
        databaseList = FirebaseDatabase.getInstance().getReference("users");

        SharedPreferences sp1 = this.getSharedPreferences("Login", MODE_PRIVATE);

        String userId = sp1.getString("userId", null);
        String email = sp1.getString("email", null);

        if (userId != null && email != null) {
            goToHomePage();
        }
    }

    public void onLogin(View v) {
        EditText email = findViewById(R.id.emailInput);
        EditText password = findViewById(R.id.passwordInput);

        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            attempt = new User(email.getText().toString(), password.getText().toString());
            Query test = databaseList.orderByChild("users");
            test.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot post : dataSnapshot.getChildren()) {
                        User user = post.getValue(User.class);
                        if (user.getEmail().equals(attempt.getEmail())) {
                            if (user.getPassword().equals(attempt.getPassword())) {
                                processLogin(post.getKey(), attempt.getEmail());
                            } else {
                                processFailedLogin("Incorrect Password");
                            }
                            return;
                        }
                    }
                    processFailedLogin("User does not currently exist. Please sign up.");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }

    public void onSignUp(View v) {
        EditText email = findViewById(R.id.emailInput);
        EditText password = findViewById(R.id.passwordInput);

        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            // Save to the database
            String id = databaseList.push().getKey();
            User guest = new User(id, email.getText().toString(), password.getText().toString());
            databaseList.child(id).setValue(guest);

            processLogin(id, email.getText().toString());
        }
    }

    private void processLogin(String userId, String email) {
        // Save shared preferences
        SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor Ed = sp.edit();
        Ed.putString("userId", userId);
        Ed.putString("email", email);
        Ed.apply();

        // Toast message and then go to Homepage
        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
        goToHomePage();
    }

    private void goToHomePage() {
        Intent i = new Intent(this, Homepage.class);
        startActivity(i);
        finish();
    }

    private void processFailedLogin(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
