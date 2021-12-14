package com.example.tp_dbwithroom;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository mrep;
    private LiveData<List<Contact>> mAllContact ;

    public ViewModel(Application application) {
        super(application);
        mrep= new Repository(application);
        mAllContact= mrep.getAllContact();
    }

    public void insert (Contact contact){
        mrep.insert(contact);   }

    public void deleteContact(Contact contact) {
        mrep.deleteContact(contact);
    }

    public LiveData<List<Contact>> getAllContact (){
        return mAllContact;
    }
}
