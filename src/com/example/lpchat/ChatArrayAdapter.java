package com.example.lpchat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChatArrayAdapter extends ArrayAdapter<String>
{
	private Context context;
	public List<String> messages;
	
	public ChatArrayAdapter(Context context, List<String> values) 
	{
		super(context, R.layout.row, values);
		this.context = context;
		messages = values; 
	}
	
	//set each listview item to the corresponding message in chat
	@Override
	public View getView(int i, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		String h = messages.get(i);
		View rowView = inflater.inflate(R.layout.row, parent, false);
		
		TextView textView1 = (TextView) rowView.findViewById(R.id.details);
		textView1.setText(h);

		return rowView;
	}
}