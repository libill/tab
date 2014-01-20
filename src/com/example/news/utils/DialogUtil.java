package com.example.news.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

import com.example.news.R;

public class DialogUtil {
	
	public static void showExitDialog(Context context){
        Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(R.string.text_logout);
        dialog.setMessage(R.string.text_logout_app);
        dialog.setPositiveButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int which) {
            	 
             }
        });
        dialog.setNeutralButton(R.string.text_confirm, new DialogInterface.OnClickListener() { 
             public void onClick(DialogInterface dialog, int which) {
         		System.exit(0);
             }
        });
        dialog.create();
        dialog.show();
	}
}
