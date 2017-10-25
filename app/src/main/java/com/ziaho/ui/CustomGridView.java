package com.ziaho.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 
 * <p>
 * 功能说明：自定义GridView
 * </p>
 *
 * Created by weiwei On 2017-3-2 上午11:39:08
 */
public class CustomGridView extends GridView {

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
