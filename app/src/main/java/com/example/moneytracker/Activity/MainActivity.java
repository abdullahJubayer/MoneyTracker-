package com.example.moneytracker.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneytracker.DB.DBHelper;
import com.example.moneytracker.ModelClass.Model_UserInfo;
import com.example.moneytracker.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText name,password;
    TextView register;
    DBHelper helper;
    ArrayList<Model_UserInfo> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        name=findViewById(R.id.UserNameEDTId);
        password=findViewById(R.id.PasswordID);
        register=findViewById(R.id.register_id);

        helper=new DBHelper(this);
        userData=helper.returnUserInfo();

        login=findViewById(R.id.LoginBtnId);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, ContainerList.class).putExtra("Tag","Setting"));
                }
            });

            login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validet()){
                    if (userData.size() == 0){
                        Toast.makeText(MainActivity.this, "No admin Found", Toast.LENGTH_SHORT).show();
                    }else{
                        String databaseName=userData.get(0).getName();
                        String databasePass=userData.get(0).getPassword();
                        String giveName=name.getText().toString();
                        String givePass=password.getText().toString();

                        if (databaseName.equals(giveName) && databasePass.equals(givePass)){
                            Intent intent=new Intent(MainActivity.this,DrawerActivity.class).putExtra("Data",userData.get(0));
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this,"UserName and Password Not Match",Toast.LENGTH_SHORT).show();
                        }
                    }
                        }
                    }
        });
    }

    private boolean validet() {
        boolean val=true;

        String n=name.getText().toString();
        String p=password.getText().toString();

        if (n.isEmpty() || n.startsWith(" ")|| n.length()<4){
            name.setError("Name Not Valid");
            val=false;
        }
        if (p.isEmpty() || n.startsWith(" ")|| p.length()<6){
            name.setError("Password Not Valid");
            val=false;
        }
        return val;
    }

    @Override
    protected void onStart() {
        super.onStart();
        userData=helper.returnUserInfo();

    }
}
