package com.fis.www.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by f103082 on 2015/5/12.
 */
public  class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="datafile.db";
    public static final  int VERSION=1;
    private static SQLiteDatabase database;
    public DBHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory,version);
    }
    public static SQLiteDatabase getDatabase(Context context){
        if(database== null || !database.isOpen()){
            database = new DBHelper(context, DATABASE_NAME,null,VERSION).getWritableDatabase();

        }
        return database;
    }
    public void onCreate(SQLiteDatabase database){database.execSQL(AuthorDAO.CREATE_TABLE);}
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS" + AuthorDAO.TABLE_NAME);
        onCreate(database);

    }
    public void close(){database.close();}
}
