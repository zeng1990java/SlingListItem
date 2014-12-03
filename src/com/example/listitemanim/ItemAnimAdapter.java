package com.example.listitemanim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAnimAdapter extends BaseAdapter {

	private Context mContext;
	
	public ItemAnimAdapter(Context context){
		mContext = context;
	}
	
	@Override
	public int getCount() {
		return 10;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);
		}
		
		TextView topText = (TextView) convertView.findViewById(R.id.top);
		topText.setText("Top_"+position);
		return convertView;
	}

}
