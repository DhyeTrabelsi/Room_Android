package com.example.tp_dbwithroom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private DAO mdao;
    private LiveData<List<Contact>> mAllContact ;

    public Repository(Application app) {
        RoomDB db=RoomDB.getDatabase(app);
        mdao = db.dao();
        mAllContact=mdao.getAllcontact();
    }
    public LiveData<List<Contact>> getAllContact (){
        return mAllContact;
    }

    public void insert (Contact contact){
        new InsertAsyncTask(mdao).execute(contact);
    }

    public void deleteContact (Contact contact){
        new DeleteAsyncTask(mdao).execute(contact);

    }




    private static class InsertAsyncTask extends AsyncTask<Contact,Void,Void>{
        private DAO madao;

        public InsertAsyncTask(DAO dao) {
            madao = dao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            madao.insert(contacts[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Contact,Void,Void>{
        private DAO madao;

        public DeleteAsyncTask(DAO dao) {
            madao = dao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            madao.deleteContact(contacts[0]);
            return null;
        }
    }

}
