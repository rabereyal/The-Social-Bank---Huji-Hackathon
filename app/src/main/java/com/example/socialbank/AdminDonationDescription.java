package com.example.socialbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminDonationDescription extends AppCompatActivity {
    TextView view_first_name;
    TextView view_last_name;
    TextView view_category;
    TextView view_detail;
    TextView view_email;
    TextView view_phone;
    String key;


    Button eraseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_donation_description);
        Intent in = getIntent();
        String first_name = in.getStringExtra("FIRST_NAME");
        String last_name = in.getStringExtra("LAST_NAME");
        String category = in.getStringExtra("CATEGORY");
        String details = in.getStringExtra("DETAILS");
        String email = in.getStringExtra("EMAIL");
        String phone = in.getStringExtra("PHONE");
        key = in.getStringExtra("KEY");
        view_first_name = (TextView)findViewById(R.id.view_first_name);
        view_last_name = (TextView)findViewById(R.id.view_last_name);
        view_category = (TextView)findViewById(R.id.view_catagory);
        view_detail = (TextView)findViewById(R.id.view_detail);
        view_email = (TextView)findViewById(R.id.view_email);
        view_phone = (TextView)findViewById(R.id.view_phone);
        eraseBtn = (Button) findViewById(R.id.Erase_btn);


        view_first_name.setText(first_name);
        view_last_name.setText(last_name);
        view_category.setText(category);
        view_phone.setText(phone);
        view_email.setText(email);
        view_detail.setText(details);

        eraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDonation(key);
                Intent eraseIntent = new Intent(getApplicationContext(),AdminRequests.class);
                startActivity(eraseIntent);
            }
        });


    }

    private void deleteDonation(String key){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Data");
        DatabaseReference donationsRef = myRef.child("Donations");
        donationsRef.child(key).setValue(null);
    }
}

