package com.example.siddharth.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by SIDDHARTH on 6/26/2016.
 */
public class MainActivity1 extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1);

        final Intent i=new Intent(MainActivity1.this,MainActivity2.class);//source,destination
        Thread th=new Thread()
        {
            @Override

            public void run() {
                // TODO Auto-generated method stub
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(i);
                }
                super.run();
            }

        };
        th.start();
    }

    @Override
    protected void onPause() {

        finish();
        super.onPause();
    }

    }
