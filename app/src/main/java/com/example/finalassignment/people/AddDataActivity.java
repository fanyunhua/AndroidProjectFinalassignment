package com.example.finalassignment.people;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.*;
import com.example.finalassignment.R;
/**
 *
 * create by fanyuhua 2018.12.20
 *
 * */
public class AddDataActivity extends AppCompatActivity {
    private EditText name,phone,tel,ads,mail;    //姓名，手机，座机，地址，邮箱
    private Button  ok,cancel;
    private My_Sql_DataBase dataBase;
    private String name_data ,phone_data,tel_data,ads_data,mail_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_data_add);
        initView();
        initDataBase();
        initEvent();
    }

    private void initEvent() {
        name_data = null;
        phone_data = null;
        tel_data = null;
        ads_data = null;
        mail_data = null;

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_data = name.getText().toString();
                phone_data = phone.getText().toString();
                tel_data = tel.getText().toString();
                ads_data = ads.getText().toString();
                mail_data = mail.getText().toString();
                SQLiteDatabase db = dataBase.getWritableDatabase();
                ContentValues values = new ContentValues();
//                String sql = "create table stdinfo(id integer primary key autoincrement ,name varchar(10),tel varchar(11),camera varchar(10) ,location varchar(10) ,postbox varchar(15))";

                if(isDataCorrect(name_data,phone_data,tel_data,ads_data,mail_data))
                {
                    values.put("name",name_data);
                    values.put("tel",phone_data);
                    values.put("camera",tel_data);
                    values.put("location",ads_data);
                    values.put("postbox",mail_data);
                    db.insert("stdinfo",null,values);
                    Toast.makeText(AddDataActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    db.close();
                    finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDataActivity.this,Pro_Jbc_Strock.class);
            }
        });
    }

    private void initDataBase() {
        //初始化数据库
        dataBase = new My_Sql_DataBase(AddDataActivity.this);
        dataBase.getWritableDatabase();
    }
    private void initView() {
        name = findViewById(R.id.Data_Name);
        phone = findViewById(R.id.Data_Tel);
        tel = findViewById(R.id.Data_Camera);
        ads = findViewById(R.id.Data_Location);
        mail = findViewById(R.id.Data_Postbox);

        ok = findViewById(R.id.Btn_Confirm);
        cancel = findViewById(R.id.Btn_Cancel);
    }
    boolean isDataCorrect(String q ,String w,String e,String r,String t)
    {
        if(q.length()>0)
        {
            if(w.length()>0)
            {
                if(e.length()>0)
                {
                    if(r.length()>0)
                    {
                        if(t.length()>0)
                        {
                            return true;
                        }
                        else
                        {
                            Toast.makeText(AddDataActivity.this,"请输入正确的邮箱地址",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(AddDataActivity.this,"请输入正确的地址",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(AddDataActivity.this,"请输入正确的座机号",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(AddDataActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(AddDataActivity.this,"请输入正确的用户名",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
