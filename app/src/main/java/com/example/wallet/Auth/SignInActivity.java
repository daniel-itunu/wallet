package com.example.wallet.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wallet.MainActivity;
import com.example.wallet.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Animator signinAnimator = AnimatorInflater.loadAnimator(this, R.animator.animate);
        signinAnimator.setTarget(signIn);
        signinAnimator.start();
        if( !username.getText().toString().equals("") && !password.getText().toString().equals("")
                && username.getText().toString().equals("username") && password.getText().toString().equals("password")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this,"signed in successfully", Toast.LENGTH_LONG ).show();
        } else {
            Toast.makeText(this,"sign in with text: username and text: password", Toast.LENGTH_LONG ).show();
        }
    }
}
