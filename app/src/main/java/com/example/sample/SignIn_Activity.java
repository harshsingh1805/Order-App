package com.example.sample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn_Activity extends AppCompatActivity {
    public static final int GOOGLE_SIGN_IN_CODE = 10005;
    EditText email1,provault;
    Button harsh;
    TextView btnregister1;
    ProgressBar youlu;
    FirebaseAuth fAuth;
    SignInButton magic;
    GoogleSignInOptions gso;
    GoogleSignInClient signInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_);



        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
     signInClient = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            Toast.makeText(SignIn_Activity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
        }

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();

        btnregister1 = findViewById(R.id.btnregister1);
        harsh = findViewById(R.id.harsh);
        email1 = findViewById(R.id.email1);
        provault = findViewById(R.id.provault);
        magic = findViewById(R.id.magic);
        youlu = findViewById(R.id.youlu);
        fAuth = FirebaseAuth.getInstance();

        btnregister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
            });
        magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = signInClient.getSignInIntent();
                startActivityForResult(sign , GOOGLE_SIGN_IN_CODE);
            }
        });

        harsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email1.getText().toString().trim();
                String password = provault.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    email1.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    provault.setError("Password is Required");
                    return;
                }
                if(password.length() < 6) {
                    provault.setError("Password Must Be greater than 6 digits");
                    return;
                }

                youlu.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignIn_Activity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                        } else {
                            Toast.makeText(SignIn_Activity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            youlu.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                    }
                });

            }
        });

        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_SIGN_IN_CODE) {
            Task<GoogleSignInAccount> signInTask = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount signInAcc = signInTask.getResult(ApiException.class);

                Toast.makeText(this, "Your Google Acoount Is Connected !", Toast.LENGTH_SHORT);
            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
}

}

