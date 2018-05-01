package com.example.socialbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
public class UserDonations extends AppCompatActivity {
    ListView items_listView;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> descriptions = new ArrayList<String>();

    ItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_donations);
            items_listView = (ListView) findViewById(R.id.items_listView);
            names.add("ביגוד");
            names.add("ריהוט");
            descriptions.add("חולצות");
            descriptions.add("כיסאות");
            itemAdapter = new ItemAdapter(this, names, descriptions);
            items_listView.setAdapter(itemAdapter);

            items_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent showDetailIntent = new Intent(getApplicationContext(), Detailed_list.class);
//                    showDetailIntent.putExtra("NAME", catagories.get(position));
//                    showDetailIntent.putExtra("DESCRIPTION", descriptions.get(position));
//
//                    startActivity(showDetailIntent);
                }
            });
        } catch (Exception e)
        {
            System.out.println(e);
        }

    }
}