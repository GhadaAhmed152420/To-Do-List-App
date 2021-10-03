package com.example.todoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        EditText loginEmail,loginPwd;
        Button loginBtn;
        TextView loginQu;
        Toolbar toolbar;
        FirebaseAuth auth;
        ProgressDialog loader;

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.loginToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LOGIN");
        auth = FirebaseAuth.getInstance();
        loader = new ProgressDialog(this);

        loginEmail = findViewById(R.id.loginEmail);
        loginPwd = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginButton);
        loginQu = findViewById(R.id.loginPageQuestion);

        loginQu.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(v -> {
            String email = loginEmail.getText().toString().trim();
            String password = loginPwd.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                loginEmail.setError("email is required");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                loginPwd.setError("password required");
            } else {
                loader.setMessage("Login in progress");
                loader.setCanceledOnTouchOutside(false);
                loader.show();
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String error = task.getException().toString();
                        Toast.makeText(LoginActivity.this, "Login failed!" + error, Toast.LENGTH_SHORT).show();
                    }
                    loader.dismiss();
                });
            }
        });
    }

}
