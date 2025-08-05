package com.mahditaz.contactsmanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity//(tableName = "contacts_table") //CUSTOM TABLE NAME
public class Contacts{


    // AN ENTITY IS A TABLE IN A ROOM DATABASE
    // IT ALSO REPRESENTS EACH ROW OF THE TABLE


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo//(name = "id") //CUSTOM FIELD NAME
    private int id;
    private String name, email, number;

    public Contacts(String name, String email, String number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public Contacts() {}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}
}
