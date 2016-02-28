package com.example.mai_.mycontact;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SearchByName extends AppCompatActivity {
    EditText name;
    UserDbHelper userDbHelper;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);
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
        name= (EditText) findViewById(R.id.search_name);
        userDbHelper=new UserDbHelper(this);
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        listView= (ListView) findViewById(R.id.listView);

    }
    public void searchByName(View view){
        String search_name=name.getText().toString();
        Cursor cursor=userDbHelper.getContactByName(sqLiteDatabase, search_name);
        userAdapter=new UserAdapter(this,R.layout.row_layout);
        listView.setAdapter(userAdapter);
        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_MOB)),
                        cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_EMAIL)));
                userAdapter.add(user);
            } while (cursor.moveToNext());
        }

    }
    public void deleteContact(View view){
        String nameText=name.getText().toString();
        userDbHelper.deleteContactsByName(sqLiteDatabase, nameText);
        Toast.makeText(getApplicationContext(),nameText+"'s contacts deleted successfuly",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,ViewAllContact.class);
        startActivity(intent);
    }

}
