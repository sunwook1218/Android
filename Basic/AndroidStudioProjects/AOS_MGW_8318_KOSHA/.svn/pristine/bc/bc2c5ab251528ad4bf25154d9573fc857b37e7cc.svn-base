package com.hs.mobile.gw.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.vo.DeptVO;


public class DeptSpinnerAdapter extends BaseAdapter {
	 
    Context context;
    ArrayList<DeptVO> data;
    LayoutInflater inflater;
 
    public DeptSpinnerAdapter(Context context, ArrayList<DeptVO> data){
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
 
    @Override
    public int getCount() {
        if(data!=null) return data.size();
        else return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.custom_spinner_layout, parent, false);
        }
 
        if(data!=null){
            //데이터세팅
            DeptVO deptVO = (DeptVO) data.get(position);
            ((TextView)convertView.findViewById(R.id.spinnerText)).setText(deptVO.getName());
        }
 
        return convertView;
    }
 
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = inflater.inflate(R.layout.custom_spinner_dropdown_line, parent, false);
        }
 
        //데이터세팅
        DeptVO deptVO = (DeptVO) data.get(position);
        ((TextView)convertView.findViewById(R.id.spinnerText)).setText(deptVO.getFullName());
 
        return convertView;
    }
 
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
}


