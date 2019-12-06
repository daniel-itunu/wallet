package com.example.wallet.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.wallet.Room.Deposit;
import com.example.wallet.Room.DepositDao;
import com.example.wallet.Room.DepositDatabase;

import java.util.List;


public class Repository {

    private DepositDao depositDao;
    private LiveData<List<Deposit>> depositList;
    private DepositDatabase db;

    public Repository(Application application) {
        db = DepositDatabase.getDatabase(application);
        depositDao = db.getDepositDao();
        depositList = depositDao.getDeposits();
    }

    public LiveData<List<Deposit>> getAllDeposits() {
        return depositList;
    }

    public void insert(Deposit deposit) {
        new Thread() {
            @Override
            public void run() {
                db.getDepositDao().insert(deposit);
            }
        }.start();

    }
}
