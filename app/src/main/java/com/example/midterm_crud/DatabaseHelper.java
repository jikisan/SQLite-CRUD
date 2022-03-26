package com.example.midterm_crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATA_VERSION = 1;


    private static final String DATABASE_NAME = "productDB";
    private static final String table_NAME = "product";

    private static final String ID = "ID";
    private static final String Name = "Name";
    private static final String Desc = "Description";
    private static final String Price = "Price";
    private static final String Quantity = "Quantity";
    private static final String Status = "Status" ;

    String CREATE_TABLE = "CREATE TABLE " + table_NAME + "(" + ID + " Integer PRIMARY KEY AUTOINCREMENT,"
            + Name + " TEXT,"
            + Desc + " TEXT,"
            + Price + " Integer,"
            + Quantity + " Integer,"
            + Status + " TEXT" + ")";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATA_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_NAME);
        onCreate(sqLiteDatabase);
    }
}
