package com.example.expensetracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.expensetracker.model.StoreModel;

import java.util.ArrayList;

public class StoreDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "store.db";
    private static final int DATABASE_VERSION = 1;


    public static final String TABLE_NAME = "stores";


    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_TYPE = "type";


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_TYPE + " TEXT)";

    public StoreDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    public void addNewStore(String storeName, String storeAddress, String storeType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, storeName);
        values.put(COLUMN_ADDRESS, storeAddress);
        values.put(COLUMN_TYPE, storeType);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }


    public ArrayList<StoreModel> readStores() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorStores = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<StoreModel> storeModelArrayList = new ArrayList<>();

        if (cursorStores.moveToFirst()) {
            do {
                storeModelArrayList.add(new StoreModel(cursorStores.getInt(0),
                        cursorStores.getString(1),
                        cursorStores.getString(2),
                        cursorStores.getString(3)));
            } while (cursorStores.moveToNext());
        }

        cursorStores.close();
        return storeModelArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
