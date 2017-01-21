package com.example.ciuc.shoppingassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="List.db";
    public String TABLE_NAME="NAME";
    public static final String ID="ID INTEGER PRIMARY KEY AUTOINCREMENT,";
    public static final String TYPE="TYPE ";
    public static final String ITEMS="ITEMS ";
    SQLiteDatabase db;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void setName(String name){
        TABLE_NAME=name;
    }
    public void createDB(){
            db=this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            db.execSQL("create table " + TABLE_NAME+" ("+ID+TYPE+"TEXT"+","+ITEMS+"TEXT"+")");

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db=this.getWritableDatabase();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        createDB();


    }

    public boolean insertDatat(String type,String items){
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TYPE,type);
        contentValues.put(ITEMS,items);


        long result=db.insertOrThrow(TABLE_NAME,null,contentValues);

        if(result==-1){

            return false;
        }
        else
            return true;

    }

    public Cursor getAllData(String names){
        db=this.getWritableDatabase();
        Cursor res;
       if(names.isEmpty()||names==null){
            return null;
        }
       else{
           res=db.rawQuery("select * from "+names,null);
       }
        return res;




    }
}
