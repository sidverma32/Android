package com.example.siddharth.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SIDDHARTH on 7/5/2016.
 */
public class Profile extends AppCompatActivity {
        EditText t1,t2,t3,t4;
        Button b1;
    TextView tv;
        dbconnection db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db=new dbconnection(Profile.this);

        t1=(EditText)findViewById(R.id.editText9);
        t2=(EditText)findViewById(R.id.editText10);
        t3=(EditText)findViewById(R.id.editText11);
        t4=(EditText)findViewById(R.id.editText12);
        tv=(TextView)findViewById(R.id.textView3);
        b1=(Button)findViewById(R.id.button3);
      String a=getIntent().getStringExtra("key");//username
        t1.setText(a);
       String x=getIntent().getStringExtra("key3");//email
        t2.setText(x);
        String b=getIntent().getStringExtra("key2");//password
        t3.setText(b);
        String y=getIntent().getStringExtra("key4");//mobile
        t4.setText(y);



    }
    public void back(View v){

        String p=t1.getText().toString();//username
        String q=t2.getText().toString();//email
        String r=t3.getText().toString();//pwd
        String s=t4.getText().toString();//mob

        db.open();
        db.update(p , q, r, s);
        Toast.makeText(Profile.this, "Updated successfully", Toast.LENGTH_LONG).show();

        db.close();

    }
    public  void show(View v){
        Intent i1=new Intent(Profile.this,MainActivity.class);
        startActivity(i1);
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
       // super.onBackPressed();
    }
}
