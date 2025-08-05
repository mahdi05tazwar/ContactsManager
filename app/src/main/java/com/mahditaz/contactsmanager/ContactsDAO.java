package com.mahditaz.contactsmanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactsDAO {

    // A DAO (DATABASE ACCESS OBJECT) INTERFACE DECLARES METHODS FOR MODIFYING A TABLE (CHECK CONTACTS CLASS FIRST)

    @Insert void insert(Contacts contact);
    @Delete void delete(Contacts contact);
    @Query("SELECT * FROM Contacts") LiveData<List<Contacts>> getAllContacts();
    @Update void update(Contacts contact);


}
