package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ice_coffee on 2015/10/10.
 * 疑问之处 $$$
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper
{
    /**
     * province表建表语句
     */
    private static final String CREATE_PROVINCE = "create table province (" +
            "id integer primary key autoincrement, " +
            "province_name text, " +
            "province_code text, " +
            ")";

    /**
     * city表建表语句
     */
    private static final String CREATE_CITY = "create table city (" +
            "id integer primary key autoincrement, " +
            "city_name text, " +
            "city_code text, " +
            "province_id integer" +
            ")";

    /**
     * county表建表语句
     */
    private static final String CREATE_COUNTY = "create table county (" +
            "id integer primary key autoincrement, " +
            "county_name text, " +
            "county_code text, " +
            "city_id integer" +
            ")";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
