package com.example.wallet.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "deposit")
public class Deposit {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="amount")
    private double amount;

    public  Deposit(@NonNull double amount){
        //his.id = id;
        this.amount = amount;

    }

    public double getAmount(){
        return this.amount;
    }

}
