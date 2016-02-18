package com.aplus6.mybooklist.viewHelper;

import java.util.ArrayList;

import com.aplus6.mybooklist.R;
import com.aplus6.mybooklist.models.Book;
import com.aplus6.mybooklist.models.MBookList;
import com.aplus6.mybooklist.servieces.MBookListManager;
import com.aplus6.mybooklist.servieces.OnListChangedListener;
import com.aplus6.mybooklist.views.BooksFragment;
import com.aplus6.mybooklist.views.SwipeView;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MyListViewAdapter extends BaseAdapter {
	private MBookListManager mblManager;
	private ArrayList<MBookList> list ;
	private LayoutInflater inflater;
	private ViewHolder holder;
	private Context ctx;
	public MyListViewAdapter(LayoutInflater inflater,Context ctx){
		this.inflater = inflater;
		this.ctx = ctx;
		mblManager  =  MBookListManager.getInstance();
		list = mblManager.getList();
		mblManager.setOnListChangedListener(new OnListChangedListener() {		
			@Override
			public void onReceive() {
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
		if(v==null){
			v = new SwipeView(ctx);
			LinearLayout view = (LinearLayout)inflater.inflate(R.layout.view_booklist_item, null);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 200);
			v.setLayoutParams(lp);
			holder  = new ViewHolder();
			holder.item_left = view.findViewById(R.id.item_left);
			holder.item_main = view.findViewById(R.id.item_main);
			holder.listName = (TextView)view.findViewById(R.id.list_name);
			holder.btn = (Button)view.findViewById(R.id.delBtn);
			view.removeAllViews();
			((SwipeView) v).inflateContent(holder.item_main);
			((SwipeView) v).inflateRight(holder.item_left);
			holder.btn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					mblManager.removeItem((MBookList)holder.listName.getTag());
				}
				
			});
			v.setTag(holder);		
		}else{
			holder = (ViewHolder) v.getTag();
		}
		holder.item_main.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				FragmentManager fm = ((Activity)ctx).getFragmentManager();
				BooksFragment bf = new BooksFragment((MBookList)holder.listName.getTag());
				FragmentTransaction tx = fm.beginTransaction(); 
			    tx.add(android.R.id.content,bf, "addList");
			    tx.addToBackStack(null);
			    tx.commit();
			}
			
		});
		MBookList bl= list.get(p);
		holder.listName.setText(bl.getName());
		holder.listName.setTag(bl);
		return v;
	}
	private class ViewHolder{
		View item_main;
		View item_left;
		TextView listName;
		Button btn;
	}
}
