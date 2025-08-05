package com.mahditaz.contactsmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mahditaz.contactsmanager.databinding.ActivityEditContactBinding;

public class EditContactActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityEditContactBinding binding;
    Contacts contact;
    MyViewModel viewModel;
    String unchanged_name, unchanged_email, unchanged_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        contact = StaticData.currentContact;
        unchanged_name = contact.getName();
        unchanged_email = contact.getEmail();
        unchanged_number = contact.getNumber();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_contact);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.setContact(contact);
        binding.editBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // TWO-WAY DATA-BINDING NOT ENABLED SO THAT DATA ISN'T STORED IN THE CONTACTS OBJECT IF ANY OF THE IF-STATEMENTS ARE TRUE, OR IF THE TICK ISN'T EVEN CLICKED.
        String newName = binding.editNameField.getText().toString();
        if (newName.isEmpty()) {
            Toast.makeText(this, "Name cannot be empty!", Toast.LENGTH_SHORT).show();
        }
        else{
            String newEmail = binding.editEmailField.getText().toString();
            String newNumber = binding.editNumberField.getText().toString();
            if (!newNumber.isEmpty()) {
                newNumber= newNumber.replace("-", "");
                newNumber = newNumber.replace(" ", "");
            }
            contact.setName(newName);
            contact.setEmail(newEmail);
            contact.setNumber(newNumber);
            viewModel.updateContact(contact);
            Toast.makeText(this, "Contact updated successfully!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}