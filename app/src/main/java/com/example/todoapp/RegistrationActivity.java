package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        EditText name,phone,regEmail,regPwd;
        Button regBtn;
        TextView regQu;
        Toolbar toolbar;
        FirebaseAuth auth;
        ProgressDialog loader;


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        toolbar = findViewById(R.id.registerToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("REGISTRATION");

        auth = FirebaseAuth.getInstance();
        loader = new ProgressDialog(this);
        name = findViewById(R.id.userName);
        phone = findViewById(R.id.phone);
        regEmail = findViewById(R.id.registerEmail);
        regPwd = findViewById(R.id.registerPassword);
        regBtn = findViewById(R.id.registerButton);
        regQu = findViewById(R.id.registrationPageQuestion);

        regQu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString().trim();
                String phoneNumber = phone.getText().toString().trim();
                String email = regEmail.getText().toString().trim();
                String password = regPwd.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    regEmail.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    regPwd.setError("password required");
                    return;
                } else {
                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                                loader.dismiss();
                            } else {
                                String error = task.getException().toString();
                                Toast.makeText(RegistrationActivity.this, "Registration failed!" + error, Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }
            }
         });
    }
}