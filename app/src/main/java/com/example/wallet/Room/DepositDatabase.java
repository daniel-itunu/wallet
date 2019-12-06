package com.example.wallet.Room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Deposit.class}, version = 1, exportSchema = false)
public abstract class DepositDatabase extends RoomDatabase {
    private static volatile DepositDatabase INSTANCE;

    public static DepositDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DepositDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DepositDatabase.class, "database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract DepositDao getDepositDao();
}