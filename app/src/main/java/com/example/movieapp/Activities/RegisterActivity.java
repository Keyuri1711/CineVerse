package com.example.movieapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText f_name, l_name, user_password, c_password, user_email;
    private Button registerbtn, login1btn;
    private FirebaseAuth movieAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        f_name = findViewById(R.id.fname);
        l_name = findViewById(R.id.lname);
        user_email = findViewById(R.id.email);
        user_password = findViewById(R.id.password);
        c_password = findViewById(R.id.cpassword);

        registerbtn = findViewById(R.id.registerbtn);
        login1btn = findViewById(R.id.lButton);

        movieAuth = FirebaseAuth.getInstance();

        login1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String fname = f_name.getText().toString().trim();
        String lname = l_name.getText().toString().trim();
        String password = user_password.getText().toString().trim();
        String email = user_email.getText().toString().trim();
        String cpassword = c_password.getText().toString().trim();

        if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()){
            Toast.makeText(this, "All details should be filled !!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length()<8){
            user_password.setError("Password should be more than 8 char");
            user_password.requestFocus();
            return;
        }

        if(!password.equals(cpassword)){
            c_password.setError("Password and confirmation is not match");
            c_password.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            user_email.setError("Please Put the valid Email address");
            user_email.requestFocus();
            return;
        }

        movieAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Register Successfully :) ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}