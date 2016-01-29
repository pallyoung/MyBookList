package com.aplus6.mybooklist.views;

import com.aplus6.mybooklist.R;
import com.aplus6.mybooklist.servieces.DBServices;
import com.aplus6.mybooklist.viewHelper.MyListViewAdapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainFragment extends Fragment {
	private MyListViewAdapter mlvAdapter;
	private ListView lv;
	private Button addListBtn;
	private Context ctx;
	private RelativeLayout body;
	private OnTouchListener onTouch;
	@Override  
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)  
    {  
		View view  = inflater.inflate(R.layout.view_main, container, false);
		lv =(ListView) view.findViewById(R.id.mybooklistview);
		addListBtn = (Button) view.findViewById(R.id.addListBtn);
		mlvAdapter =  new MyListViewAdapter(inflater,getActivity());
		lv.setAdapter(mlvAdapter);
		body = (RelativeLayout) view.findViewById(R.id.body);
		onTouch = new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent me) {
				if(me.getAction() == MotionEvent.ACTION_DOWN &&lv.getTag()!=null){
					((SwipeView)lv.getTag()).smoothScrollToStart();
				}				
				return false;
			}			
		};
		addListBtn.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent me) {
				// TODO Auto-generated method stub
				if(me.getAction()==MotionEvent.ACTION_DOWN){
					if(lv.getTag()!=null){
						((SwipeView)lv.getTag()).smoothScrollToStart();
					}
					AddListFragment alf =  new AddListFragment();
					FragmentManager fm = getFragmentManager();  
				    FragmentTransaction tx = fm.beginTransaction(); 
				    tx.add(android.R.id.content,alf, "addList");
				    tx.addToBackStack(null);
				    tx.commit();
				}			
				return false;
			}
			
		});
		body.setOnTouchListener(onTouch);
		lv.setOnTouchListener(onTouch);
        return view;  
    }  
}
