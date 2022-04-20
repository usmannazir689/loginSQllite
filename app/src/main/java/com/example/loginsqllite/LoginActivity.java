package com.example.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText username,password;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.usernamelogin);
        password = (EditText)findViewById(R.id.passwordlogin);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        myDB = new DBHelper(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("") || pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "please enter the credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                   Boolean result=myDB.checkusernamepassword(user,pass);
                   if (result==true)
                   {
                       Intent intent =new Intent(getApplicationContext(),Homeactivity.class);
                       startActivity(intent);
                   }
                   else 
                   {
                       Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });


    }

}