package com.example.finalassignment.people;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.example.finalassignment.R;

/**
 *
 * create by fanyuhua 2018.12.20
 *
 * */
public class UpdateData  extends AppCompatActivity{
    private EditText phone,tel,ads,mail;    //姓名，手机，座机，地址，邮箱
    TextView name;
    private Button ok,cancel;
    private In_for_mation in_for_mation;
    private My_Sql_DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_data_update);
        initDataBase();
        initView();
        Intent intent = getIntent();
        in_for_mation = (In_for_mation) getIntent().getSerializableExtra("person");
        setText();

        setListener();

    }
    private void initDataBase() {
        //初始化数据库
        dataBase = new My_Sql_DataBase(UpdateData.this);
        dataBase.getWritableDatabase();
    }
    private void setListener() {
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dataBase.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("tel",phone.getText().toString());
                values.put("camera",tel.getText().toString());
                values.put("location",ads.getText().toString());
                values.put("postbox",mail.getText().toString());
                db.update(dataBase.tableName,values,"name=?",new String[]{name.getText().toString()});
                Toast.makeText(UpdateData.this,"修改成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateData.this,Pro_Jbc_Strock.class);
                startActivity(intent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        name = this.findViewById(R.id.data1);
        phone = this.findViewById(R.id.data2);
        tel = this.findViewById(R.id.data3);
        ads = this.findViewById(R.id.data4);
        mail = this.findViewById(R.id.data5);

        ok = findViewById(R.id.button3);
        cancel = findViewById(R.id.button4);
    }
    private void setText()
    {
        name.setText(in_for_mation.getName());
        phone.setText(in_for_mation.getTel());
        tel.setText(in_for_mation.getCamera());
        ads.setText(in_for_mation.getLocation());
        mail.setText(in_for_mation.getPostbox());
    }
}
