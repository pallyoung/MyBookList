package com.aplus6.mybooklist.views;

import com.aplus6.mybooklist.R;
import com.aplus6.mybooklist.models.Book;
import com.aplus6.mybooklist.models.MBookList;
import com.aplus6.mybooklist.viewHelper.BookListViewAdapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class BooksFragment extends Fragment {
	private MBookList mbList;
	private BookListViewAdapter blvAdapter;
	private Button addBookBtn;
	private TextView title;
	private ListView listView;
	public BooksFragment(MBookList mbList){
		this.mbList = mbList;
		
	}
	@Override  
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)  
    {  
		View view  = inflater.inflate(R.layout.view_books, container, false);
		blvAdapter = new BookListViewAdapter(inflater,this.getActivity(),mbList.getId());
		addBookBtn = (Button) view.findViewById(R.id.addBookBtn);
		title = (TextView)view.findViewById(R.id.title);
		listView = (ListView)view.findViewById(R.id.booksListView);
		listView.setAdapter(blvAdapter);
		title.setText(mbList.getName());
		addBookBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				BookInfoFragment bf =  new BookInfoFragment(mbList);
				FragmentManager fm = getFragmentManager();  
			    FragmentTransaction tx = fm.beginTransaction(); 
			    tx.add(android.R.id.content,bf, "books");
			    tx.addToBackStack(null);
			    tx.commit();
			}
			
		});
        return view;  
    }  
}
