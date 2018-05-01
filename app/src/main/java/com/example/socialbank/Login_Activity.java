package com.example.socialbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Login_Activity extends AppCompatActivity {
    EditText emailTextView;
    EditText passwordTextView;
    Button confirmBtn;

    Intent newIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        emailTextView = (EditText) findViewById(R.id.email);
        passwordTextView = (EditText) findViewById(R.id.password);
        confirmBtn = (Button) findViewById(R.id.confirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = emailTextView.getText().toString();
                final String password = passwordTextView.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Data");
                DatabaseReference usersRef = myRef.child("Users");
                usersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            User user = snapshot.getValue(User.class);
                            if (user.email.equals(userName)) {
                                if (user.password.equals(password)) {
                                    if (user.isAdmin) {
                                        newIntent = new Intent(getApplicationContext(), AdminRequests.class);

                                    } else {
                                        newIntent = new Intent(getApplicationContext(), ResquestList.class);
                                        newIntent.putExtra("CATEGORY", user.category);
                                    }
                                    startActivity(newIntent);

                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                    });
            }
        });
    }
}
