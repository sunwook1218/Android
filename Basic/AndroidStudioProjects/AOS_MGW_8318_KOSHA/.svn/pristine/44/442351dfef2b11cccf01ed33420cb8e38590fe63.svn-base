package com.hs.mobile.gw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.text.TextUtils;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.util.Debug;

public class PopupActivity extends Activity
{
	boolean cancelable = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
        	builder.setTitle(title);

        
        String text = getIntent().getStringExtra("message");
        if (!TextUtils.isEmpty(text))
        	builder.setMessage(text);

        Debug.trace("msg = " + text);
        
		String ok_text = getIntent().getStringExtra("ok_text");
		if (!TextUtils.isEmpty(ok_text)) {
			builder.setPositiveButton(ok_text, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					setResult(RESULT_OK);
					finish();
				}
			});
		}

		String cancel_text = getIntent().getStringExtra("cancel_text");
		if (!TextUtils.isEmpty(cancel_text)) {
			builder.setNegativeButton(cancel_text, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					setResult(RESULT_CANCELED);
	                finish();
				}
			});
		}
        
        
		cancelable = getIntent().getBooleanExtra("cancelable", false);
		if (cancelable)
			builder.setCancelable(cancelable);
		
		AlertDialog dlg = builder.create();
		dlg.setCanceledOnTouchOutside(false);
		dlg.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				// TODO Auto-generated method stub
				Debug.trace("");
				finish();
			}
		});
		dlg.show();
    }
    
    
    @Override
	protected void onResume() {
		ViewModel.Log("PopAct blue 해제", "bluetooth");
		// TODO Auto-generated method stub
		super.onResume();
		Debug.trace("");
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Debug.trace("");
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Debug.trace("");
	}


	@Override
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
    	Debug.trace("");
            finish();
    }
    
    @Override
    public void finish()
    {
    	Debug.trace("");
        // TODO Auto-generated method stub
        super.finish();
    }
}