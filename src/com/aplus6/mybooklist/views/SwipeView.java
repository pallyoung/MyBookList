package com.aplus6.mybooklist.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class SwipeView extends HorizontalScrollView {
	private LinearLayout wrapper;
	private ContentView content;
	public SwipeView(Context context) {
		super(context);
		initView();
	}
	public SwipeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}
	public SwipeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}
	@SuppressLint("ClickableViewAccessibility")
	private void initView(){
		this.setHorizontalScrollBarEnabled(false);
		wrapper = new LinearLayout(getContext());
		content = new ContentView(getContext());
		content.setOrientation(LinearLayout.HORIZONTAL);	
		this.addView(wrapper,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		wrapper.addView(content);
		setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				final Runnable runner;
				final int w = wrapper.getMeasuredWidth()-content.getMeasuredWidth();
				final int x = getScrollX();
				View parent = ((View) getParent());
				switch(me.getAction()){
				case MotionEvent.ACTION_DOWN:{
					if(parent.getTag()!=null&&(!parent.getTag().equals(SwipeView.this))){
						((SwipeView)parent.getTag()).smoothScrollToStart();
					}
					parent.setTag(SwipeView.this);				
					break;
				}
				case MotionEvent.ACTION_UP:
					if(x<100){
						runner = new Runnable(){
							@Override
							public void run() {
								smoothScrollTo(0, 0);						
							}							
						};
					}else{
						runner = new Runnable(){
							@Override
							public void run() {
								smoothScrollTo(w, 0);						
							}							
						};
					}
					SwipeView.this.post(runner);				
					break;
				}
				return false;
			}
			
		});
	}
	public void inflateContent(View view){
		content.addView(view,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
	}
	public void inflateRight(View view){
		wrapper.addView(view);
	}
	public void smoothScrollToStart(){
		post(new Runnable(){
			@Override
			public void run(){
				smoothScrollTo(0,0);
			}
		});
	}
	class ContentView extends LinearLayout{
		public ContentView(Context context) {
			super(context);
		}
		public ContentView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public ContentView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
		}
		 @Override
		 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
			 
			 View parentScrollView=((View)(getParent().getParent().getParent()));
			 widthMeasureSpec=parentScrollView.getMeasuredWidth();
			 super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			 setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
			 
		 }
		 @Override
		 protected void onLayout(boolean changed, int left, int top, int right, int bottom){
			 super.onLayout(changed, left, top, right, bottom);
			 View v = getChildAt(0);
			 v.layout(0,0,getWidth(),getHeight());
		 }
	}
}

