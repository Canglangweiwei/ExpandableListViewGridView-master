package com.ziaho.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.cn.main.R;

/**
 * <p>
 * 功能说明：主界面
 * </p>
 * Created by weiwei On 2017-3-2 上午11:41:24
 */
public class MainActivity extends Activity {

    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        ExpandableListViewAdapter mExpandableListViewAdapter = new ExpandableListViewAdapter(this);
        mExpandableListView.setAdapter(mExpandableListViewAdapter);

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                Log.e(TAG, "groupPosition=" + groupPosition + ",childPosition=" + childPosition);
                return false;
            }
        });

        // 设置Group默认展开
        int groupCount = mExpandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            mExpandableListView.expandGroup(i);
        }
    }
}
