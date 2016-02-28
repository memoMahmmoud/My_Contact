package com.example.mai_.mycontact;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
    EditText name,mob,email;
    //UserDbHelper userDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);
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
        name= (EditText) findViewById(R.id.contactName);
        mob= (EditText) findViewById(R.id.contactMobile);
        email= (EditText) findViewById(R.id.contactEmail);

        //userDbHelper=new UserDbHelper(this);
    }
    public void save(View view){
        String name=this.name.getText().toString();
        String mob=this.mob.getText().toString();
        String email=this.email.getText().toString();

        //SQLiteDatabase sqLiteDatabase=userDbHelper.getWritableDatabase();
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute("add_information",name,mob,email);
        /*long row=userDbHelper.addInformation(sqLiteDatabase,name,mob,email);
        userDbHelper.close();
        if(row>0){
            Toast.makeText(this,"Data Saved correctly",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this,"Data not saved in database !",Toast.LENGTH_LONG).show();*/


    }

}
