package com.join.android.app.common.db;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

/**
 * Sample Android UI activity which displays a text window when it is run.
 */
public class HelloAndroid extends OrmLiteBaseActivity<DatabaseHelper> {

	private final String LOG_TAG = getClass().getSimpleName();

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(LOG_TAG, "creating " + getClass() + " at " + System.currentTimeMillis());
		TextView tv = new TextView(this);
		doSampleDatabaseStuff("onCreate", tv);
		setContentView(tv);
	}

	/**
	 * Do our sample database stuff.
	 */
	private void doSampleDatabaseStuff(String action, TextView tv) {
	}
}