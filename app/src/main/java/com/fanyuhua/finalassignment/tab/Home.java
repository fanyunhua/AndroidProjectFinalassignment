package com.fanyuhua.finalassignment.tab;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.fanyuhua.finalassignment.R;

import com.fanyuhua.finalassignment.people.AddDataActivity;
import com.fanyuhua.finalassignment.people.InLookActivity;
import com.fanyuhua.finalassignment.people.In_for_mation;
import com.fanyuhua.finalassignment.people.My_Sql_DataBase;
import com.fanyuhua.finalassignment.people.Pro_Jbc_Strock;
import com.fanyuhua.finalassignment.people.Search_Adapter;
import com.fanyuhua.finalassignment.util.util.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * create by fanyuhua 2018.12.17
 *
 * */
public class Home extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String[] price;
    private String[] name;
    private int[] image;

    private My_Sql_DataBase dataBase;
    private Search_Adapter sa;
    private List<In_for_mation> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home,null);
        listView = view.findViewById(R.id.homeLV);
        initDataBase();
        setDate();
        listViewListener();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main,menu);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // initDataBase();
        //关键一句 添加menu
        setHasOptionsMenu(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add:
                Intent intent = new Intent(getContext(),AddDataActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setDate()
    {
        list = new ArrayList<In_for_mation>();

        SQLiteDatabase db = dataBase.getWritableDatabase();
        Cursor cursor = db.query(dataBase.tableName,new String[]{"name","tel"},
                null,null,null,null,null,null);
        sa = new Search_Adapter(getContext(),R.layout.list_item);
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

    private void initDataBase() {
        //初始化数据库
        dataBase = new My_Sql_DataBase(getContext());
        dataBase.getWritableDatabase();
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
                Intent intent = new Intent(getContext(),InLookActivity.class);
                intent.putExtra("name",name_intent);
                startActivity(intent);
            }
        });
    }
}
