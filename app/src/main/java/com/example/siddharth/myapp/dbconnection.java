package com.example.siddharth.myapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SIDDHARTH on 6/28/2016.
 */
public class dbconnection {


    Context cn;
    String db = "login";
    String tb = "register";
    int version = 2;
    String id = "ID";
    String user = "username";
    String email = "email";
    String pwd = "password";
    String mob = "mobile";
    SQLiteDatabase sql;
    connecion con;

    public class connecion extends SQLiteOpenHelper {

        public connecion(Context context) {
            super(context, db, null, version);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            String query = "create table " + tb + " ("+ user + " text  primary key, "  + email + " text not null, " + pwd + " text not null," + mob + " text not null);";
            arg0.execSQL(query);
            // TODO Auto-generated method stub

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            //drop table if existed older
              arg0.execSQL("drop table if exists"+tb);
            //create tables again;
             onCreate(arg0);


        }

    }


    public dbconnection(MainActivity mainActivity) {
        cn = mainActivity;

        // TODO Auto-generated constructor stub
    }

    public  dbconnection(Profile profile){
        cn=profile;
    }
    public dbconnection(MainActivity2 mainActivity2) {
        cn = mainActivity2;

        // TODO Auto-generated constructor stub
    }


    public void open() {
        con = new connecion(cn);
        sql = con.getWritableDatabase();

    }
  /* public String view() {

        con = new connecion(cn);
        sql = con.getReadableDatabase();
        String s = "";
        String[] arr = { user, pwd, email, mob };// column name
        // Cursor cr = sql.query(tb, arr, null, null, null, null, null);
        Cursor cr= sql.query(tb, arr, null, null, null, null, null);
        cr.moveToFirst();
        for (cr.moveToFirst(); !cr.isAfterLast(); cr.moveToNext()) {
            s = s + cr.getString(0) + " " + cr.getString(1) + " " + cr.getString(2) + " "
                    + cr.getString(3) + " \n";

        }

        return s;
    }*/

    public void insert(String u, String v, String w, String x) {
        ContentValues cv = new ContentValues();
        cv.put(user, u);
        cv.put(email, v);
        cv.put(pwd,w);
        cv.put(mob,x);
        sql.insert(tb, null, cv);
    }
    public  Cursor check()
    {

        con = new connecion(cn);
        sql = con.getReadableDatabase();
        String[] aa = { user };
        Cursor c = sql.query(tb, aa, null, null, null, null, null);
        return c;

    }


    public Cursor login() {

        con = new connecion(cn);
        sql = con.getReadableDatabase();
        String[] aa = { user, pwd, email, mob };
        Cursor c = sql.query(tb, aa, null, null, null, null, null);
        return c;

    }
    public void close() {
        sql.close();
    }

    public void update(String p, String q, String r, String s) {
        ContentValues cv = new ContentValues();

        cv.put(email,q);
        cv.put(pwd,r);
        cv.put(mob,s);
     //  sql.update(tb, cv, z, null);
      // String where= user + "=" + p;
        sql.update(tb, cv,user +"=?", new String[]{p});
        //TODO Auto-generated method stub

    }

   /* public Cursor getData(String d){
        sql = con.getReadableDatabase();
        String quer="select * from " + tb + " where user =?";
        Cursor cursor=sql.rawQuery(quer,new String[]{d});
        if (cursor != null)
        {   cursor.moveToFirst();}
        return cursor;
    }
*/

    }
