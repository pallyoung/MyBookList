package com.aplus6.mybooklist.viewHelper;

import java.util.ArrayList;

import com.aplus6.mybooklist.R;
import com.aplus6.mybooklist.models.MBookList;
import com.aplus6.mybooklist.servieces.MBookListManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListViewAdapter extends BaseAdapter {
	private ArrayList<MBookList> list = MBookListManager.getMBookLists();
	private LayoutInflater inflater;
	private ViewHolder holder;
	public MyListViewAdapter(LayoutInflater inflater){
		this.inflater = inflater;
		MBookListManager.setOnListChangedListener(new MBookListManager.OnListChangedListener() {
			
			@Override
			public void onReceive(ArrayList<MBookList> value) {
				MyListViewAdapter.this.notifyDataSetChanged();				
			}
		});
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
				return arg0;
	}

	@Override
	public View getView(int p, View v, ViewGroup vg) {
		if(v == null){
			v = inflater.inflate(R.layout.view_booklist_item, null);
			holder  = new ViewHolder();
			holder.listName = (TextView)v.findViewById(R.id.list_name);
			v.setTag(holder);
		}else{
			holder = (ViewHolder) v.getTag();
		}
		MBookList bl= list.get(p);
		holder.listName.setText(bl.getName());
		holder.listName.setTag(bl);
		return v;
	}
	private class ViewHolder{
		TextView listName;
	}
}
