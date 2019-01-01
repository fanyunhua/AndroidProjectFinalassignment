package com.fanyuhua.finalassignment.people;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fanyuhua.finalassignment.R;

/**
 *
 * create by fanyuhua 2018.12.20
 *
 * */
public class Search_Adapter extends ArrayAdapter<In_for_mation> {

    LayoutInflater layoutInflater;
    private In_for_mation in_for_mation;
    public Search_Adapter(@NonNull Context context, int resource) {
        super(context, resource);
        layoutInflater = LayoutInflater.from(context);
    }

    public void setIn_for_mation(In_for_mation in_for_mation) {
        this.in_for_mation = in_for_mation;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        In_for_mation in = getItem(position);
        if(convertView==null)
        {
            convertView = layoutInflater.inflate(R.layout.list_item,null,false);
        }
        TextView t1 = convertView.findViewById(R.id.text_1);
        TextView t2 = convertView.findViewById(R.id.text_2);

        t1.setText(in.getName().toString());
        t2.setText(in.getTel().toString());
        return convertView;
    }
}
