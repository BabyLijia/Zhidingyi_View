package com.example.asus.dianzan_pinglun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus.dianzan_pinglun.adapter.MyAdapter;
import com.example.asus.dianzan_pinglun.bean.PersonBean;
import com.example.asus.zhidingyi_view.R;

import java.util.ArrayList;
import java.util.List;

public class DianZan_plActivity extends AppCompatActivity {

    private ListView mlv;
    private List<PersonBean> data;
    private List<Boolean> mCheckedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_zan_pl);
        final List<PersonBean> mdata = getData();
        mCheckedList = new ArrayList<Boolean>();
        for (int i = 0; i <data.size(); i++) {
            mCheckedList.add(false);
        }
        mlv = (ListView) findViewById(R.id.mlv);
        final MyAdapter myAdapter = new MyAdapter(mdata, DianZan_plActivity.this);
        mlv.setAdapter(myAdapter);
        myAdapter.setmData(mdata,mCheckedList);
        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCheckedList.set(position,!mCheckedList.get(position));
                myAdapter.setmData(mdata,mCheckedList);
                myAdapter.notifyDataSetChanged();
            }
        });

    }

    private List<PersonBean> getData() {
        int ITEM_COUNT = 20;

        data = new ArrayList<>();

        data.add(new PersonBean(R.drawable.wenwen, "薄荷栗", "我学过跆拳道，都给我跪下唱征服"));
        data.add(new PersonBean(R.drawable.xuyuan1, "欣然", "走遍天涯海角，唯有我家风景最好，啊哈哈"));
        data.add(new PersonBean(R.drawable.xuyuan2, "陈磊_CL", "老子以后要当行长的，都来找我借钱吧，now"));
        data.add(new PersonBean(R.drawable.xuyuan3, "永恒依然", "房子车子都到碗里来"));
        data.add(new PersonBean(R.drawable.xuyuan4, "蓝珊", "你们这群傻×，我笑而不语"));
        data.add(new PersonBean(R.drawable.wenwen, "玫瑰", "我学过跆拳道，都给我跪下唱征服"));
        data.add(new PersonBean(R.drawable.xuyuan1, "杨然", "房子车子都到碗里来"));
        data.add(new PersonBean(R.drawable.xuyuan2, "陈磊_CL", "老子以后要当行长的，都来找我借钱吧，now"));
        data.add(new PersonBean(R.drawable.xuyuan3, "永恒之心", "你们这群傻×，我笑而不语"));
        data.add(new PersonBean(R.drawable.xuyuan4, "佳姝", "走遍天涯海角，唯有我家风景最好，啊哈哈"));
        return data;
    }
}
