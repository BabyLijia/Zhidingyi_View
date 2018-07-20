package com.example.asus.zhidingyi_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

public class CustomListView extends ListView {
    private ImageView mIv;

    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mMaxHeight;
    private boolean flag = true;
    private int ivHeight, picHeight;

    public void getImageView(ImageView iv) {
        mIv = iv;
        mIv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // api 16 mIv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //图片控件高度
                if (flag) {
                    ivHeight = mIv.getHeight();
                    flag = false;
                }
                Log.e("TAG_iv", ivHeight + "+++++++++");

            }
        });

        //图片高度
        picHeight = mIv.getDrawable().getIntrinsicHeight();
        mMaxHeight = ivHeight > picHeight ? ivHeight + 200 : picHeight;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.e("TAG", "isTouchEvent========" + isTouchEvent + "======" + deltaY);

        if (mIv != null) {

            if (deltaY < 0 && isTouchEvent) {
                int dy = mIv.getHeight() - deltaY;

                //如果现在滑动的高度大于mMaxHeight
                if (dy > mMaxHeight) {
                    //就把mMaxHeight赋值给现在的高度
                    dy = mMaxHeight;
                }

                //将获取到的控件的新高度赋值
                ViewGroup.LayoutParams layoutParams = mIv.getLayoutParams();
                layoutParams.height = dy;
                //刷新
                requestLayout();

            }
        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                //下拉回弹动画
                ValueAnimator valueAnimator = ObjectAnimator.ofInt(mIv.getHeight(), ivHeight);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int  value = (int) valueAnimator.getAnimatedValue();
                        mIv.getLayoutParams().height=value;
                        requestLayout();
                    }
                });
                //弹射状擦值器
                valueAnimator.setInterpolator(new OvershootInterpolator());
                valueAnimator.setDuration(500);
                valueAnimator.start();
                break;
        }
        return super.onTouchEvent(ev);
    }
}
