package com.minorproject.minorproject.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorSpace;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minorproject.minorproject.R;
import com.minorproject.minorproject.models.ModelMenu;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter1 extends BaseExpandableListAdapter {
    private Context context;
    private List<ModelMenu> listDataHeader;
    private HashMap<ModelMenu, List<ModelMenu>> listDataChild;

    public ExpandableListAdapter1(Context context, List<ModelMenu> listDataHeader,
                                  HashMap<ModelMenu, List<ModelMenu>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }

    @Override
    public ModelMenu getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).menuName;
        final String childIcon = getChild(groupPosition,childPosition).icon;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_child, null);
        }

        TextView txtListChild = convertView
                .findViewById(R.id.lblListItem);
/*
        Resources contextResources = context.getResources();
        int Resid = contextResources.getIdentifier(childIcon,"drawable",context.getPackageName());
        Drawable groupDrawable = contextResources.getDrawable(Resid);
        //Set ImageView
        ImageView groupImage = (ImageView)convertView.findViewById(R.id.lblListIcon);
        groupImage.setImageDrawable(groupDrawable);*/
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null)
            return 0;
        else
            return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                    .size();
    }

    @Override
    public ModelMenu getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).menuName;
        String headerIcon  = getGroup(groupPosition).icon;
        boolean haschildren = getGroup(groupPosition).hasChildren;


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header, null);
        }

        ImageView dropdown = convertView.findViewById(R.id.drop);
        if (!haschildren){
            dropdown.setVisibility(View.GONE);
        }

        if(haschildren){
            dropdown.setVisibility(View.VISIBLE);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        Resources contextResources = context.getResources();
        int Resid = contextResources.getIdentifier(headerIcon,"drawable",context.getPackageName());
        Drawable groupDrawable = contextResources.getDrawable(Resid);
        //Set ImageView
        ImageView groupImage = (ImageView)convertView.findViewById(R.id.lblListIcon);
        groupImage.setImageDrawable(groupDrawable);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}