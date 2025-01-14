package com.example.CryptoChat.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.CryptoChat.R;
import com.example.CryptoChat.common.data.exceptions.ObjectNotExistException;
import com.example.CryptoChat.common.data.fake.FakeContactProvider;
import com.example.CryptoChat.common.data.models.User;

public class ContactSettingsController extends AppCompatActivity {

    protected Menu menu;
    protected String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        uid = (String) intent.getStringExtra("uid");

        setContentView(R.layout.activity_contact_settings);

        EditText alias = findViewById(R.id.edit_contact_alias_text);
        alias.setText(uid);

        /*
         * TODO: Fetch user object from provider and set default alias to EditText field
         * */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.toolbar_edit_contact, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.confirm_edit_contact:
                /*
                 * TODO: Update user info here
                 * */
                try{
                    User u = FakeContactProvider.getInstance().getUser(uid);
                    EditText alias = findViewById(R.id.edit_contact_alias_text);
                    u.setAlias(alias.getText().toString());
                    FakeContactProvider.getInstance().setUser(u);
                } catch (ObjectNotExistException e) {

                }

                onBackPressed();


                break;
        }
        return true;
    }

    public static void open(Context context, String uid) {
        Intent intent = new Intent(context, ContactSettingsController.class);
        intent.putExtra("uid", uid);
        context.startActivity(intent);
    }


}
