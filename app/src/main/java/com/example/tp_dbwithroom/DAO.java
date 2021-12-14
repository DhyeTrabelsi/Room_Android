package com.example.tp_dbwithroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insert(Contact contact);

    @Delete
    void deleteContact(Contact contact);

    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAllcontact();

}