package com.example.nascounter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.nascounter.R;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private String[] groups={"标准标签","NAS标签"};
    private String[] childs={"14443","15693"};
    public MyExpandableListAdapter(Context context){
        mContext=context;
    }
    static class GroupViewHolder{
        TextView tv_group;
    }
    static class ChildViewHolder{
        TextView tv_child;
    }
    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return childs.length;
    }

    @Override
    public Object getGroup(int i) {
        return groups[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return childs[i1];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder;
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_group,viewGroup,false);
            groupViewHolder=new GroupViewHolder();
            groupViewHolder.tv_group=view.findViewById(R.id.tv_group);
            view.setTag(groupViewHolder);
        }else {
            groupViewHolder=(GroupViewHolder) view.getTag();
        }
        groupViewHolder.tv_group.setText(groups[i]);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder;
        if (view==null){
            view=LayoutInflater.from(mContext).inflate(R.layout.item_child,viewGroup,false);
            childViewHolder=new ChildViewHolder();
            childViewHolder.tv_child=view.findViewById(R.id.tv_child);
            view.setTag(childViewHolder);
        }else {
            childViewHolder=(ChildViewHolder)view.getTag();
        }
        childViewHolder.tv_child.setText(childs[i1]);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private String[][] getTwoDimensionalArray(String[] array) {
        String[][] twoDimensionalArray = null;
        for (int i = 0; i < array.length; i++) {
            String[] tempArray = array[i].split(",");
            if (twoDimensionalArray == null) {
                twoDimensionalArray = new String[array.length][tempArray.length];
            }
            for (int j = 0; j < tempArray.length; j++) {
                twoDimensionalArray[i][j] = tempArray[j];
            }
        }
        return twoDimensionalArray;
    }
}
