package com.example.tp_dbwithroom;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;

@Database(entities = {Contact.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB instance;
    public abstract DAO dao();

    public static  RoomDB getDatabase(final Context context) {
        if (instance == null) {
            synchronized (RoomDB.class) {
                if (instance == null) {
                    // Create database here
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, "contact")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(roomcallback)
                            .build();

                }
            }
        }
        return instance;
    }
    private static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDataAsyncTask(instance).execute();
        }
    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void,Void,Void>{
        private DAO mdao;

        public PopulateDataAsyncTask(RoomDB db) {
            mdao = db.dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            mdao.insert(new Contact("trabelsi","dhia","97322333","sfax"));
            mdao.insert(new Contact("moez","mefteh","97322333","tunis"));
            return null;
        }
    }
}
