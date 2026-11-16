package com.mahditaz.contactsmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mahditaz.contactsmanager.util.ClickHandlers;
import com.mahditaz.contactsmanager.R;
import com.mahditaz.contactsmanager.data.MyViewModel;
import com.mahditaz.contactsmanager.databinding.ActivityAddContactBinding;
import com.mahditaz.contactsmanager.db.Contacts;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityAddContactBinding binding;
    Contacts contact;
    ClickHandlers clickHandler;
    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_contact);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        contact = new Contacts("","", "");
        clickHandler = new ClickHandlers(this, contact, viewModel);
        binding.setContact(contact);
        //binding.setClickHandler(clickHandler);
        binding.addConFAB.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (contact.getName().isEmpty())
            Toast.makeText(this, "Please provide a name!", Toast.LENGTH_SHORT).show();
        else {
            if (!contact.getNumber().isEmpty()) {
                contact.setNumber(contact.getNumber().replace("-", ""));
                contact.setNumber(contact.getNumber().replace(" ", ""));
            }
            viewModel.addContact(contact);
            Toast.makeText(this, "Contact added successfully!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}