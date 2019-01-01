package com.fanyuhua.finalassignment.util.util;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "shopping";//数据库名字
    private static final int DB_VERSION = 1;   // 数据库版本
    private Context m_context;
    public String tableName = "stdinfo";
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        //super(contex,DB_NAME,null,DB_VERSION);
        m_context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql3 = "create table user (name varchar(30),passwd varchar(30),phone varchar(30))";
        sqLiteDatabase.execSQL(sql3);

        String sql = "create table stdinfo(id integer primary key autoincrement ,name varchar(10),tel varchar(11),camera varchar(10) ,location varchar(10) ,postbox varchar(15))";
        sqLiteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
