package com.mahditaz.contactsmanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mahditaz.contactsmanager.databinding.ContactsItemViewBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CustomViewHolder> {

    ArrayList<Contacts> contacts;
    Context context;
    Copier copier;

    public RVAdapter(ArrayList<Contacts> contacts, Context context, Copier copier) {
        this.contacts = contacts;
        sortAlphabetically();
        this.context = context;
        this.copier = copier;
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
        sortAlphabetically();
        notifyDataSetChanged(); // IT'S A RECYCLERVIEW FEATURE WHICH INFORMS THE RECYCLERVIEW THAT THE CONTACTS DATASET HAS
        // CHANGED WHENEVER THIS setContacts() METHOD IS CALLED (AS THIS METHOD WOULD CAUSE THE CHANGE), AND THE RECYCLERVIEW
        // THEREFORE REFRESHES ITSELF IN THE UI.
    }

    private void sortAlphabetically(){
        Collections.sort(contacts, new Comparator<Contacts>() {
            @Override
            public int compare(Contacts c1, Contacts c2) {
                return c1.getName().compareToIgnoreCase(c2.getName());
            }
        });
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactsItemViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.contacts_item_view, parent, false);

        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Contacts currentContact = contacts.get(position);
        holder.itemViewBinding.setContact(currentContact);
    }

    @Override
    public int getItemCount() {
        return (contacts != null) ? contacts.size() : 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ContactsItemViewBinding itemViewBinding;

        public CustomViewHolder(@NonNull ContactsItemViewBinding itemViewBinding) {
            super(itemViewBinding.getRoot());
            this.itemViewBinding = itemViewBinding;
            itemViewBinding.getRoot().setOnClickListener(this);
            itemViewBinding.getRoot().setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int currentPosition = this.getAdapterPosition();
            Contacts contact = contacts.get(currentPosition);
            StaticData.currentContact = contact;
            Intent i = new Intent(view.getContext(), ContactDetailsActivity.class);
            context.startActivity(i);
        }

        @Override
        public boolean onLongClick(View view) {
            Contacts contact = contacts.get(this.getAdapterPosition());
            try{
                copier.copyToClipboard("Copying phone number", contact.getNumber());
                Toast.makeText(context, "Phone number copied to clipboard!", Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                Toast.makeText(context, "Copying failed!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }
}
