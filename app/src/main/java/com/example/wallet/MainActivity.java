package com.example.wallet;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.wallet.Room.Deposit;
import com.example.wallet.ViewModel.DepositViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button deposit;
    private Button send;
    //private double balance;
    private TextView textView;
    private Button logoutButton;
    private double sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deposit = findViewById(R.id.depositButton);
        send = findViewById(R.id.sendButton);
        logoutButton = findViewById(R.id.logout);
        textView = findViewById(R.id.balance);
        deposit.setOnClickListener(this);
        send.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        sum=0;
        //balance = 0;
        //observer
        DepositViewModel viewModel = ViewModelProviders.of(this).get(DepositViewModel.class);
        viewModel.getDepositLiveData().observe(this, new Observer<List<Deposit>>() {
            @Override
            public void onChanged(List<Deposit> deposits) {
                for (int i = 0; i < deposits.size(); i++) {
                    sum = sum + deposits.get(i).getAmount();
                }
                
                textView.setText(String.valueOf(sum));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.depositButton:
                Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animate);
                animator.setTarget(deposit);
                animator.start();
                Intent intent = new Intent(MainActivity.this, DepositActivity.class);
                startActivity(intent);
                break;
            case R.id.sendButton:
                Animator sendAnimator = AnimatorInflater.loadAnimator(this, R.animator.animate);
                sendAnimator.setTarget(send);
                sendAnimator.start();
                Toast.makeText(MainActivity.this, "send money disabled", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Animator logoutAnimator = AnimatorInflater.loadAnimator(this, R.animator.animate);
                logoutAnimator.setTarget(logoutButton);
                logoutAnimator.start();
                finish();
                break;
        }
    }

}