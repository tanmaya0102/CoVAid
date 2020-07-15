package com.tanmaya.covaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginUser extends AppCompatActivity {
    EditText ETEmail,ETPassword;
    String email,password;
    FirebaseAuth firebaseAuth;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        ETEmail=findViewById(R.id.email);
        ETPassword=findViewById(R.id.password);
        button=findViewById(R.id.button2);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent=new Intent(LoginUser.this,AppActivity.class);
            startActivity(intent);
            finish();
            return;
        } else {
            // No user is signed in
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=ETEmail.getText().toString();
                password=ETPassword.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginUser.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(LoginUser.this, "Login Error", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(LoginUser.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            LoginUser.this.startActivity(new Intent(LoginUser.this, AppActivity.class));
                            LoginUser.this.finish();
                        }

                    }
                });
            }
        });

    }
}