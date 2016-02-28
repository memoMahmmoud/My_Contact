package com.example.mai_.mycontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mai_ on 05-Oct-15.
 */
public class UserDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="USER_CONTACT.db";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_QUERY="CREATE TABLE "+ContactUser.userInfo.TABLE_NAME+"("+ContactUser.userInfo.USER_NAME+" TEXT,"+
            ContactUser.userInfo.USER_MOB+" TEXT,"+
            ContactUser.userInfo.USER_EMAIL+" TEXT);";
    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public long addInformation(SQLiteDatabase db,String name, String mob, String email){
        ContentValues contentValues=new ContentValues();
        contentValues.put(ContactUser.userInfo.USER_NAME,name);
        contentValues.put(ContactUser.userInfo.USER_MOB,mob);
        contentValues.put(ContactUser.userInfo.USER_EMAIL, email);
        long row=db.insert(ContactUser.userInfo.TABLE_NAME,null,contentValues);
        return row;
    }
    public Cursor getInformation(SQLiteDatabase db){
        String[] columns={ContactUser.userInfo.USER_NAME,ContactUser.userInfo.USER_MOB,ContactUser.userInfo.USER_EMAIL};
        Cursor cursor=db.query(ContactUser.userInfo.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }
    public Cursor getContactByName(SQLiteDatabase db,String name){
        String[] projections={ContactUser.userInfo.USER_NAME,ContactUser.userInfo.USER_MOB,ContactUser.userInfo.USER_EMAIL};
        String selection=ContactUser.userInfo.USER_NAME+" LIKE ?";
        String[] selectionArgs={name};
        Cursor cursor=db.query(ContactUser.userInfo.TABLE_NAME, projections, selection, selectionArgs, null, null, null);
        return cursor;
    }
    public void deleteContactsByName(SQLiteDatabase db,String name){
        String whereClause=ContactUser.userInfo.USER_NAME+" LIKE ?";
        String[] whereArgs={name};
        db.delete(ContactUser.userInfo.TABLE_NAME,whereClause,whereArgs);
    }
    public void updateContact(SQLiteDatabase db,String old_name,String new_name,String new_mobile,String new_email){
        ContentValues contentValues=new ContentValues();
        contentValues.put(ContactUser.userInfo.USER_NAME, new_name);
        contentValues.put(ContactUser.userInfo.USER_MOB, new_mobile);
        contentValues.put(ContactUser.userInfo.USER_EMAIL, new_email);

        String whereClause=ContactUser.userInfo.USER_NAME+" LIKE ?";
        String[] whereArgs={old_name};
        db.update(ContactUser.userInfo.TABLE_NAME,contentValues,whereClause,whereArgs);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
