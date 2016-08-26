package com.example.siddharth.myapp;

/**
 * Created by SIDDHARTH on 7/14/2016.
 */

import java.util.ArrayList;
public class Record {


    //variables
    int _id;
    String _sum;
    String _note;
    String _dt;


    //empty Constructor
    public Record(){

    }

    //constructor
    public  Record(int id,String sum,String note,String dt)
    {
        this._id=id;
        this._sum=sum;
        this._note=note;
        this._dt=dt;
    }

    //constructor
    public  Record(String sum,String note,String dt){
        this._sum=sum;
        this._note=note;
        this._dt=dt;

    }
    //getting ID
    public int getID()
    {
        return  this._id;
    }

    //setting ID
    public void setID(int id)
    {
         this._id=id;
    }

    //getting name
    public String getsum()
    {
        return  this._sum;
    }
    //setting name
    public void setsum(String sum)
    {
        this._sum=sum;
    }

    //getting name
    public String getnote()
    {
        return  this._note;
    }
    //setting name
    public void setnote(String note)
    {
        this._note=note;
    }


    //getting name
    public String getdt()
    {
        return  this._dt;
    }
    //setting name
    public void setdt(String dt)
    {
        this._dt=dt;
    }





}
