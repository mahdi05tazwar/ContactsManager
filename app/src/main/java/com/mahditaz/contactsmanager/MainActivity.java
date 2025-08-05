package com.mahditaz.contactsmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.Toast;

import com.mahditaz.contactsmanager.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    ClickHandlers clickHandlers;
    RecyclerView RV;
    RVAdapter adapter;
    ContactsDB db;
    MyViewModel viewModel;
    ArrayList<Contacts> contacts = new ArrayList<>();
    Copier copier;
    ClipboardManager clipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        StaticData.clipboard = clipboard;

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        clickHandlers = new ClickHandlers(this);
        mainBinding.setClickHandlers(clickHandlers);

        RV = mainBinding.RV;
        RV.setLayoutManager(new LinearLayoutManager(this));
        RV.setHasFixedSize(true); // HEIGHT AND WIDTH OF EVERY ITEM WILL BE THE SAME



        copier = new Copier() {
            @Override public void copyToClipboard(String label, String text) {
                ClipData clip = ClipData.newPlainText(label, text);
                clipboard.setPrimaryClip(clip);}
        };
        adapter = new RVAdapter(contacts, this, copier);




        db = ContactsDB.getInstance(this); // IT IS A SINGLETON SO ONLY ONE INSTANCE IS USED. EXPLAINS WHY THIS VARIABLE
        // ISN'T DIRECTLY USED ANYWHERE; IT'S JUST FOR INITIALISING THE LONE INSTANCE.
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);


        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> currentContacts) {
                contacts.clear();
                for (Contacts c: currentContacts) contacts.add(c);
                adapter.setContacts(contacts);

                int numContacts = contacts.size();
                mainBinding.contactsCountText.setText(numContacts + " contacts found");
                //adapter.notifyDataSetChanged(); // ORIGINAL; I DIDN'T UNDERSTAND HOW IT WAS WORKING HENCE I RATHER USED THE ONE ABOVE.
            }
        });

        /*Contacts contact1 = new Contacts("Walter White", "waltuh@meth.com", "+880123456789");
        viewModel.addContact(contact1);*/

        RV.setAdapter(adapter);

        // SWIPE FUNCTIONALITY
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) { // 0 MEANS NO DRAG-AND-DROP IS SUPPORTED.
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contacts contact = contacts.get(viewHolder.getAdapterPosition());
                viewModel.deleteContact(contact);
                Toast.makeText(MainActivity.this, "Contact deleted! (Name: " + contact.getName() + ")", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(RV);
    }
}