package com.example.sample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp_Activity extends AppCompatActivity {
    EditText mFullName,mEmail,mPassword,mPhoneNumber;
    Button confusion;
    TextView btnlogin1;
    FirebaseAuth fAuth;
    ProgressBar mprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        getSupportActionBar().hide();
        btnlogin1 = findViewById(R.id.btnlogin1);
        mFullName = findViewById(R.id.mFullName);
        mEmail = findViewById(R.id.mEmail);
        mPassword = findViewById(R.id.mPassword);
        mPhoneNumber = findViewById(R.id.mPhoneNumber);
        confusion = findViewById(R.id.confusion);
        fAuth = FirebaseAuth.getInstance();
        mprogressBar = findViewById(R.id.mprogressBar);

        btnlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp_Activity.this, SignIn_Activity.class);
                startActivity(intent);

            }
        });

        confusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 6) {
                    mPassword.setError("Password Must Be greater than 6 digits");
                    return;
                }

                mprogressBar.setVisibility(View.VISIBLE);

               fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()) {
                           Toast.makeText(SignUp_Activity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                           finish();
                       }else {
                           Toast.makeText(SignUp_Activity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(),MainActivity.class));
                           finish();
                       }
                   }
               });
                if(fAuth.getCurrentUser() != null){
                    Toast.makeText(SignUp_Activity.this, "You are Already a User . Please Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),SignIn_Activity.class));
                    finish();
                }
            }

        });

    }
}
