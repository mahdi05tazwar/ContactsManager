package com.mahditaz.contactsmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mahditaz.contactsmanager.databinding.ActivityContactDetailsBinding;

public class ContactDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityContactDetailsBinding binding;
    Contacts contact;
    ClickHandlers clickHandler;
    MyViewModel viewModel;
    ClipboardManager clipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        clipboard = StaticData.clipboard;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_details);

        contact = StaticData.currentContact;
        binding.setContact(contact);

        clickHandler = new ClickHandlers(this, contact);
        binding.setClickHandler(clickHandler);
        binding.deleteFAB.setOnClickListener(this);
        binding.editFAB.setOnClickListener(this);
        binding.copyNameBtn.setOnClickListener(this);
        binding.copyEmailBtn.setOnClickListener(this);
        binding.copyNumberBtn.setOnClickListener(this);
        binding.copyAllBtn.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.deleteFAB){
            viewModel.deleteContact(contact);
            Toast.makeText(this, "Contact deleted! (Name: " + contact.getName() + ")", Toast.LENGTH_LONG).show();
            finish();
        }
        else if (id == R.id.editFAB){
            Intent i = new Intent(this, EditContactActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.copyNameBtn){
            ClipData clip = ClipData.newPlainText("Copying name", contact.getName());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Name copied to clipboard!", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.copyEmailBtn){
            ClipData clip = ClipData.newPlainText("Copying e-mail", contact.getEmail());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "E-mail copied to clipboard!", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.copyNumberBtn){
            ClipData clip = ClipData.newPlainText("Copying number", contact.getNumber());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Phone no. copied to clipboard!", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.copyAllBtn){
            String text = "Name: " + contact.getName() + "\nE-mail: " + contact.getEmail() + "\nPhone no: " + contact.getNumber();
            ClipData clip = ClipData.newPlainText("Copying all", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Name, e-mail and phone no. copied to clipboard!", Toast.LENGTH_LONG).show();
        }
    }
}