package com.example.asus.wenzi_zhuan_yuyin;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.zhidingyi_view.R;

import java.util.Locale;

public class YuYin2_Activity extends AppCompatActivity implements TextToSpeech.OnInitListener, View.OnClickListener {

    private TextToSpeech textToSpeech;
    private TextView tv_quxian;
    private TextView tv_danbian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yu_yin);
        initView();
        textToSpeech = new TextToSpeech(YuYin2_Activity.this, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.stop(); // 不管是否正在朗读TTS都被打断
        textToSpeech.shutdown(); // 关闭，释放资源
    }

    private void initVoice(String str) {
        if (textToSpeech != null && !textToSpeech.isSpeaking()) {
            textToSpeech.setPitch(0.0f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            textToSpeech.speak(str,
                    TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void initView() {
        tv_quxian = (TextView) findViewById(R.id.tv_quxian);
        tv_danbian = (TextView) findViewById(R.id.tv_danbian);
        tv_danbian.setOnClickListener(this);
        tv_quxian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_danbian:
                initVoice("单边板");
                break;
            case R.id.tv_quxian:
                initVoice("曲线行驶");
                break;
        }
    }
}
