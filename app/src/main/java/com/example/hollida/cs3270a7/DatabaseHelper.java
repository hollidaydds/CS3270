package com.example.hollida.cs3270a7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hollida on 6/3/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private SQLiteDatabase database;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public  SQLiteDatabase open()
    {
        database = getWritableDatabase();
        return database;
    }

    public void close()
    {
        if (database != null)
            database.close();
    }

    public long insertCourse(String name, String code, String start, String end)
    {
        long rowID = -1;

        ContentValues newCourse = new ContentValues();
        newCourse.put("name",name);
        newCourse.put("code",code);
        newCourse.put("start",start);
        newCourse.put("end",end);
        if (open() !=null){
            rowID = database.insert("Couses", null, newCourse);
            close();
        }
        return rowID;
    }

    public  long updateCourse(long id, String name, String code, String start, String end)
    {
        long rowID= -1;

        ContentValues editCourse = new ContentValues();
        editCourse.put("name", name);
        editCourse.put("code", code);
        editCourse.put("start", start);
        editCourse.put("end", end);
        if (open() != null){
            rowID = database.update("Couses", editCourse, "_id=" + id, null);
            close();
        }
        return rowID;
    }
    public Cursor getAllCourses()
    {
        Cursor cursor = null;
        if(open() != null){
            cursor = database.rawQuery("SELECT * FROM Couses ORDER BY code", null);
        }
        return cursor;
    }

    public Cursor getOneCourse(long id)
    {
        String[] params = new String[1];
        params[0] = "" + id;
        Cursor cursor = null;
        if(open() != null){
            cursor = database.rawQuery("SELECT * FROM Couses WHERE _id = ?", params);
        }
        return cursor;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE Couses " +
                "(_id integer primary key autoincrement," +
                "name TEXT, code TEXT, start TEXT, end TEXT);";

        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
