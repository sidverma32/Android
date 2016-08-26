package com.example.siddharth.myapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.server.converter.ConverterWrapper;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by SIDDHARTH on 6/30/2016.
 */

public class MainActivity3 extends AppCompatActivity {
    TabHost tabHost;
    ArrayAdapter<String> adapter;
    ListView lv,lv1;
    NotificationManager nm;
    Notification n;
    private PendingIntent pendingIntent;
    public static ArrayList<String> ArrayofName = new ArrayList<String>();
    private GoogleApiClient client;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayofName.clear();
        reminder r = new reminder(MainActivity3.this);
        List<Record> records = r.getrecord();
       // for(Record re: records)
           // String log="ID:"+re.getID()+""+re.getsum()+""+re.getnote()+""+re.getdt();
            //Log.d("Name:",log);

        lv = (ListView) findViewById(R.id.listView2);
        lv1=(ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(MainActivity3.this, R.layout.list_row, ArrayofName);
        lv.setAdapter(adapter);
        lv1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        registerForContextMenu(lv);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(i);
                finish();
            }
        });

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //Tab1
        TabHost.TabSpec spec = host.newTabSpec("tab one");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Recent Tasks");
        host.addTab(spec);


        //tab3
        spec = host.newTabSpec("tab");
        spec.setContent(R.id.tab3);

        spec.setIndicator("All Tasks");
        host.addTab(spec);


 }
   /* public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

   /* public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
       // cancel();
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        nm.cancel(11);
        super.onPause();
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.op1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.op2:
                Intent intent = getIntent();
                startActivity(intent);
                finish();
                // Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;

            case R.id.op3:

                String a = getIntent().getStringExtra("key");
                String b = getIntent().getStringExtra("key2");
                String x = getIntent().getStringExtra("key3");
                String y = getIntent().getStringExtra("key4");
                Intent i1 = new Intent(MainActivity3.this, Profile.class);

                i1.putExtra("key", a);
                i1.putExtra("key2", b);
                i1.putExtra("key3", x);
                i1.putExtra("key4", y);
                startActivity(i1);
                break;


            case R.id.op4:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.op5:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        MenuInflater mn = getMenuInflater();
        mn.inflate(R.menu.context, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater mn = getMenuInflater();
        mn.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.c1:
                adapter.remove(adapter.getItem(info.position));
               /* reminder r = new reminder(MainActivity3.this);
                String get_id=r.id.toString();
                r.open();
                r.delete(get_id);
                r.close();*/
                Toast.makeText(MainActivity3.this, item.getTitle(), Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.siddharth.myapp/http/host/path")
        );
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.siddharth.myapp/http/host/path")
        );
    }

}
