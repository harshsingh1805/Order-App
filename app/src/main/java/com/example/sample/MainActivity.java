package com.example.sample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btnlogin;
    EditText first,second;
    Button btnsignUp;
    FirebaseAuth fAuth;
    ProgressBar final1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();

        btnlogin = findViewById(R.id.btnlogin);
        btnsignUp = findViewById(R.id.btnsignUp);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        final1 = findViewById(R.id.final1);
        fAuth = FirebaseAuth.getInstance();



        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = first.getText().toString().trim();
                String password = second.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    first.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    second.setError("Password is Required");
                    return;
                }
                if(password.length() < 6) {
                   second.setError("Password Must Be greater than 6 digits");
                    return;
                }

                final1.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                        }else {
                            Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                        }
                    }
                });

                if(fAuth.getCurrentUser() != null){
                    Toast.makeText(MainActivity.this, "You are not a user . Please Register First ", Toast.LENGTH_SHORT).show();

                }

            }

        });

    }
}

