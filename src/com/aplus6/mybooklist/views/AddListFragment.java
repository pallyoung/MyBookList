package com.aplus6.mybooklist.views;

import com.aplus6.mybooklist.MainActivity;
import com.aplus6.mybooklist.R;
import com.aplus6.mybooklist.models.MBookList;
import com.aplus6.mybooklist.servieces.MBookListManager;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddListFragment extends Fragment {
	private Button cancel;
	private Button ok;
	private FragmentManager fm;
	private EditText listName;
	private MainActivity activity;
	@Override  
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)  
    {  
		View view  = inflater.inflate(R.layout.view_add_list, container, false);
		cancel = (Button)view.findViewById(R.id.cancel);
		ok = (Button)view.findViewById(R.id.ok);
		listName = (EditText) view.findViewById(R.id.list_name);
		fm = getFragmentManager();
		activity = (MainActivity)getActivity();
		cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fm.popBackStack();
			}		
		});
		ok.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String l_name = listName.getText().toString();
				Log.e("eeeeeeeeeeeee", l_name);
				if(l_name.equals("")){
					Toast toast=Toast.makeText(activity, "请输入一个书单名", Toast.LENGTH_LONG);
					toast.show();
				}else{
					MBookList bl = new MBookList();
					bl.setName(l_name);
					bl.setC_time(System.currentTimeMillis());
					bl.setM_time(System.currentTimeMillis());
					MBookListManager.addMBookList(bl);
					fm.popBackStack();
				}
				
			}		
		});
        return view;  
    }  
}
