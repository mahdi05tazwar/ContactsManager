package com.mahditaz.contactsmanager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class}, version = 1)
public abstract class ContactsDB extends RoomDatabase {
    // THIS IS A DATABASE CLASS
    // CHECK CONTACTS AND CONTACTSDAO CLASS FIRST

    private static ContactsDB instance;
    public abstract ContactsDAO getContactDAO();

    public static synchronized ContactsDB getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ContactsDB.class, "contacts_db")
                    .fallbackToDestructiveMigration().build();
            /*GETAPPLICATIONCONTEXT() IS REQUIRED FOR THINGS WITH A LONG LIFECYCLE (UNLIKE THAT OF A SINGLE ACTIVITY)
            * FALLBACKTODESTRUCTIVEMIGRATION() will destructively recreate database tables if Migrations that would migrate
            * old database schemas to the latest schema version are not found*/
        }

        return instance;
    }
}
