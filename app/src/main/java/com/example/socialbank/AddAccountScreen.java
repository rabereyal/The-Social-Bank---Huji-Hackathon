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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class AddAccountScreen extends AppCompatActivity {

    private EditText editFirstName;
    private EditText editLastName;
    private EditText editBussinessName;
    private EditText editAddress;
    private EditText editEmail;
    private EditText edit_phone;
    private EditText editPassword;
    private Spinner categories;
    private Button send;
    String category;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_account_activitiy);
        try {

            edit_phone = (EditText)findViewById(R.id.edit_telephone);
            editFirstName = (EditText) findViewById(R.id.edit_first_name);
            editLastName = (EditText) findViewById(R.id.seconed_edit);
            editBussinessName = (EditText) findViewById(R.id.edit_beithaasek);
            editAddress = (EditText) findViewById(R.id.edit_address);
            editEmail = (EditText) findViewById(R.id.edit_email);
            editPassword = (EditText) findViewById(R.id.edit_password);
            send = (Button) findViewById(R.id.send_btn);
            categories = (Spinner) findViewById(R.id.categoey_options);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categories));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categories.setAdapter(adapter);
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
                    String bussinessName = editBussinessName.getText().toString();
                    String address = editAddress.getText().toString();
                    String email = editEmail.getText().toString();
                    String password = editPassword.getText().toString();
                    String phone = edit_phone.getText().toString();
                    User newUser = new User(firstName, lastName, bussinessName, address, email, password, category,phone);


                    addNewUser(newUser);

                    Intent newIntent = new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(newIntent);


                }
            });


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addNewUser(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Data");
        DatabaseReference usersRef = myRef.child("Users");
        DatabaseReference myUser = usersRef.push();
        myUser.setValue(user);
        /*
        myUser.child("First Name").setValue(first);
        myUser.child("Last Name").setValue(last);
        myUser.child("Bussiness").setValue(bussinessName);
        myUser.child("Address").setValue(adrs);
        myUser.child("Email").setValue(mail);
        myUser.child("Password").setValue(psw);
        myUser.child("Category").setValue(selectedCategory);
        */

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,User> usersList = (HashMap<String,User>) dataSnapshot.getValue();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
