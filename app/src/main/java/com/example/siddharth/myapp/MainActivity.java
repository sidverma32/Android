package com.example.siddharth.myapp;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button b;
    dbconnection db;
    TextInputLayout input_user,input_email,input_pwd,input_mob;
TextView tv;
    NotificationManager nm;
    Notification n;

    //ProgressDialog pd;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        input_user=(TextInputLayout)findViewById(R.id.username);
        input_email=(TextInputLayout)findViewById(R.id.email);
        input_pwd=(TextInputLayout)findViewById(R.id.password);
        input_mob=(TextInputLayout)findViewById(R.id.phone);

        b = (Button) findViewById(R.id.button);
        e1=(EditText)findViewById(R.id.editText6);
        e2=(EditText)findViewById(R.id.editText);
        e3=(EditText)findViewById(R.id.editText2);
        e4=(EditText)findViewById(R.id.editText4);
        tv=(TextView)findViewById(R.id.textView3);
        db=new dbconnection(MainActivity.this);
        //spinner=(ProgressBar)findViewById(R.id.progressBar);
        e1.addTextChangedListener(new MyTextWatcher(e1));
        e2.addTextChangedListener(new MyTextWatcher(e2));
        e3.addTextChangedListener(new MyTextWatcher(e3));
        e4.addTextChangedListener(new MyTextWatcher(e4));

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);



    }

    public void show(View s)
    {


        Intent i=new Intent(MainActivity.this,MainActivity2.class);
        startActivity(i);
    }
       /* public void view(View vs){

        String sr=db.view();
            Dialog dg=new Dialog(MainActivity.this);
            dg.setTitle("register data");
            TextView tv1=new TextView(MainActivity.this);
            tv1.setText(sr);
            dg.setContentView(tv1);
            dg.show();
    }*/


            public void regi (View z){

                String u = e1.getText().toString();
                String v = e2.getText().toString();
                String w = e3.getText().toString();
                String x = e4.getText().toString();
                if (!validateName()) {
                    return;
                }

                if (!validateEmail()) {
                    return;
                }

                if (!validatePassword()) {
                    return;
                }
                if(!validatePhone())
                {
                    return;
                }

                Cursor c = db.check();
                c.moveToFirst();
                boolean abc = false;

                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    if (u.equals(c.getString(0))) {
                        abc = true;
                    }

                }
                if (abc) {
                    Toast.makeText(MainActivity.this, "Username  already exist", Toast.LENGTH_LONG).show();

                } else {
                    db.open();
                    db.insert(u, v, w, x);
                    // pd=ProgressDialog.show(this,"Loading","Please wait....",true);
                    //spinner.setVisibility(View.VISIBLE);
                    final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle("Loading...");
                    dialog.setMessage("Please wait.");
                    dialog.setIndeterminate(true);
                    dialog.setCancelable(false);
                    dialog.show();

                    long delayInMillis = 5000;

                   Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }

                    }, delayInMillis);
                    Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();

                    //  Intent i1=new Intent(MainActivity.this,MainActivity2.class);
                    //startActivity(i1);

                    db.close();

                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                }
            }
    private boolean validateName() {
        if (e1.getText().toString().trim().isEmpty()) {
            input_user.setError(getString(R.string.err_msg_name));
            requestFocus(e1);
            return false;
        } else {

            input_user.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validatePhone() {
        if (e4.getText().toString().trim().isEmpty()) {
            input_mob.setError(getString(R.string.err_msg_phone));
            requestFocus(e4);
            return false;
        } else {

            input_mob.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = e2.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            input_email.setError(getString(R.string.err_msg_email));
            requestFocus(e2);
            return false;
        } else {
            input_email.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (e3.getText().toString().trim().isEmpty()) {
            input_pwd.setError(getString(R.string.err_msg_password));
            requestFocus(e3);
            return false;
        } else {
            input_pwd.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
                case R.id.email:
                    validateEmail();
                    break;
                case R.id.password:
                    validatePassword();
                    break;
                case R.id.phone:
                    break;
            }
        }
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
