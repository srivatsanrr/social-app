package com.example.srivatsan.perceptron;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class registrationActivity extends AppCompatActivity {
    private EditText userName, userPass, userFullName, userPassVerify, userEmail;
    private TextView userLogin;
    private Button signUp;
    private CheckBox tnc;
private FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setUIViews();
        firebaseauth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate(userName.getText().toString(), userPass.getText().toString(), userPassVerify.getText().toString(), userFullName.getText().toString(), userEmail.getText().toString(), tnc.getLinksClickable())){
                    //upload data to database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPass.getText().toString();
                    firebaseauth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(registrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(registrationActivity.this, Login.class));
                            }
                            else
                            {
                                Toast.makeText(registrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                    });

                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registrationActivity.this, Login.class));
            }
        });
    }
    private void setUIViews(){
        userName  = (EditText)findViewById(R.id.tvusername);
        userPass = (EditText)findViewById(R.id.tvpass);
        userPassVerify = (EditText) findViewById(R.id.tvrepass);

        userFullName = (EditText) findViewById(R.id.tvfullname);
        userEmail = (EditText) findViewById(R.id.tvemailid);
        signUp = (Button)findViewById(R.id.bsup);
        userLogin = (TextView) findViewById(R.id.tvlogin);
    }

    private Boolean validate(String name, String pwd1, String pwd2, String FullName, String email, Boolean tnc) {
        //scope to optimise code- handles most use cases though
        Boolean flg1=true, flg2=true, flg3=true, flg4=true, flg5=true, flg6=true, flg7= true;
        if (!pwd1.equals(pwd2)) {
            Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
            flg1=false;
        }
        if (tnc==false){
            Toast.makeText(this, "Please read and agree to the terms and conditions.", Toast.LENGTH_SHORT).show();
            flg7=false;
        }
        if (name.isEmpty() || name.contains(" ")) {
            Toast.makeText(this, "Please enter a valid username (No spaces).", Toast.LENGTH_SHORT).show();
            flg2=false;
        }
        if (pwd1.isEmpty() || pwd2.isEmpty()) {
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
            flg3=false;
        }
        if (FullName.isEmpty()) {
            flg4=false;
            Toast.makeText(this, "Please fill your Name", Toast.LENGTH_SHORT).show();
        }
        if (FullName.length() == 1) {
            flg5 = false;
            Toast.makeText(this, "Name must be atleast 2 characters", Toast.LENGTH_SHORT).show();
        }
        if (email.isEmpty()) {
            flg6=false;
            Toast.makeText(this, "Please fill your email id.", Toast.LENGTH_SHORT).show();
        }
        return (flg1&&flg2&&flg3&&flg4&&flg5&&flg6&&flg7);
    }
}
