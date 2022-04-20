package com.example.loginsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password,repassword;
    Button btnSignup,btnSignin;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);
        btnSignin=(Button)findViewById(R.id.btnSignin);
        btnSignup=(Button)findViewById(R.id.btnSignup);
        myDB = new DBHelper(this);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if (user.equals("") || pass.equals("")  || repass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "All the field required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (pass.equals(repass))
                    {
                        Boolean usercheckresult =myDB.checkusername(user);
                        if (usercheckresult == false)
                        {
                            Boolean regResult = myDB.insertdata(user,pass);
                            if (regResult == true)
                            {
                                Toast.makeText(MainActivity.this, "Registration sucessfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                            else 
                            {
                                Toast.makeText(MainActivity.this, "Registration false", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "User already exist. \n please Sign in", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}