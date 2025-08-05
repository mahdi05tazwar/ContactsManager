package com.mahditaz.contactsmanager;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    // COLLECTS REQUIRED DATA FROM MULTIPLE SOURCES SO THAT ALL DATA CAN BE ACCESSED USING A SINGLE CLASS.
    // IN THIS CASE, HOWEVER, THERE'S ONLY ONE SOURCE (ROOM).

    private final ContactsDAO contactsDAO;
    ExecutorService executor;
    Handler handler;

    public Repository(Application application) {
        ContactsDB db = ContactsDB.getInstance(application);
        this.contactsDAO = db.getContactDAO();
        executor = Executors.newSingleThreadExecutor(); // ALLOWS TO EXECUTE CODE ON A BACKGROUND THREAD
        handler = new Handler(Looper.getMainLooper()); // UPDATES THE UI BASED ON THE NEW THREAD'S ACTIONS (THE UI
        // IS SUPPOSED TO BE UPDATED ONLY BY THE MAIN UI THREAD, HENCE THIS IS REQUIRED).
    }

    public void addContact(Contacts contact){
        executor.execute(new Runnable() { // A runnable is an interface whose instances can run as a Thread.
            @Override public void run() {contactsDAO.insert(contact);}
        });

    }
    public void deleteContact(Contacts contact){
        executor.execute(new Runnable() {
            @Override public void run() {contactsDAO.delete(contact);}
        });
    }
    public void updateContact(Contacts contact){ // NO SEPARATE THREAD
        executor.execute(new Runnable() {
            @Override public void run() {contactsDAO.update(contact);}
        });
    }
    public LiveData<List<Contacts>> getAllContacts(){
        // THIS ONE NEEDS TO BE DONE DIFFERENTLY AS THE RUN METHOD OF RUNNABLES DON'T RETURN ANYTHING, AND IT SHOULD BE LIVE.
        // LIVE DATA RUNS ON A SEPARATE THREAD BY DEFAULT.
        return contactsDAO.getAllContacts(); // THIS ALLOWS THE REPOSITORY TO ENSURE THAT THE CONTACTS LIST IS "OBSERVABLE" TO
        // THE VIEWMODEL.
    }
}
