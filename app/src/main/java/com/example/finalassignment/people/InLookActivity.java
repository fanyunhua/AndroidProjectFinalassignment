package com.example.finalassignment.people;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.*;
import android.telephony.PhoneNumberUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalassignment.R;

/**
 *
 * create by fanyuhua 2018.12.20
 *
 * */
public class InLookActivity extends AppCompatActivity {
    private My_Sql_DataBase dataBase;
    private In_for_mation auto;
    private String name_data;
    private TextView name, phone, tel, ads, mail;
    private String name_data2, phone_data, tel_data, ads_data, mail_data;
    public In_for_mation person;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_data_inlook);
        initView();
        Intent intent = getIntent();
        //获取要查看的联系人
        name_data = intent.getStringExtra("name");
        initDataBase();
        initAuto();

    }

    private void initAuto() {

        SQLiteDatabase db = dataBase.getWritableDatabase();
        Cursor cursor = db.query(dataBase.tableName, new String[]{"name", "tel", "camera", "location", "postbox"},
                "name=?", new String[]{name_data}, null, null, null, null);
////                String sql = "create table stdinfo(
// id integer primary key autoincrement ,name varchar(10),tel varchar(11),camera varchar(10) ,location varchar(10) ,postbox varchar(15))";
        while (cursor.moveToNext()) {
            name_data2 = cursor.getString(0).toString();
            phone_data = cursor.getString(1).toString();
            tel_data = cursor.getString(2).toString();
            ads_data = cursor.getString(3).toString();
            mail_data = cursor.getString(4).toString();
//            Toast.makeText(InLookActivity.this,cursor.getString(0),Toast.LENGTH_SHORT).show();
        }
        name.setText(name_data2);
        phone.setText(phone_data);
        tel.setText(tel_data);
        ads.setText(ads_data);
        mail.setText(mail_data);
        person = new In_for_mation(name_data2, phone_data, tel_data, ads_data, mail_data);
        db.close();
    }

    private void initDataBase() {
        //初始化数据库
        dataBase = new My_Sql_DataBase(InLookActivity.this);
        dataBase.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);

        return true;
    }

    private void initView() {
        name = findViewById(R.id.text_2);
        phone = findViewById(R.id.textView4);
        tel = findViewById(R.id.textView8);
        ads = findViewById(R.id.textView10);
        mail = findViewById(R.id.textView12);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.write:
                Intent intent = new Intent(InLookActivity.this, UpdateData.class);

                Bundle b = new Bundle();
                //此处必须在需要传递的对象类中实现Serializable接口
                b.putSerializable("person", person);
                intent.putExtras(b);
                startActivity(intent);
                break;
            case R.id.delete:
                deletePerson();
                break;
            case R.id.call:
                //打电话
                if(ContextCompat.checkSelfPermission(InLookActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(
                            InLookActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},1
                            );
                }
                else{
                    System.out.println("这里");
                    Intent intenti = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone_data));
                    startActivity(intenti);
                }
                break;
            case R.id.sendMsg:
                //发短信
                if(PhoneNumberUtils.isGlobalPhoneNumber(phone_data)){
                    Intent intent1 = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phone_data));
                    intent1.putExtra("sms_body", "");
                    startActivity(intent1);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void deletePerson()
    {
        SQLiteDatabase db = dataBase.getWritableDatabase();
        db.delete(dataBase.tableName,"name=?",new String[]{name_data2});
        Toast.makeText(InLookActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
        finish();
    }
}
