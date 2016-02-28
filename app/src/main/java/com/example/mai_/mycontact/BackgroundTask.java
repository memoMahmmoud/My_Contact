package com.example.mai_.mycontact;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Mai_ on 11-Nov-15.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context context;
    UserDbHelper userDbHelper;
    Activity activity;
    ListView listView;
    UserAdapter userAdapter;
    public BackgroundTask(Context context) {
        this.context=context;
        activity= (Activity) context;
    }

    @Override
    protected String doInBackground(String... params) {

        String method=params[0];
        userDbHelper=new UserDbHelper(this.context);

        if (method.equals("add_information")) {
            String name = params[1];
            String mobile = params[2];
            String email = params[3];

            SQLiteDatabase sqLiteDatabase=userDbHelper.getWritableDatabase();

            long row = userDbHelper.addInformation(sqLiteDatabase, name, mobile, email);
            if(row>0)
            {
                return "row inserted correctly ";
            }
            else
                return "Error in savind data into database";
        }
        else if (method.equals("get_info")){

            SQLiteDatabase sqLiteDatabase=userDbHelper.getReadableDatabase();
            Cursor cursor=userDbHelper.getInformation(sqLiteDatabase);
            userAdapter=new UserAdapter(context,R.layout.row_layout);
            listView= (ListView) activity.findViewById(R.id.listView);
            if (cursor.moveToFirst()){
                do {
                    User user=new User(cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_NAME)),
                            cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_MOB)),
                            cursor.getString(cursor.getColumnIndex(ContactUser.userInfo.USER_EMAIL)));
                    userAdapter.add(user);

                }while (cursor.moveToNext());
                return "get_info";
            }
            userDbHelper.close();

        }
        return null;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        if (string.equals("row inserted correctly ")||string.equals("Error in savind data into database"))
            Toast.makeText(context,string,Toast.LENGTH_LONG).show();
        else if(string.equals("get_info")){
            listView.setAdapter(userAdapter);
        }

    }
}
