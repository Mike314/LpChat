package com.example.lpchat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class LpChatManager
{
	static Activity parentActivity;
	static public List<String> messages = new ArrayList<String>();
	
	public static void run(Activity activity)
	{
		parentActivity = activity;
		//Start chat activity
		parentActivity.startActivityForResult(new Intent(parentActivity, LpChatActivity.class), 0);
		//displayButton();
	}
	
    public static void displayLiveChatButton() 
    {
        Button liveChatButton = new Button(parentActivity);
        liveChatButton.setText("Live Chat"); 
		liveChatButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//Toast.makeText(parentActivity, "onClick", Toast.LENGTH_LONG).show();
				parentActivity.startActivityForResult(new Intent(parentActivity, LpChatActivity.class), 0);
			}
		});
        
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                //? WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,
                WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG,
                0,
//              WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                      | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.OPAQUE);
        params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        params.setTitle("Live Chat");
        WindowManager wm = (WindowManager) parentActivity.getSystemService(parentActivity.WINDOW_SERVICE);
        wm.addView(liveChatButton, params);
    }	
}