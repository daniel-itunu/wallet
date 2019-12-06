package com.example.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wallet.Room.Deposit;
import com.example.wallet.ViewModel.DepositViewModel;
import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

public class DepositActivity extends AppCompatActivity implements View.OnClickListener {
    private Button nextButton;
    private EditText editDepositAmount;
    private double depositAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        nextButton = findViewById(R.id.next);
        editDepositAmount = findViewById(R.id.edit);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //check if deposit amount is greater than NGN100.00
        if (editDepositAmount.getText().toString().equals("")) {
            Toast.makeText(this, "Deposit cannot be null", Toast.LENGTH_SHORT).show();
        } else {
            double editTextValue = (double) Integer.parseInt(editDepositAmount.getText().toString());
            depositAmount = editTextValue;
            if (editTextValue < 100.00) {
                Toast.makeText(this, "Deposit amount should be greater than NGN100.00", Toast.LENGTH_SHORT).show();
            } else {
                //perform deposit via flutterwave rave
                performDeposit();
            }
        }
    }

    //perform deposit via flutterwave rave
    public void performDeposit() {
        RavePayManager ravePayManager = new RavePayManager(this);
        ravePayManager.setAmount(depositAmount)
                .setCountry("NG")
                .setEmail("danielsoft4ever@gmail.com")
                .onStagingEnv(true)
                .setfName("paul")
                .setlName("pogba")
                .setNarration("Deposit")
                .shouldDisplayFee(true)
                .showStagingLabel(true)
                .setTxRef(String.valueOf(System.currentTimeMillis()))
                .setEncryptionKey("58f5e669011a916eb20ce5b6")
                .setCurrency("NGN")
                .setPublicKey("FLWPUBK-7e1555b78d671863d9431c0c8f569b8b-X")
                .acceptCardPayments(true).initialize();
    }

    //get result back to calling activity after performing transaction
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //get viewmodel object reference
        DepositViewModel viewModel = new DepositViewModel(getApplication());
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            //if deposit is successful
            //make a toast success message and
            //insert deposit amount into database
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS ", Toast.LENGTH_SHORT).show();
                //perform database transaction in background
                new Thread() {
                    @Override
                    public void run() {
                        viewModel.insert(new Deposit(depositAmount));
                    }
                }.start();

//                Intent intent = new Intent(DepositActivity.this, MainActivity.class);
//                startActivity(intent);
                finish();
            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR "+message, Toast.LENGTH_SHORT).show();
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED ", Toast.LENGTH_SHORT).show();
            }

        }
    }


}
