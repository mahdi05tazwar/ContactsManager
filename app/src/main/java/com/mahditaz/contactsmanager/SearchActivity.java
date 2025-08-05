package com.mahditaz.contactsmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mahditaz.contactsmanager.databinding.ActivityMainBinding;
import com.mahditaz.contactsmanager.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;
    RecyclerView sRV;
    ArrayList<Contacts> contacts = new ArrayList<>();
    MyViewModel viewModel;
    SearchRVAdapter adapter;
    Copier copier;
    ClipboardManager clipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        clipboard = StaticData.clipboard;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.searchField.requestFocus();

        sRV = binding.sRV;
        sRV.setLayoutManager(new LinearLayoutManager(this));
        sRV.setHasFixedSize(true);




        copier = new Copier() {
            @Override public void copyToClipboard(String label, String text) {
                //clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(label, text);
                clipboard.setPrimaryClip(clip);}
        };
        adapter = new SearchRVAdapter(contacts, this, copier);
        adapter.setDestroyer(new Destroyer() {
            @Override public void destroy() {finish();}
        });




        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        SearchHolder sh = new SearchHolder();
        binding.setSearchholder(sh);

        sh.getLiveSearch().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                ArrayList<Contacts> currentContacts = (ArrayList<Contacts>) contacts.clone();
                if (s.equals("")) {
                    adapter.setContacts(currentContacts);
                    return;
                }

                Iterator<Contacts> it = currentContacts.iterator();
                while (it.hasNext()) {
                    Contacts c = it.next();
                    if (!c.getName().toLowerCase().contains(s.toLowerCase())
                    && !c.getNumber().toLowerCase().contains(s.toLowerCase())) it.remove();
                }
                adapter.setContacts(currentContacts);
            }
        });


        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> currentContacts) {
                contacts.clear();
                for (Contacts c: currentContacts) contacts.add(c);
            }
        });

        sRV.setAdapter(adapter);
    }
}