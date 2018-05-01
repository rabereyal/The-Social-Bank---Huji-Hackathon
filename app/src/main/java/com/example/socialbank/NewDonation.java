package com.example.socialbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewDonation extends AppCompatActivity {


    private EditText editFirstName;
    private EditText editLastName;
    private EditText edit_phone;
    private EditText editAddress;
    private EditText editEmail;
    private EditText edit_details;
    private Spinner categories;
    private Button send;
    public ArrayAdapter<String> adapter;
    public String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_donation);

        editFirstName = (EditText) findViewById(R.id.edit_first_name);
        editLastName = (EditText) findViewById(R.id.seconed_edit);
        edit_details = (EditText)findViewById(R.id.edit_details);
        editAddress = (EditText) findViewById(R.id.edit_address);
        editEmail = (EditText) findViewById(R.id.edit_email);
        edit_phone = (EditText)findViewById(R.id.edit_telephone);
        send = (Button) findViewById(R.id.send_btn);
        categories = (Spinner) findViewById(R.id.categoey_options);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categories));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapter);
        category = categories.getSelectedItem().toString();
        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = getResources().getStringArray(R.array.categories)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = editFirstName.getText().toString();
                String lastName = editLastName.getText().toString();
                String details = edit_details.getText().toString();
                String address = editAddress.getText().toString();
                String email = editEmail.getText().toString();
                String phone = edit_phone.getText().toString();

                Request newReq = new Request(firstName, lastName, address, email, details, category, phone);


                addNewDonation(newReq);

                Intent newIntent = new Intent(getApplicationContext(), AdminRequests.class);
                startActivity(newIntent);


            }
        });
    }

    private void addNewDonation(Request newReq) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Data");
        DatabaseReference resquestRef = myRef.child("Donations");
        DatabaseReference newDonation = resquestRef.push();
        newDonation.setValue(newReq);
    }
}
