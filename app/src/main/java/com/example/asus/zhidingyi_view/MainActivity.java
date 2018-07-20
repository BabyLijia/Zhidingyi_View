package com.example.asus.zhidingyi_view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CustomListView mcustom_lv;
    private String[] mStr = {"A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mcustom_lv = (CustomListView) findViewById(R.id.mcustom_lv);
        //1Android系统适配器与布局 显示ListView
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_list_item_1, mStr) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null)
                    convertView = View.inflate(MainActivity.this, R.layout.layout_lv_item, null);
                TextView tv = convertView.findViewById(R.id.item_tv);
                tv.setText(mStr[position]);
                return convertView;
            }
        };
        mcustom_lv.setAdapter(stringArrayAdapter);

        //2添加头图片
        View inflate = View.inflate(MainActivity.this, R.layout.layout_header_view, null);
        mcustom_lv.addHeaderView(inflate);
        ImageView iv = inflate.findViewById(R.id.header_view);
        mcustom_lv.getImageView(iv);

    }
}
