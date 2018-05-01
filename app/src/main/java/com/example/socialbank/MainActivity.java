package com.example.socialbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button signIn_btn;
    Button login_btn;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.welcome_screen);
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            signIn_btn = (Button) findViewById(R.id.newusr);
            signIn_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent moveToLogin = new Intent(getApplicationContext(), AddAccountScreen.class);
                    startActivity(moveToLogin);
                }
            });
            login_btn = (Button) findViewById(R.id.user);
            login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent moveToLogin = new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(moveToLogin);
                }
            });
        }catch (Exception e){
            System.out.println(e);
    }
}

}
