package com.aplus6.mybooklist.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MyListView extends ListView {
	private SwipeView sv;
	public MyListView(Context context, AttributeSet attrs,int defStyleAttr) {
		super(context, attrs,defStyleAttr);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public MyListView(Context context) {
		super(context);
	}
	public void setSwipeView(SwipeView sv){
		this.sv = sv;
	}
	public SwipeView getSwipeView(){
		return sv;
	}
	
}
