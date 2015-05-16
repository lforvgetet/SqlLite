package com.fis.www.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by f103082 on 2015/5/12.
 */
public class AuthorDAO {
    //final 在程式定義中是不會改變的
    public static final String TABLE_NAME="author";
    public static final String KEY_ID="_id";
    public static final String NAME= "name";
    public static final String CREATE_TABLE=
            "CREATE TABLE author ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "name TEXT)";
    private SQLiteDatabase database;
    public AuthorDAO(Context context){database = DBHelper.getDatabase(context);}
    public Author insert(Author author){
        ContentValues cv = new ContentValues();
        cv.put("name",author.getName());
        long id = database.insert(TABLE_NAME,null,cv);
        author.setId(id);
        return author;
    }
   public boolean update(Author author){

       ContentValues cv= new ContentValues();
       cv.put("name",author.getName());
       String where = KEY_ID + "=" + author.getId();
     return database.update(TABLE_NAME,cv,where,null) > 0;

   }
    public boolean delete(long id){

        String where =KEY_ID +"=" + id;
        return database.delete(TABLE_NAME,where,null)>0;
    }
    public List<Author> getAll(){
         List<Author> result= new ArrayList<>();
        //Parameters: TableName, Columns, where, where Params,Having,order, Limit
        //Columns=>帶所需欄位
        //params=>若參數=? 則需帶陣列進來
        Cursor cursor= database.query(TABLE_NAME,null,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            result.add(getRecord(cursor));
        }
        cursor.close();
        return result;
    }
    public Author get(long id){
        Author user = null;
        String where = KEY_ID+"="+id;
        Cursor result=database.query(TABLE_NAME,null,where,null,null,null,null,null);

        if(result.moveToFirst()){
            user=getRecord(result);
        }

        result.close();
        return user;
    }

    public Author getRecord(Cursor cursor){
        Author result= new Author();
        result.setId(cursor.getLong(0));
        result.setName(cursor.getString(1));
        return result;
    }
    public int getCount(){
        int result= 0;
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " +TABLE_NAME,null);
        if (cursor.moveToNext()){
            result=cursor.getInt(0);
        }
        return result;
    }


}
