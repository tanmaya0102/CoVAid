package com.tanmaya.covaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterUser extends AppCompatActivity {
ImageView imageView;
FirebaseAuth firebaseAuth;
EditText ETEmail,ETPassword,ETName,ETAge;
String email,password,name,age;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        imageView=findViewById(R.id.imageView6);
        ETEmail=findViewById(R.id.email);
        ETPassword=findViewById(R.id.password);
        ETName=findViewById(R.id.name);
        button=findViewById(R.id.reg);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent=new Intent(RegisterUser.this,AppActivity.class);
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
                name=ETName.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterUser.this, "SignUp Error", Toast.LENGTH_SHORT).show();
                        } else {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                            user.updateProfile(userProfileChangeRequest);
                            Toast.makeText(RegisterUser.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                            RegisterUser.this.startActivity(new Intent(RegisterUser.this, AppActivity.class));
                            RegisterUser.this.finish();
                        }
                    }
                });


            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterUser.this,LoginUser.class);
                startActivity(intent);
            }
        });

    }
}