package com.example.socialbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResquestList extends AppCompatActivity {
    ListView items_listView;
    ArrayList<String> categories = new ArrayList<String>();
    ArrayList<String> descriptions = new ArrayList<String>();
    ItemAdapter itemAdapter;
    ArrayList<Request> requests = new ArrayList<>();
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resquest_list);

        final ArrayList<Request> requests = getRequests();

        items_listView = (ListView) findViewById(R.id.reqeust_list);
        itemAdapter = new ItemAdapter(this, categories, descriptions);
        items_listView.setAdapter(itemAdapter);
        iv = (ImageView) findViewById(R.id.visible);
        items_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                iv.setVisibility(View.VISIBLE);
            }
        });

    }

    public ArrayList<Request> getRequests() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Data");
        DatabaseReference donationsRef = myRef.child("Donations");
        donationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Request request = snapshot.getValue(Request.class);
                    requests.add(request);
                }
                categories.clear();
                descriptions.clear();
                Intent in = getIntent();
                for (int i = 0; i < requests.size(); i++) {
                    if (requests.get(i).category.equals(in.getStringExtra("CATEGORY"))) {
                        categories.add(requests.get(i).category);
                        descriptions.add(requests.get(i).details);
                    }
                }
                itemAdapter = new ItemAdapter(ResquestList.this, categories, descriptions);
                items_listView.setAdapter(itemAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return requests;
    }
}



