package com.example.lpchat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LpChatActivity extends Activity
{
	private ListView mainListView;
	private List<String> messages = new ArrayList<String>();
	Boolean chatStarted = false;
	public static final String prefsName = "LpChatPrefsFile";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lp_chat);

		if (LpChatManager.messages.size() > 0)
		{
			messages = LpChatManager.messages;
			chatStarted = true;
		}
		else
			//initial help text:
			messages.add("Send a message to our live service reps for immediate help");
		
		mainListView = (ListView) findViewById(R.id.mainListView);

		Button sendButton = (Button) findViewById(R.id.sendButton);
		//When the Send button is pressed, copy message text from input area to a list and clear input area
		sendButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EditText msgEditText = (EditText) findViewById(R.id.messageText);
				
				String message = msgEditText.getText().toString();
				if (message.length() == 0)
					return;
				
				if (!chatStarted)
				{
					chatStarted = true;
					messages.clear();				//remove the initial help text
				}
				
				messages.add("Me: " + message);
				msgEditText.setText("");			//clear input area
				refreshScreen();
			}
		});

		Button hideButton = (Button) findViewById(R.id.hideButton);
		//When the Hide button is pressed, finish activity
		hideButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (chatStarted)
				{
					LpChatManager.messages = messages;
					LpChatManager.displayLiveChatButton();
				}
				
				finish();
			}
		});

		EditText msgEditText = (EditText) findViewById(R.id.messageText);
		msgEditText.requestFocus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lp_chat, menu);
		return true;
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		refreshScreen();
	}

	public void refreshScreen()
	{
		//refresh the listview items with message list
		ChatArrayAdapter listAdapter = new ChatArrayAdapter(this, messages);
		mainListView.setAdapter(listAdapter);
	}

	void writenumMessages(int a)
	{
		SharedPreferences settings = getSharedPreferences(prefsName, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("numMessages", a);

		editor.commit();
	}

	int readnumMessages()
	{
		SharedPreferences settings = getSharedPreferences(prefsName, 0);
		return settings.getInt("numMessages", 0);
	}

	public void writeMessage(int i, String a)
	{
		SharedPreferences settings = getSharedPreferences(prefsName, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("message" + i, a);

		editor.commit();
	}

	public String readMessage(int i)
	{
		SharedPreferences settings = getSharedPreferences(prefsName, 0);
		return settings.getString("message" + i, "");
	}
}