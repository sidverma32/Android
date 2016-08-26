package com.example.siddharth.myapp;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by SIDDHARTH on 7/5/2016.
 */
public class MainActivity4 extends AppCompatActivity {

    reminder r;
    EditText e1, e2, e3;
    CalendarView cv;
    Button b1, b2;
    TextInputLayout input_sum, input_note;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_4);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        b1 = (Button) findViewById(R.id.bt);
        b2 = (Button) findViewById(R.id.bt1);
        e1 = (EditText) findViewById(R.id.editText7);
        e2 = (EditText) findViewById(R.id.editText8);
        e3 = (EditText) findViewById(R.id.editText13);
        cv = (CalendarView) findViewById(R.id.calendarView);
        input_sum = (TextInputLayout) findViewById(R.id.summary);
        input_note = (TextInputLayout) findViewById(R.id.note);
        r = new reminder(MainActivity4.this);
        e1.addTextChangedListener(new MyTextWatcher(e1));
        e2.addTextChangedListener(new MyTextWatcher(e2));

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int date) {
                e3.setText(date + "/" + month + "/" + year);
                //   Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = e1.getText().toString();
                String b = e2.getText().toString();
                String c = e3.getText().toString();
                if (!validateSum()) {
                    return;
                }

                if (!validateNote()) {
                    return;
                }

                r.open();
                r.insert(a, b, c);
                Toast.makeText(MainActivity4.this, "reminder for your task is set", Toast.LENGTH_SHORT).show();
                r.close();
                e1.setText("");
                e2.setText("");
                e3.setText("");


            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private boolean validateSum() {
        if (e1.getText().toString().trim().isEmpty()) {
            input_sum.setError("Enter summary");
            e1.requestFocus();
            return false;
        } else {

            input_sum.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateNote() {
        if (e2.getText().toString().trim().isEmpty()) {
            input_note.setError("Enter notes");
            e2.requestFocus();
            return false;
        } else {

            input_note.setErrorEnabled(false);
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
                case R.id.summary:
                    validateSum();
                    break;
                case R.id.note:
                    validateNote();
                    break;

            }
        }
    }



}