package com.fanyuhua.finalassignment.people;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.*;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import com.fanyuhua.finalassignment.R;

/**
 *
 * create by fanyuhua 2018.12.20
 *
 * */
public class Pro_Jbc_Strock extends AppCompatActivity {
    private My_Sql_DataBase dataBase;
    private ListView listView;
    private Search_Adapter sa;
    private List<In_for_mation> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro__jbc__strock);
        initDataBase();
        initView();
        listViewListener();

    }

    private void listViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter a = adapterView.getAdapter();
                a = listView.getAdapter();
                In_for_mation ifma = (In_for_mation) a.getItem(i);
                String name_intent = ifma.getName();
//                Toast.makeText(Pro_Jbc_Strock.this,i+""+ifma.getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Pro_Jbc_Strock.this,InLookActivity.class);
                intent.putExtra("name",name_intent);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        listView = findViewById(R.id.List_data_);
    }

    private void initDataBase() {
        //初始化数据库
        dataBase = new My_Sql_DataBase(Pro_Jbc_Strock.this);
        dataBase.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add:
                Intent intent = new Intent(Pro_Jbc_Strock.this,AddDataActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        list = new ArrayList<In_for_mation>();

        SQLiteDatabase db = dataBase.getWritableDatabase();
        Cursor cursor = db.query(dataBase.tableName,new String[]{"name","tel"},
                null,null,null,null,null,null);
        sa = new Search_Adapter(Pro_Jbc_Strock.this,R.layout.list_item);
//        In_for_mation a = new In_for_mation();
//        a.setName("addddd");
//        sa.add(a);
        while (cursor.moveToNext())
        {
            sa.add(new In_for_mation(cursor.getString(0),cursor.getString(1)));
        }
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(sa);
    }
}
