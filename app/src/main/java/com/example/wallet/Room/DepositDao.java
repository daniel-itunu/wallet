package com.example.wallet.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DepositDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Deposit deposit);

    @Query("SELECT * FROM deposit")
    LiveData<List<Deposit>> getDeposits();
}
