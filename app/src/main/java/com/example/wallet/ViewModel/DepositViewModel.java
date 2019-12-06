package com.example.wallet.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.wallet.Repository.Repository;
import com.example.wallet.Room.Deposit;
import java.util.List;

public class DepositViewModel extends AndroidViewModel {
    static LiveData<List<Deposit>> depositLiveData;
    private Repository repository;


    public DepositViewModel(Application application) {
        super(application);
        repository = new Repository(application);
        depositLiveData = repository.getAllDeposits();
    }

    public LiveData<List<Deposit>> getDepositLiveData() {
        return depositLiveData;
    }

    public void insert(Deposit deposit) {
        repository.insert(deposit);

    }

//    public double sum(){
//        double sum = 0;
//        for (int i = 0; i < depositLiveData.size(); i++) {
//            double balance = depositLiveData.get(i).getAmount();
//            sum = balance + total;
//    }
}
