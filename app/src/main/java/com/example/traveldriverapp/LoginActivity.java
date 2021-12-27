package com.example.traveldriverapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.traveldriverapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createUser();
            }


        });
        binding.forgotpass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(i);

            }
        });
        binding.appCompatImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, GoogleSignInActivity.class);

                startActivity(intent);
            }
        });
        binding.appCompatImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, FacebookAuthActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonRegisterHere.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);

            }
        });
    }

    private void createUser() {
        final String email = binding.editTextEmail.getText().toString().trim();
        final String password =binding.editTextpassword.getText().toString().trim();
        if (email.isEmpty()) {
            binding.editTextEmail.setError("Email Required");
            binding.editTextEmail.requestFocus();
            binding.editTextEmail.setFocusable(true);
            return;
        }
        if (password.isEmpty()) {
            binding.editTextpassword.setError("Password Required");
            binding.editTextpassword.requestFocus();
            binding.editTextpassword.setFocusable(true);
            return;
        }
        if (password.length() < 6) {
            binding.editTextpassword.setError("Password Should be atleast six character");
            binding.editTextpassword.requestFocus();
            binding.editTextpassword.setFocusable(true);
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Login Success ", Toast.LENGTH_SHORT).show();
                            startProfileActivity();

                        } else {

//                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
//                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void startProfileActivity() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

    }
}