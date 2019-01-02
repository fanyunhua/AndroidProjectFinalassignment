package com.example.finalassignment.tab;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalassignment.R;


public class Add extends Fragment {
    private ImageView imageView3;
    private  Button ok,stop;
    private EditText name,time;
    private TextView tv;
    //播放闹钟
    private MediaPlayer mPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avtivity_add,null);

        //初始化视图
        initView(view);
        //初始化事件函数
        initEvent();
        mPlayer = MediaPlayer.create(getContext(),R.raw.ddr);

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            //初始化播放器 MediaPlayer
        }
        return view;
    }

    private void initEvent() {
        @SuppressLint("HandlerLeak") final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int aa = msg.what;
//                Toast.makeText(getContext(),aa+"",Toast.LENGTH_SHORT).show();

                if (aa>0)
                {
                    tv.setText(aa+"");
                }
                else
                {
                    try {
                        if (mPlayer != null) {
                              if (mPlayer.isPlaying())
                              {

                                  mPlayer.pause();
                              }
                              else {
                                 //                          mPlayer.prepare();
                                  stop.setVisibility(View.VISIBLE);
                                  mPlayer.start();
                                  }
                        }
                        } catch (Exception e) {
                            e.printStackTrace();
                    }

                }

            }
        };
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");

                String name_data = name.getText().toString();
                String time_data_str = time.getText().toString();

                if (name_data.length()>0)
                {
                    if(time_data_str.length()>0)
                    {
                        final int  time_data = Integer.parseInt(time_data_str);
                        final int[] cou = {time_data};
                        Thread aa = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    while (true)
                                    {
                                        Thread.currentThread().sleep(1000);

                                        handler.sendEmptyMessage(cou[0]);
                                        cou[0]--;
                                        if (cou[0]<=0)
                                        {
                                            handler.sendEmptyMessage(0);
                                            break;
                                        }

                                    }

                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        });
                        aa.start();
                    }
                    else
                        Toast.makeText(getContext(),"输入事件时间",Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(getContext(),"输入事件名称",Toast.LENGTH_SHORT).show();


            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.pause();
                stop.setVisibility(View.GONE);
                tv.setText("");
            }
        });

    }

    private void initView(View view) {
        ok = view.findViewById(R.id.start);
        name = view.findViewById(R.id.event_name);
        time = view.findViewById(R.id.event_time);
        time = view.findViewById(R.id.event_time);
        tv = view.findViewById(R.id.textView7);
        stop = view.findViewById(R.id.Addstop);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
