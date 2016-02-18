package com.aplus6.mybooklist.viewHelper;

import java.util.ArrayList;

import com.aplus6.mybooklist.R;
import com.aplus6.mybooklist.models.Book;
import com.aplus6.mybooklist.servieces.BooksManager;
import com.aplus6.mybooklist.servieces.OnListChangedListener;
import com.aplus6.mybooklist.views.SwipeView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class BookListViewAdapter extends BaseAdapter {
	private Context ctx;
	private ArrayList<Book> books;
	private ViewHolder holder;
	private BooksManager bm;
	private LayoutInflater inflater;
	private long l_id;
	public BookListViewAdapter(LayoutInflater inflater,Context ctx,long l){
		this.ctx = ctx;
		this.l_id = l;
		bm = BooksManager.getInstance();
		this.inflater =  inflater;
		this.books = bm.getBooksByList(l_id);
		bm.setOnListChangedListener(new OnListChangedListener(){

			@Override
			public void onReceive() {
				books = bm.getBooksByList(l_id);
				notifyDataSetChanged();
			}
			
		});
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return books.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return books.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(int index, View v, ViewGroup vg) {
		if(v==null){
			v = new SwipeView(ctx);
			holder = new ViewHolder();
			LinearLayout view = (LinearLayout) inflater.inflate(R.layout.view_books_item,null);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 100);
			v.setLayoutParams(lp);
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
					bm.removeItem((Book)holder.listName.getTag());
				}
				
			});
			v.setTag(holder);	
		}else{
			holder = (ViewHolder) v.getTag();
		}
//		holder.item_main.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				FragmentManager fm = ((Activity)ctx).getFragmentManager();
//				BooksFragment bf = new BooksFragment((MBookList)holder.item_main.getTag());
//				FragmentTransaction tx = fm.beginTransaction(); 
//			    tx.add(android.R.id.content,bf, "addList");
//			    tx.addToBackStack(null);
//			    tx.commit();
//			}
//			
//		});
		Book b= books.get(index);
		holder.listName.setText(b.getName());
		holder.listName.setTag(b);
		return v;
	}
	private class ViewHolder{
		View item_main;
		View item_left;
		TextView listName;
		Button btn;
	}
}
