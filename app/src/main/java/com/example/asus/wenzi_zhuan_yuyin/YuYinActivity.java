package com.example.asus.wenzi_zhuan_yuyin;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.zhidingyi_view.MainActivity;
import com.example.asus.zhidingyi_view.R;
import com.iflytek.cloud.SpeechUtility;

import java.util.Locale;

public class YuYinActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_quxian;
    private TextView tv_danbian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_yin);
        SpeechUtility.createUtility(getApplicationContext(), "appid=5b508634");
        initView();
    }

    private void initView() {
        tv_quxian = (TextView) findViewById(R.id.tv_quxian);
        tv_danbian = (TextView) findViewById(R.id.tv_danbian);
        tv_danbian.setOnClickListener(this);
        tv_quxian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AudioUtils.getInstance().init(YuYinActivity.this); //初始化语音对象
        switch (v.getId()) {
            case R.id.tv_quxian:
                AudioUtils.getInstance().speakText("曲线行驶"); //播放语音
                break;
            case R.id.tv_danbian:
                AudioUtils.getInstance().speakText("单边板"); //播放语音
                break;
        }
    }
}
