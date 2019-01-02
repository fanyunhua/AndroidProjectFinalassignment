package com.example.finalassignment.tab;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.finalassignment.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * create by fanyuhua 2018.12.17
 *
 * */
public class More extends Fragment {
    private ListView listView;
    public ArrayAdapter<String> adapter;
    public List<String> list = new ArrayList<>();
    String displayName;
    String number;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avtivity_more,null);
        listView = view.findViewById(R.id.MoreLV);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }


        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(getContext(), "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    private void readContacts() {
        Cursor cursor = null;
        try {
            //cursor指针 query询问 contract协议 kinds种类
            HashMap<String, String> maps = new HashMap<String, String>();
            ArrayList<String> na = new ArrayList<String>();
            cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {

                while (cursor.moveToNext()) {
                    number = null;
                    displayName = null;
                     displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                    list.add(displayName + '\n' + number);
                    maps.put(displayName,number);
                    na.add(displayName);
                }

                Set set = new HashSet();
                List newList = new ArrayList();
                for (Iterator iter = na.iterator(); iter.hasNext();) {
                    Object element = iter.next();
                    if (set.add(element))
                        newList.add(element);
                }
                na.clear();
                na.addAll(newList);
                for (int i = 0;i<maps.size();i++)
                {
                    list.add(na.get(i)+"\n"+maps.get(na.get(i)));
                }
                //notify公布
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
