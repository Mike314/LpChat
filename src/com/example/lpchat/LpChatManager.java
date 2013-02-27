package com.example.lpchat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;

public class LpChatManager
{
	static Activity parentActivity;
	
	public static void run(Activity activity)
	{
		parentActivity = activity;
		//Start chat activity
		parentActivity.startActivityForResult(new Intent(parentActivity, LpChatActivity.class), 0);
		//displayButton();
	}
	
    public static void displayButton() 
    {
        Button v = new Button(parentActivity);
        v.setText("Live Chat"); 
        
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
        wm.addView(v, params);
    }	
}