package com.coolweather.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ice_coffee on 2015/10/10.
 * 疑问之处 $$$
 *
 * 单例获取本类对象
 */
public class CoolWeatherDB
{
    /**
     * 数据库名
     */
    private static final String DB_NAME = "cool_weather";

    /**
     * 数据库版本
     */
    private static final int VERSION = 1;

    private static CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;

    /**
     * 构造函数私有
     */
    private CoolWeatherDB(Context context)
    {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);

        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取CoolWeatherDB的实例
     * @param context
     * @return
     */
    public synchronized static CoolWeatherDB getInstance(Context context)
    {
        if (null == coolWeatherDB )
        {
            coolWeatherDB = new CoolWeatherDB(context
            );
        }

        return coolWeatherDB;
    }

    //-------------------------------------省信息操作----------------------------------------

    /**
     * 将Province实例存储到数据库中
     */
    public void saveProvince(Province province)
    {
        if (null != province)
        {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    /**
     * 从数据中获取全国所有省份信息
     */
    public List<Province> loadProvinces()
    {
        List<Province> provinces = new ArrayList<>();

        Cursor cursor = db.query("province", null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Province province = new Province();

                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("provinceName")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("provinceCode")));

                provinces.add(province);
            } while (cursor.moveToNext());
        }

        return provinces;
    }

    //------------------------------------------城市信息操作-----------------------------------------------

    /**
     * 将City实例存储到数据库中
     */
    public void saveCity(City city)
    {
        if (null != city)
        {
            ContentValues values = new ContentValues();

            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());

            db.insert("city", null, values);
        }
    }

    /**
     * 从数据库中获取某省份下所有城市的信息
     */
    public List<City> loadCities()
    {
        List<City> cities = new ArrayList<>();

        Cursor cursor = db.query("city", null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do
            {
                City city = new City();

                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));

                cities.add(city);
            }while (cursor.moveToNext());
        }

        return cities;
    }

    //---------------------------------------县信息操作--------------------------------------------

    /**
     * 将county实例存储到数据库中
     */
    public void saveCounty(County county)
    {
        if (null != county)
        {
            ContentValues values = new ContentValues();

            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());

            db.insert("county", null, values);
        }
    }

    /**
     * 从数据库中获取某城市下所有县的信息
     */
    public List<County> loadCounties()
    {
        List<County> counties = new ArrayList<>();

        Cursor cursor = db.query("county", null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do
            {
                County county = new County();

                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));

                counties.add(county);
            }while (cursor.moveToNext());
        }

        return counties;
    }
}
