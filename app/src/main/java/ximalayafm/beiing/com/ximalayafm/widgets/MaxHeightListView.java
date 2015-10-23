package ximalayafm.beiing.com.ximalayafm.widgets;

import java.text.AttributedCharacterIterator.Attribute;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *  自动扩充尺寸的ListVIew，适用于 ScrollView + ListView
 */
public class MaxHeightListView extends ListView {

	public MaxHeightListView(Context context) {
		this(context, null, 0);
	}

	public MaxHeightListView(Context context, AttributeSet attr) {
		this(context, attr, 0);
	}

	public MaxHeightListView(Context context, AttributeSet attr, int style) {
		super(context, attr, style);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
