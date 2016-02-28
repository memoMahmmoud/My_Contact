package com.example.mai_.mycontact;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class ViewAllContact extends AppCompatActivity {
    //ListView listView;
    //UserAdapter userAdapter;
    //SQLiteDatabase sqLiteDatabase;
    //UserDbHelper userDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_contact);
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
        /*listView= (ListView) findViewById(R.id.listView);
        userAdapter=new UserAdapter(this,R.layout.row_layout);
        listView.setAdapter(userAdapter);
        userDbHelper=new UserDbHelper(this);
        sqLiteDatabase=userDbHelper.getReadableDatabase();
        Cursor cursor=userDbHelper.getInformation(sqLiteDatabase);
        if (cursor.moveToFirst()){
            do {
               User user=new User(cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_NAME)),
                       cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_MOB)),
                       cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_EMAIL)));
                userAdapter.add(user);
            }while (cursor.moveToNext());
        }
        userDbHelper.close();
        */
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute("get_info");
    }

}
