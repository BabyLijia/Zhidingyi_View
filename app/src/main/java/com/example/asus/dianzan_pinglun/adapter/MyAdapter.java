package com.example.asus.dianzan_pinglun.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.asus.dianzan_pinglun.bean.PersonBean;
import com.example.asus.zhidingyi_view.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<PersonBean> mData;
    private List<Boolean> mCheckedList;
    private Context context;
    private PopupWindow popupWindow = null;
    private View inflate;
    private TextView pinglun;
    private TextView zan;

    public MyAdapter(List<PersonBean> getData, Context context) {
        this.mData = getData;
        this.context = context;
    }

    public void setmData(List<PersonBean> mData, List<Boolean> mCheckedList) {
        this.mData = mData;
        this.mCheckedList = mCheckedList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return null == mData ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private int widthPop;
    private int heightPop;
    private List<String> mRenming = new ArrayList<>();
    int a = 1;
    int b;

    @SuppressLint("WrongConstant")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_dianzan, null);

            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflate = li.inflate(R.layout.layout_pop, null, false);

            popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            // popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setTouchable(true);

            inflate.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            widthPop = inflate.getMeasuredWidth();
            heightPop = inflate.getMeasuredHeight();

            View parent2 = popupWindow.getContentView();
            zan = parent2.findViewById(R.id.tv_zan);
            pinglun = parent2.findViewById(R.id.tv_pinglun);

            holder.mimg = (ImageView) convertView.findViewById(R.id.mimg);
            holder.mname = (TextView) convertView.findViewById(R.id.mname);
            holder.mmessage = (TextView) convertView.findViewById(R.id.mmessage);
            holder.mimg_pl = (CheckBox) convertView.findViewById(R.id.mimg_pl);
            holder.tv_dzRenShu = (TextView) convertView.findViewById(R.id.tv_dzRenShu);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mimg.setImageResource(mData.get(position).getImg());
        holder.mname.setText(mData.get(position).getName());
        holder.mmessage.setText(mData.get(position).getMessage());
        holder.mimg_pl.setChecked(mCheckedList.get(position));

        holder.mimg_pl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //为了一开始防止是否是选中状态，所以循环每条item为false
                    for (int i = 0; i < mCheckedList.size(); i++) {
                        mCheckedList.set(i, false);
                    }
                    //当我根据checkbox的索引点击1条时刷新适配器
                    mCheckedList.set(position, true);
                    notifyDataSetChanged();
                    //显示popowindow所处位置使用checkbox控件获取到pop的高度
                    int heightMoreBtnView = holder.mimg_pl.getHeight();
                    //使用checkbox减去
                    popupWindow.showAsDropDown(holder.mimg_pl, -widthPop,
                            -(heightPop + heightMoreBtnView) / 2);
                } else {
                    for (int i = 0; i < mCheckedList.size(); i++) {
                        mCheckedList.set(i, false);
                    }
                    notifyDataSetChanged();
                }


                pinglun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                zan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(holder.tv_dzRenShu.getText().toString().isEmpty()){

                            
                        }


                    }

                });
                //显示键盘
              /*  popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
                popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);*/
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        public ImageView mimg;
        public TextView mname;
        public TextView mmessage;
        public CheckBox mimg_pl;
        public TextView tv_dzRenShu;

    }
}
