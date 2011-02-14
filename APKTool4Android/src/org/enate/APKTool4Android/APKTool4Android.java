package org.enate.APKTool4Android;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import brut.androlib.AndrolibException;

public class APKTool4Android extends Activity {
    
	protected static Context c;
	protected static TextView btn_banner;
	protected static EditText txt_usrApkFile;
	protected static Button btn_decompile;
	protected static Button btn_recompile;
	protected static ListView list;
	protected static int aTaskMethod = 0;
    private ProgressDialog pd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);
        
        c = getApplicationContext();
        
        list = (ListView)findViewById(R.id.longcat);
        list.setFastScrollEnabled(true);
        btn_banner = (TextView)findViewById(R.id.txt_hello);
        txt_usrApkFile = (EditText)findViewById(R.id.txt_usrApkFile);
        btn_decompile = (Button)findViewById(R.id.btn_decompile);
        btn_recompile = (Button)findViewById(R.id.btn_recompile);
        
        btn_decompile.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		aTaskMethod=1;
        		new aTask().execute();
			}
        });
        btn_recompile.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View v) {
        		aTaskMethod=2;
        		new aTask().execute();
			}
        });       		
    }
    
    public class aTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			switch(aTaskMethod){
			case 1:
				btn_banner.setText("Decompiling now");
				pd = ProgressDialog.show(APKTool4Android.this, "APKTool4Android", "Decompiling...",true,true);
				break;
			case 2:
				btn_banner.setText("Recompiling now");
				pd = ProgressDialog.show(APKTool4Android.this, "APKTool4Android", "Recompiling...",true,true);
				break;
			}
		}
		@Override
		protected Void doInBackground(Void... params) {
			switch(aTaskMethod){
			case 1:
				try {brut.apktool.Main.main("d",txt_usrApkFile.getText().toString(), "/sdcard/apktool/testoutput");} 
				catch (AndrolibException e) {e.printStackTrace();}catch (IOException e) {e.printStackTrace();}catch (InterruptedException e) {e.printStackTrace();}
				return null;
			case 2:
        		try {brut.apktool.Main.main("b", "/sdcard/apktool/testoutput", "/sdcard/apktool/testRECOMPILED.apk");} 
        		catch (AndrolibException e) {e.printStackTrace();}catch (IOException e) {e.printStackTrace();}catch (InterruptedException e) {e.printStackTrace();}
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			try{pd.dismiss();}catch(Exception e){Log.d("",e+"");}			
			switch(aTaskMethod){
			case 1:btn_banner.setText("Finished decompiling");break;
			case 2:btn_banner.setText("Finished recompiling");break;
			}
		}
	}

    
    
}