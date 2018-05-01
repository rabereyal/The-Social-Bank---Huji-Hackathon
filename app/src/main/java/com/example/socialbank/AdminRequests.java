package com.example.socialbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class AdminRequests extends AppCompatActivity {
    ListView items_listView;
    ArrayList<String> catagories = new ArrayList<String>();
    ArrayList<String> descriptions = new ArrayList<String>();
    ArrayList<String> keys = new ArrayList<String>();
    Button newReqBtn;
    ItemAdapter itemAdapter;
    ArrayList<Request> requests = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_requests);

       final ArrayList<Request> requests = getRequests();

        items_listView = (ListView) findViewById(R.id.reqeust_list);
        itemAdapter = new ItemAdapter(this, catagories, descriptions);
        items_listView.setAdapter(itemAdapter);
        newReqBtn = (Button) findViewById(R.id.newresq);
        newReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddIntent = new Intent(getApplicationContext(), NewDonation.class);
                startActivity(goToAddIntent);
            }
        });

            items_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent showDetailIntent = new Intent(getApplicationContext(), AdminDonationDescription.class);
                    Request req = requests.get(position);
                    showDetailIntent.putExtra("FIRST_NAME", req.firstName);
                    showDetailIntent.putExtra("LAST_NAME", req.lastName);
                    showDetailIntent.putExtra("ADDRESS", req.address);
                    showDetailIntent.putExtra("CATEGORY", req.category);
                    showDetailIntent.putExtra("DETAILS", req.details);
                    showDetailIntent.putExtra("EMAIL", req.email);
                    showDetailIntent.putExtra("PHONE", req.phone_number);
                    showDetailIntent.putExtra("KEY", keys.get(position));

                    startActivity(showDetailIntent);
                }
            });

    }

    public ArrayList<Request> getRequests() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Data");
        DatabaseReference donationsRef = myRef.child("Donations");
        //final ArrayList<Request> requests = new ArrayList<>();
        donationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Request request = snapshot.getValue(Request.class);
                    requests.add(request);
                    keys.add(snapshot.getKey());
                }
                catagories.clear();
                descriptions.clear();
                for (int i=0; i<requests.size(); i++)
                {
                    catagories.add(requests.get(i).category);
                    descriptions.add(requests.get(i).details);

                }
                itemAdapter = new ItemAdapter(AdminRequests.this, catagories, descriptions);
                items_listView.setAdapter(itemAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return requests;
    }
}

