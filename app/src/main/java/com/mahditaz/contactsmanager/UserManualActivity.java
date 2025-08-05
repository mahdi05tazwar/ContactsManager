package com.mahditaz.contactsmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class UserManualActivity extends AppCompatActivity {
    RecyclerView mRV;
    UserManualItem call, delete1, confirm, delete2, share, newContact, edit, email, details, search, manual, copy, developerinfo;
    ArrayList<UserManualItem> items = new ArrayList<>();
    ManualRVAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual);

        call = new UserManualItem(R.drawable.baseline_call_24, "Calling contacts", "The button with this icon redirects you to your calling app and sets the phone number of the contact as the number to be dialed.");
        delete1 = new UserManualItem(R.drawable.baseline_delete_24, "Deleting contacts (1)", "The button with this icon deletes the current contact.");
        confirm = new UserManualItem(R.drawable.baseline_done_24, "Confirming actions", "The buttons with this icon allow you to confirm a particular action.");
        delete2 = new UserManualItem(R.drawable.baseline_delete_24, "Deleting contacts (2)", "In the home screen, swipe any contact to the left to delete it.");
        share = new UserManualItem(R.drawable.baseline_share_24, "Sharing contacts", "The button with this icon allows you to share a contact using any messaging/emailing app.");
        newContact = new UserManualItem(R.drawable.baseline_add_24, "Adding contacts", "The button with this icon allows you to add a new contact. Every contact MUST have a name.");
        edit = new UserManualItem(R.drawable.baseline_edit_24, "Editing contacts", "The button with this icon allows you to edit contacts.");
        email = new UserManualItem(R.drawable.baseline_email_24, "E-mailing contacts", "The button with this icon redirects you to your e-mailing app and sets the e-mail address of the selected contact as the receiver address.");
        details = new UserManualItem(R.drawable.baseline_person_24, "Viewing contacts", "Click on any contact to view all details. You can also edit, share, email, call or delete the contact from there.");
        search = new UserManualItem(R.drawable.baseline_search_24, "Searching contacts", "The button with this icon allows you to search for contacts (by name/number).");
        manual = new UserManualItem(R.drawable.baseline_question_mark_24, "Viewing the User Manual", "The button with this icon allows you to view the user manual (which you're currently doing).");
        copy = new UserManualItem(R.drawable.baseline_content_copy_24, "Copying", "Hold down any contact in the home screen or search screen to copy the phone number to your clipboard.");
        developerinfo = new UserManualItem(R.drawable.baseline_code_24, "Developer info", "Md Mahdi Tajwar Raeed\nLast modified: December 2023\nContact me at mahdi05tazwar@gmail.com for any issue.");
        items.add(newContact); items.add(confirm); items.add(search); items.add(manual); items.add(delete1); items.add(delete2);
        items.add(details); items.add(call); items.add(edit); items.add(email); items.add(share); items.add(copy);items.add(developerinfo);

        mRV = findViewById(R.id.mRV);
        mRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ManualRVAdapter(this, items);
        mRV.setAdapter(adapter);
    }
}