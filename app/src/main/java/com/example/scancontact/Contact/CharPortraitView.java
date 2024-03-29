package com.example.scancontact.Contact;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;

import com.example.scancontact.R;

import java.util.Random;


public class CharPortraitView extends AppCompatTextView {

    private boolean isRandom = false;
    private Random random;
    private int mBackColor;
    private Context mContext;
    private String[] colors;
    private boolean mHead=true;
    private String mContent ="Aa";


    public CharPortraitView(Context context) {
        this(context, null);
    }

    public CharPortraitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
        build();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int minSize = Math.min(width, height);
        setMeasuredDimension(minSize, minSize);
    }

    private void init(AttributeSet attrs) {
        // 初始化随机数
        random = new Random();
        // 获取参数
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.CharPortraitView);
        // 获取是否随机背景
        isRandom = array.getBoolean(R.styleable.CharPortraitView_random, false);
        // 获取背景颜色
        mBackColor = array.getColor(R.styleable.CharPortraitView_back_color, Color.BLUE);
        array.recycle();
    }
    //设置
    private void build() {
        if (!isRandom) {
            mBackColor = getRandomColor(); //产生随机颜色
        }
        // 设置居中
        setGravity(Gravity.CENTER);
        // 设置自适应文字大小
        TextViewCompat.setAutoSizeTextTypeWithDefaults(this, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        // 设置背景颜色
        setBackgroundResource(R.drawable.shape_drawable);
        GradientDrawable drawable = (GradientDrawable) getBackground();
        drawable.setColor(mBackColor);
        setBackgroundDrawable(drawable);
        if (mContent==null){
            return;
        }
        if (mHead){
            setText(mContent.substring(0, 1));//取首字符显示
        }else {
            setText(mContent.substring(mContent.length()-1, mContent.length()));//取末尾字符显示
        }
    }
    /**
     * 设置文本内容
     *@param mHead  true 第一个字符 false 最后一个字符
     *@return
     */
    public CharPortraitView setHead(boolean mHead) {
        this.mHead=mHead;
        build();
        return this;
    }
    //设置文本内容
    public CharPortraitView setContent(String str) {
        this.mContent=str;
        build();
        return this;
    }

    //设置背景颜色
    public CharPortraitView setBackColor(int backColor) {
        mBackColor = backColor;
        isRandom = false;
        build();
        return this;
    }
    //设置是否开启随机颜色
    public CharPortraitView setRandom(boolean isRandom) {
        this.isRandom = isRandom;
        build();
        return this;
    }
    /**
     * 设置随机背景颜色数组
     * @param colors
     * @return
     */
    public CharPortraitView setBackColor(String[] colors){
        this.colors=colors;
        build();
        return this;
    }
    /**
     * 获取随机背景颜色
     * @return
     */
    private int getRandomColor() {
        String[] colorArray;
        if (colors!=null&&colors.length>0){
            colorArray=colors;
        }else {
            colorArray=mContext.getResources().getStringArray(R.array.color);
        }
        int value = random.nextInt(colorArray.length);
        return Color.parseColor(colorArray[value]);
    }

}


