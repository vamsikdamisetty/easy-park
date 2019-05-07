package com.example.vamsi.easy_parking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProfileOpenHelper extends SQLiteOpenHelper {

    public static final String username = "_uname", profile_name= "_pname",
            mobno="_mob",city="_city",country="_country";

    public static final String id2 = "_id",place2 = "_place",city2 ="_city",vehicle = "_vehicle" ,
            time2 = "_time",amount2 = "_amount";
    public ProfileOpenHelper(Context contex) {
        super(contex, "ProfileDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table UserProfile (_uname TEXT primary key, " +
                "_pname TEXT, _mob TEXT, _city TEXT, _country TEXT)");

        sqLiteDatabase.execSQL("create table Bookings (_id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " _place TEXT, _city TEXT,_vehicle TEXT, _time TEXT, _amount TEXT)");

    }

    public long insertItem(String... s){
        ContentValues cv = new ContentValues();
        cv.put(username, s[0]);
        cv.put(profile_name, s[1]);
        cv.put(mobno, s[2]);
        cv.put(city, s[3]);
        cv.put(country, s[4]);

        return this.getWritableDatabase().insert("UserProfile", null, cv);
    }

    public Cursor getData(){
        return this.getReadableDatabase().rawQuery("Select * from "+"UserProfile",null );
    }

    public long insertItem2(String... s){
        ContentValues cv = new ContentValues();
        cv.put(city2, s[0]);
        cv.put(place2, s[1]);
        cv.put(vehicle, s[2]);
        cv.put(time2, s[3]);
        cv.put(amount2, "40");
        return this.getWritableDatabase().insert("Bookings", null, cv);
    }
    public Cursor getData2(){
        return this.getReadableDatabase().rawQuery("Select * from "+"BookingProfile"+" order by"+
                " _ID desc",null );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
