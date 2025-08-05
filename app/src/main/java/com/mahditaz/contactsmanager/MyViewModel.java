package com.mahditaz.contactsmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    // ANDROIDVIEWMODEL IS A VIEWMODEL THAT CONTAINS AN APPLICATION CONTEXT.

    private Repository repo;


    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repo = new Repository(application);
    }

    public LiveData<List<Contacts>> getAllContacts() {return repo.getAllContacts();}
    public void addContact(Contacts contact){repo.addContact(contact);}
    public void deleteContact(Contacts contact){repo.deleteContact(contact);}
    public void updateContact(Contacts contact){repo.updateContact(contact);}

}
