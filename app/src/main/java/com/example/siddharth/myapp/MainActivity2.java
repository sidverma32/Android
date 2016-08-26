package com.example.siddharth.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SIDDHARTH on 6/26/2016.
 */
public class MainActivity2 extends AppCompatActivity {

    Button b;
    EditText e1,e2;
    dbconnection db;
    TextInputLayout input_username,input_password;

    TextView tv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        input_username=(TextInputLayout)findViewById(R.id.username);
        input_password=(TextInputLayout)findViewById(R.id.password);
       b=(Button) findViewById(R.id.button);
       e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText5);
        tv = (TextView) findViewById(R.id.textView);
        db=new dbconnection(MainActivity2.this);
        e1.addTextChangedListener(new MyTextWatcher(e1));
        e2.addTextChangedListener(new MyTextWatcher(e2));

    }

//onclick on text view
    public void show(View v) {
        Intent i1 = new Intent(MainActivity2.this, MainActivity.class);//source,destination
        startActivity(i1);
    }
    public  void login(View v)
    {

        String a=e1.getText().toString();
        String b=e2.getText().toString();
        String x="";
        String y="";
        Cursor c=db.login();
        c.moveToFirst();
        boolean abc=false;
        if (!validateName()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
       /* if(e1.getText().toString()=="")
        {
            Toast.makeText(MainActivity2.this,"Username or pwd not blank",Toast.LENGTH_SHORT).show();
                db.close();
        }
*/
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {

                if (a.equals(c.getString(0)) && b.equals(c.getString(1))) {
                    x=c.getString(2);//fetch email
                    y=c.getString(3);//fetch mobile

                    abc = true;
                }

        }
        if(abc)
        {

            //transfer multiple data from one MainActivity2 to Profile activity via MainActivity3
            Intent i=new Intent(MainActivity2.this,MainActivity3.class);
            i.putExtra("key",e1.getText().toString());
            i.putExtra("key2",e2.getText().toString());
            i.putExtra("key3",x);
            i.putExtra("key4",y);

              startActivity(i);
        }
        else
        {
            Toast.makeText(MainActivity2.this,"Username or pwd not matched",Toast.LENGTH_SHORT).show();

        }
    }
    private boolean validateName() {
        if (e1.getText().toString().trim().isEmpty()) {
            input_username.setError("Enter your Username");
            e1.requestFocus();
            return false;
        } else {

            input_username.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validatePassword() {
        if (e2.getText().toString().trim().isEmpty()) {
            input_password.setError(getString(R.string.err_msg_password));
            e2.requestFocus();
            return false;
        } else {
            input_password.setErrorEnabled(false);
        }

        return true;
    }
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.username:
                    validateName();
                    break;
                case R.id.password:
                    validatePassword();
                    break;

            }
        }
    }

}
