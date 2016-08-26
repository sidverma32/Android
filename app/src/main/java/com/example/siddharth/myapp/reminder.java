package com.example.siddharth.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SIDDHARTH on 7/5/2016.
 */
public class reminder{


    Context cn;
    String db = "login1";
    String tb = "task";
    int version = 1;
    String id="ID";
    String sum = "summary";
    String note = "notes";
    String dt = "datetime";

    SQLiteDatabase sql;

    connection con;
    public class connection extends SQLiteOpenHelper {

        public connection(Context context) {
            super(context, db, null, version);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            String query = "create table " + tb + " ( " + id
                    + " integer primary key autoincrement, " + sum
                    + " text not null, " + note + " text not null, " + dt + " text not null );";
            arg0.execSQL(query);
            // TODO Auto-generated method stub

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
                //drop table if existed older
             //   arg0.execSQL("drop table if exists"+tb);
            //create tables again;
          //  onCreate(arg0);

        }

    }
    public reminder(MainActivity4 mainActivity4) {
        cn = mainActivity4;

        // TODO Auto-generated constructor stub
    }
    public reminder(MainActivity3 mainActivity3) {
        cn = mainActivity3;

        // TODO Auto-generated constructor stub
    }



    public void open() {
        con = new connection(cn);
        sql = con.getWritableDatabase();

    }
    public void insert(String a, String b, String c) {

        ContentValues cv = new ContentValues();
        cv.put(sum, a);
        cv.put(note,b);
        cv.put(dt,c);

        sql.insert(tb, null, cv);
    }
    public void delete(String get_id) {
        sql.delete(tb, id + "=" + get_id, null);
        // TODO Auto-generated method stub

    }


    public String view() {

        con = new connection(cn);
        sql = con.getReadableDatabase();
        String s = "";
        String[] arr = { id, sum, note, dt };// column name
       // Cursor cr = sql.query(tb, arr, null, null, null, null, null);
        Cursor cr= sql.query(tb, arr, null, null, null, null, null);
        cr.moveToFirst();
        for (cr.moveToFirst(); !cr.isAfterLast(); cr.moveToNext()) {
            s = s + cr.getString(0) + " " + cr.getString(1) + " " + cr.getString(2) + " "
                    + cr.getString(3) + " \n";

        }

        return s;
    }
    public  Cursor check()
    {

        con = new connection(cn);
        sql = con.getReadableDatabase();
        String[] data = { dt };
        Cursor c = sql.query(tb, data, null, null, null, null, null);
        return c;

    }

    public List<Record> getrecord() {
        List<Record> recordlist = new ArrayList<Record>();
        //select all query
        con = new connection(cn);

        String query = "SELECT * FROM " +tb;
       sql=con.getReadableDatabase();
        Cursor cursor = sql.rawQuery(query, null);

        //looping all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setID(Integer.parseInt(cursor.getString(0)));
                record.setsum(cursor.getString(1));
                record.setnote(cursor.getString(2));
                record.setdt(cursor.getString(3));
                String name="";
                 name = cursor.getString(1) + "\n" + cursor.getString(2) + "\n" + cursor.getString(3);
                MainActivity3.ArrayofName.add(name);

                //addinf record to list
                recordlist.add(record);

            } while (cursor.moveToNext());
        }
        return recordlist;
    }

    public void close() {
        sql.close();
    }
}
