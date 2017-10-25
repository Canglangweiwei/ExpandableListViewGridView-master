package com.ziaho.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.main.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 * 功能说明：自定义Adapter
 * </p>
 * Created by weiwei On 2017-3-2 上午11:39:58
 */
class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private static final String[] group = {"常用", "搞笑", "原创", "资讯", "体育", "游戏", "汽车", "娱乐"};
    private static final String[][] gridViewChild = {
            {"明星八卦", "雷人囧事", "网络红人", "科技资讯", "美女花边", "游戏达人", "美女车模", "疯狂恶搞"},
            {"疯狂恶搞", "搞笑综艺", "原创搞笑", "爆笑宠物", "雷人囧事"},
            {"原创热点", "原创影视", "音乐动画", "火星搞笑", "校园作品", "网络红人", "拍客"},
            {"社会资讯", "国内资讯", "国际资讯", "财富资讯", "科技资讯"},
            {"篮球天地", "足球世界", "综合体育", "极限运动", "武术摔角", "美女花边"},
            {"网络游戏", "电子竞技", "单机电玩", "游戏达人", "工会战队"},
            {"新车速递", "车型推荐", "改装酷玩", "汽车广告", "评测报告", "美女车模"}, {"明星八卦", "影视资讯"}};
    private LayoutInflater mInflater;
    private Context context;

    ExpandableListViewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return gridViewChild[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * child视图生成器
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView =
                    mInflater.inflate(R.layout.channel_expandablelistview_item, parent, false);
            mViewChild.gridView =
                    (GridView) convertView.findViewById(R.id.channel_item_child_gridView);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        SimpleAdapter mSimpleAdapter =
                new SimpleAdapter(context, setGridViewData(gridViewChild[groupPosition]),
                        R.layout.channel_gridview_item, new String[]{"channel_gridview_item"},
                        new int[]{R.id.channel_gridview_item});
        mViewChild.gridView.setAdapter(mSimpleAdapter);
        setGridViewListener(mViewChild.gridView);
        mViewChild.gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        return convertView;
    }

    /**
     * 设置gridview点击事件监听
     */
    private void setGridViewListener(final GridView gridView) {
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view instanceof TextView) {
                    // 如果想要获取到哪一行，则自定义gridview的adapter，item设置2个textview一个隐藏设置id，显示哪一行
                    TextView tv = (TextView) view;
                    Toast.makeText(context, "position=" + position + "||" + tv.getText(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 设置gridview数据
     */
    private ArrayList<HashMap<String, Object>> setGridViewData(String[] data) {
        ArrayList<HashMap<String, Object>> gridItem = new ArrayList<HashMap<String, Object>>();
        for (String aData : data) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("channel_gridview_item", aData);
            gridItem.add(hashMap);
        }
        return gridItem;
    }

    // getChildrenCount返回1 因为用一个Item 来加载GridView
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group[groupPosition];
    }

    @Override
    public int getGroupCount() {
        return group.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * group视图生成器
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.channel_expandablelistview, parent, false);
            mViewChild.textView = (TextView) convertView.findViewById(R.id.channel_group_name);
            mViewChild.imageView =
                    (ImageView) convertView.findViewById(R.id.channel_imageview_orientation);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        /**
         * 展开图标
         */
        if (isExpanded) {// 展开
            mViewChild.imageView.setImageResource(R.drawable.channel_expandablelistview_top_icon);
        } else {// 折叠
            mViewChild.imageView
                    .setImageResource(R.drawable.channel_expandablelistview_bottom_icon);
        }
        mViewChild.textView.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private ViewChild mViewChild;

    /**
     * <p>
     * 功能说明：child持有类
     * </p>
     * <p>
     * Created by weiwei On 2017-3-2 下午12:49:13
     */
    private static class ViewChild {
        ImageView imageView;
        TextView textView;
        GridView gridView;
    }
}
