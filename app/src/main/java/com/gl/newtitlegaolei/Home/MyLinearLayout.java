package com.gl.newtitlegaolei.Home;

/**
 * 作者：高镭
 * 时间：2017/2/22 16:42
 * 班级：1501A
 */
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by cj on 2017/2/22.
 */

public class MyLinearLayout extends LinearLayout {
    //在java 代码里自己new的 时候
    public MyLinearLayout(Context context) {
        this(context,null);
    }

    //在布局文件里 应用的时候
    public MyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
