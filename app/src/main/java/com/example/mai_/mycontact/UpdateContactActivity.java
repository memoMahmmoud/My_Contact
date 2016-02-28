package com.example.mai_.mycontact;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class UpdateContactActivity extends AppCompatActivity {
    EditText search_name;
    UserDbHelper userDbHelper;
    SQLiteDatabase db;
    EditText update_name;
    EditText update_mobile;
    EditText update_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        search_name= (EditText) findViewById(R.id.search_name);
        update_name= (EditText) findViewById(R.id.contactName);
        update_mobile= (EditText) findViewById(R.id.contactMobile);
        update_email= (EditText) findViewById(R.id.contactEmail);
    }
    public void search(View view){
        String name=search_name.getText().toString();
        userDbHelper=new UserDbHelper(this);
        db=userDbHelper.getReadableDatabase();
        Cursor cursor=userDbHelper.getContactByName(db, name);
        if (cursor.moveToFirst()){
            update_name.setText(cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_NAME)));
            update_mobile.setText(cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_MOB)));
            update_email.setText(cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_EMAIL)));
        }
    }
    public void updateContact(View view){
        userDbHelper=new UserDbHelper(this);
        db=userDbHelper.getWritableDatabase();
        String old_name=search_name.getText().toString();
        String new_name=update_name.getText().toString();
        String new_mob=update_mobile.getText().toString();
        String new_email=update_email.getText().toString();
        userDbHelper.updateContact(db,old_name,new_name,new_mob,new_email);
    }

}
