package com.join.android.app.common.activity;

import android.app.Activity;
import android.widget.TextView;
import com.kingnet.android.app.mugo.R;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

@EActivity(R.layout.activity_with_extra)
public class ActivityWithExtra extends Activity {


	public static final String MY_STRING_EXTRA = "myStringExtra";
	public static final String MY_DATE_EXTRA = "myDateExtra";
	public static final String MY_INT_EXTRA = "myIntExtra";
	public static final String MY_List = "myList";

	@ViewById
    TextView extraTextView;

	@Extra(MY_STRING_EXTRA)
    String myMessage;

	@Extra(MY_DATE_EXTRA)
    Date myDate;

    @Extra(MY_List)
    String myList;

	@Extra("unboundExtra")
    String unboundExtra = "unboundExtraDefaultValue";

	/**
	 * The logs will output a classcast exception, but the program flow won't be interrupted
	 */
	@Extra(MY_INT_EXTRA)
    String classCastExceptionExtra = "classCastExceptionExtraDefaultValue";

	@AfterViews
	protected void init() {
		extraTextView.setText(myList+myMessage + " " + myDate + " " + unboundExtra + " " + classCastExceptionExtra);
	}

}
