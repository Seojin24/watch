package com.watch2;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter extends SQLiteOpenHelper {
    private static final String DB_NAME = "data.db";
    private static final int VERSION = 2;
    private static final String ID = "_id";
    private static final String TYPE = "type";
    private static final String TYPE2 = "type2";
    private static final String AM = "amount";
    private static final String TIME = "time";
    


    private static final String TABLE_NAME = "ts";
    
    private static final String CREATE_TABLE =
    			"CREATE TABLE " + TABLE_NAME + " (" +
        		ID + " integer primary key autoincrement, " +
                        TYPE + " text not null," +
                        TYPE2 + " text not null," +
                        AM + " integer not null," +
                        TIME + " text not null) ";
    private static final String CREATE_TABLE2 =
            "CREATE TABLE IF NOT EXIST " + TABLE_NAME + " (" +
                    ID + " integer primary key autoincrement, " +
                    TYPE + " text not null," +
                    TYPE2 + " text not null," +
                    AM + " integer not null," +
                    TIME + " text not null) ";


 static SQLiteDatabase db;

    public DBAdapter(Context context) {
        super(context, DB_NAME, null, VERSION);
        db = this.getWritableDatabase();

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_TABLE);
    }

    

    public boolean insertT(String type,String type2,int am,String time) {

        ContentValues cv = new ContentValues();
        cv.put(TYPE, type);
        cv.put(TYPE2, type2);
        cv.put(AM, am);
        cv.put(TIME, time);
        return db.insert(TABLE_NAME, null, cv) != -1;

    }
    
    // create

    public int getday(String aa,String type) {
        Cursor c = db.query(TABLE_NAME, new String[] {ID,TYPE,AM,TIME}, "time = ? and type = ?", new String[]{aa,type}, null, null, ID + " DESC");
        int total = 0;
        if (c.moveToFirst()) {
            final int indexAM = c.getColumnIndex(AM);
            do {
                total += c.getInt(indexAM);
                
            } while (c.moveToNext());
        }
        
        c.close();

        return total;
    }

    public int getday2(String aa,String type,String type2) {
        Cursor c = db.query(TABLE_NAME, new String[] {ID,TYPE,TYPE2,AM,TIME}, "time = ? and type = ? and type2 = ?", new String[]{aa,type,type2}, null, null, ID + " DESC");
        int total = 0;
        if (c.moveToFirst()) {
            final int indexAM = c.getColumnIndex(AM);
            do {
                total += c.getInt(indexAM);

            } while (c.moveToNext());
        }

        c.close();

        return total;
    }

    public int getp() {
        Cursor c = db.query(TABLE_NAME, new String[] {ID,TYPE,TYPE2,AM,TIME}, "type = ?", new String[]{"2"}, null, null, ID + " DESC");
        int total = 0;
        if (c.moveToFirst()) {
            final int indexAM = c.getColumnIndex(AM);
            do {
                total += c.getInt(indexAM);

            } while (c.moveToNext());
        }

        c.close();

        return total;
    }


    public boolean deleteContact(int id) {
        String[] params = new String[] { Integer.toString(id) };
        int result = db.delete(TABLE_NAME, ID + "=?", params);
        return result > 0;
    }

    public void deleteall() {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
    
}
