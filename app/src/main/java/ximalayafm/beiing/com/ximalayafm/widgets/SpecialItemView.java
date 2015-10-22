package ximalayafm.beiing.com.ximalayafm.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
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
        txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        txtTitle.setTextColor(Color.BLACK);
        txtTitle.setId(R.id.special_item_title);
        addView(txtTitle);


        //-------------------------------subtitl
        txtSubTitle = new TextView(context);
        lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置对齐规则
        lp.addRule(CENTER_VERTICAL);
        lp.addRule(ALIGN_LEFT,R.id.special_item_title );
        lp.addRule(BELOW,R.id.special_item_title );
        txtSubTitle.setLayoutParams(lp);

        txtSubTitle.setId(R.id.special_item_subtitle);
        txtSubTitle.setText("SubTitle");
        addView(txtSubTitle);

    }

}






