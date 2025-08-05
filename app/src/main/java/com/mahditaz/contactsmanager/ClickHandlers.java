package com.mahditaz.contactsmanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

public class ClickHandlers {
    Context context;
    Contacts contact;
    MyViewModel viewModel;

    public ClickHandlers() {}

    public ClickHandlers(Context context) {
        this.context = context;
    }

    public ClickHandlers(Context context, Contacts contact, MyViewModel viewModel) {
        this.context = context;
        this.contact = contact;
        this.viewModel = viewModel;
    }

    public ClickHandlers(Context context, Contacts contact) {
        this.context = context;
        this.contact = contact;
    }

    public void onFABClicked(View view){
        Intent i = new Intent(view.getContext(), AddContactActivity.class);
        context.startActivity(i);
    }

    public void onSearchFABClicked(View view){
        Intent i = new Intent(view.getContext(), SearchActivity.class);
        context.startActivity(i);
    }

    public void onUserManualFABClicked(View view){
        Intent i = new Intent(view.getContext(), UserManualActivity.class);
        context.startActivity(i);
    }

    public void onCreateConFABClicked(View view){
        if (contact.getName().equals(""))
            Toast.makeText(context, "Please provide a name!", Toast.LENGTH_SHORT).show();
        else if (contact.getNumber().equals("") || contact.getEmail().equals(""))
            Toast.makeText(context, "A e-mail address and/or a phone number is required!", Toast.LENGTH_LONG).show();
        else {
            viewModel.addContact(contact);
            Toast.makeText(context, "Contact added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onShareConFABClicked(View view){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, "Name: " + contact.getName() + "\nE-mail: " + contact.getEmail() + "\nPhone no: " + contact.getNumber());
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing a contact");
        context.startActivity(i);
    }

    public void onEmailFABClicked(View view){
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:")); // ONLY E-MAIL APPS WILL BE SHOWN
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{contact.getEmail()});
        context.startActivity(i);
    }

    public void onCallFABClicked(View view){
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:"+contact.getNumber()));
        context.startActivity(i);
    }
}
