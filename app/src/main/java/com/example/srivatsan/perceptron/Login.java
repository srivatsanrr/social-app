package com.example.srivatsan.perceptron;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button forgotpassword;
    private Button login;
    private Button signup; //corresponding id is Bsup


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name  = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.pwd);
        forgotpassword = (Button)findViewById(R.id.Bforgotpassword);
        signup = (Button)findViewById(R.id.Bsup);
        login = (Button)findViewById(R.id.Blogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString(), password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, registrationActivity.class));
            }
        });

    }
    private void validate(String userName, String userPassword){ //firebase
        Intent intent = new Intent(Login.this, Feeds.class);
        startActivity(intent);

//
    }
}

