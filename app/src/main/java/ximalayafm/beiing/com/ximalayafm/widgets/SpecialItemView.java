package ximalayafm.beiing.com.ximalayafm.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ximalayafm.beiing.com.ximalayafm.R;

/**
 * Created by Administrator on 2015/10/22.
 */

/**
 * 用于在发现模块中 推荐栏目中 “精品听单” 的条目展现
 */
public class SpecialItemView extends RelativeLayout {
    /**
     * 听单的图标
     */
    private ImageView imgIcon;

    /**
     * 听单标题
     */
    private TextView txtTitle;

    /**
     * 听单子标题，实际就是描述
     */
    private TextView txtSubTitle;

    /**
     * 听单中的专辑 或者 曲目数量
     */
    private TextView txtNumber;

    /**
     * 最右侧的箭头
     */
    private ImageView  imgArrow;


    private ImageView imgLine;

    /**
     * 在代码中使用 new SpecialItemView(context) 时调用
     *
     * @param context
     */
    public SpecialItemView(Context context) {
        this(context, null);
    }



    /**
     * 在布局xml中使用该控件时使用的构造
     *
     * @param context
     * @param attrs
     */
    public SpecialItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //初始化控件
        imgIcon = new ImageView(context);

        //手写属性
        //LayoutParams 代表 在 布局中的 android:layout_xxx 属性
        //控件要添加到哪一个容器中，就用哪一个容器的params
        RelativeLayout.LayoutParams lp = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        //ImageView垂直居中
        //相当于 layout_centerVertical = "true"
        lp.addRule(CENTER_VERTICAL);
        imgIcon.setLayoutParams(lp);

        imgIcon.setImageResource(R.mipmap.ic_launcher);
        //设置 ImageView的id，注意看values -ids
        imgIcon.setId(R.id.special_item_icon);
        addView(imgIcon);



        //---------------------------------------
        //标题部分
        txtTitle = new TextView(context);
        lp = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        //设置 TextView 与 ImageView 顶部对齐
        lp.addRule(ALIGN_TOP, R.id.special_item_icon);
        lp.addRule(RIGHT_OF, R.id.special_item_icon);

        lp.leftMargin = 20;//单位 像素需要进行机型适配
        txtTitle.setLayoutParams(lp);
        txtTitle.setText("标题");
        //设置标题字体大小
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        txtTitle.setTextColor(Color.BLACK);
        txtTitle.setId(R.id.special_item_title);
        addView(txtTitle);


        //-------------------------------subtitl
        txtSubTitle = new TextView(context);
        lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置对齐规则
        lp.addRule(CENTER_VERTICAL);
        lp.addRule(ALIGN_LEFT, R.id.special_item_title);
        lp.addRule(BELOW, R.id.special_item_title);
        txtSubTitle.setLayoutParams(lp);

        txtSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        txtSubTitle.setId(R.id.special_item_subtitle);
        txtSubTitle.setText("SubTitle");

        //设置单行
        txtSubTitle.setSingleLine();
        addView(txtSubTitle);



        //--------------------------
        txtNumber = new TextView(context);

        lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //设置对齐， 与图片底部对齐， 与标题左对齐
        txtNumber.setLayoutParams(lp);
        lp.addRule(ALIGN_BOTTOM, R.id.special_item_icon);
        lp.addRule(ALIGN_LEFT, R.id.special_item_title);
        txtNumber.setText("5张专辑");

        //给TextView 设置左侧图标
        //这个方法可以同时设置 左上右下 图标
        Resources resources = getResources();
        Drawable drawableLeft = ContextCompat.getDrawable(context, R.drawable.finding_album_img);
        drawableLeft.setBounds(0, 0, 20, 20);
        txtNumber.setCompoundDrawables(drawableLeft, null, null, null);
        txtNumber.setId(R.id.special_item_num);
        addView(txtNumber);


        //箭头
        imgArrow = new ImageView(context);
        lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        imgArrow.setImageResource(R.drawable.arrow_right_1);
        //容器右对齐
        lp.addRule(ALIGN_PARENT_RIGHT);

        lp.addRule(CENTER_VERTICAL);

        imgArrow.setLayoutParams(lp);

        addView(imgArrow);


        //-----------------------------
        imgLine = new ImageView(context);

        lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);

        lp.addRule(ALIGN_LEFT, R.id.special_item_title);

        lp.addRule(ALIGN_PARENT_BOTTOM);
        lp.setMargins(0, 5, 0, 0);

        imgLine.setLayoutParams(lp);
        imgLine.setBackgroundColor(Color.GRAY);
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title){
//        if (title != null) {
//
//        }

        txtTitle.setText(title);
    }

    /**
     * 设置子标题
     * @param subTitle
     */
    public void setSubTitle(String subTitle){
        txtSubTitle.setText(subTitle);
    }


    public void setNumber(String number){
        txtNumber.setText(number);
    }

    public void setShowBottomLine(boolean show){
        if(show){
            imgLine.setVisibility(View.VISIBLE);
        } else{
            imgLine.setVisibility(View.INVISIBLE);
        }
    }

    public void setRightArrowResource(int resId){
        imgArrow.setImageResource(resId);
    }

}

















