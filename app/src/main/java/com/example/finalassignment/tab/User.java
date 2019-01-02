package com.example.finalassignment.tab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalassignment.R;
/**
 *
 * create by fanyuhua 2018.12.17
 *
 * */
public class User extends Fragment {
    private Button exit;
    private SharedPreferences sp;
    private SharedPreferences sp2;
    TextView usernameTV;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.acticity_user,null);
        sp = this.getActivity().getSharedPreferences("state",0);
        sp2 = this.getActivity().getSharedPreferences("setting",0);
        String name = sp2.getString("user","");

        initView(view);
        usernameTV.setText(name);
        initListener();
        return view;
    }

    private void initListener() {

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("count",true);
                editor.commit();
                getActivity().finish();
            }
        });
    }

    private void initView(View view) {
        exit = view.findViewById(R.id.button4);
        usernameTV = view.findViewById(R.id.usernameTV);
    }
}
