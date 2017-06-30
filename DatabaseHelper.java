package com.itplus.demosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 29/05/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "CONTACT_DB";
    private final static String CONTACTS_TABLE = "CONTACTS_TABLE";
    private SQLiteDatabase mDatabase;
    private Context mContext;

    //B1: Tao CSDL
    public DatabaseHelper(Context mContext){
        super(mContext,DATABASE_NAME,null,1);
        File database = mContext.getApplicationContext().getDatabasePath("CONTACT_DB");
        if (!database.exists()) {
            mDatabase = mContext.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
            String createTableQuery = "CREATE TABLE CONTACTS_TABLE (ID	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE_NUMBER	TEXT)";
            mDatabase.execSQL(createTableQuery);
        }
        this.mContext = mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //B3: Mở kết nối
    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DATABASE_NAME).getPath();
        if(mDatabase!=null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    //B4: Tao ham dong ket noi
    public void closeDatabase(){
        if(mDatabase!=null){
            mDatabase.close();
        }
    }

    //Ham INSERT (insert Contact vao bang CONTACTS_TABLE)
    public void insertContact(){
        openDatabase();//Mo ket noi
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME","Nguyen Van A");
            contentValues.put("PHONE_NUMBER","123456789");
            long result = mDatabase.insert(CONTACTS_TABLE,null,contentValues);
            if(result==1){
                Toast.makeText(mContext, "Thanh cong", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public void deleteContact(){
        openDatabase();//Mo ket noi
        try {
            String[] values = {"1"};
            long result =  mDatabase.delete(CONTACTS_TABLE, "ID=?",values);
            if(result==1){
                Toast.makeText(mContext, "Thanh cong", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public void updateContact(){
        openDatabase();//Mo ket noi
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME","BBBBBB");
            String[] values = {"1"};
            long result =  mDatabase.update(CONTACTS_TABLE,contentValues,"ID=?",values);
            if(result==1){
                Toast.makeText(mContext, "Thanh cong", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeDatabase();
        }
    }

    public List<Contact> getContact(){
        List<Contact> lstContact = new ArrayList<Contact>();
        openDatabase();//Mo ket noi
        try {
            String query = "select ID, NAME, PHONE_NUMBER from "+CONTACTS_TABLE;
            Cursor mCursor = mDatabase.rawQuery(query,null);
            if(mCursor!=null){
                mCursor.moveToFirst();
                while (!mCursor.isAfterLast()){
                    String strID = mCursor.getString(0);
                    String strName = mCursor.getString(1);
                    String strPhoneNumber = mCursor.getString(2);
                    Contact mContact = new Contact(strID,strName,strPhoneNumber);
                    lstContact.add(mContact);
                    mCursor.moveToNext();
                }
                mCursor.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeDatabase();
        }
        return lstContact;
    }
}
