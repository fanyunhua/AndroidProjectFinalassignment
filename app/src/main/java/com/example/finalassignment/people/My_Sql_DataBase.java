package com.example.finalassignment.people;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.example.finalassignment.R;

/**
 *
 * create by fanyuhua 2018.12.20
 *
 * */
public class My_Sql_DataBase extends SQLiteOpenHelper {
    public String tableName = "stdinfo";
    Context context;
    public My_Sql_DataBase(Context context) {

        super(context,"proper.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table stdinfo(id integer primary key autoincrement ,name varchar(10),tel varchar(11),camera varchar(10) ,location varchar(10) ,postbox varchar(15))";
        sqLiteDatabase.execSQL(sql);
        Toast.makeText(context,"数据表创建成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
