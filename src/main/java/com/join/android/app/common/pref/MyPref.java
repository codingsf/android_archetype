package com.join.android.app.common.pref;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-25
 * Time: 上午10:34
 */

@SharedPref
public interface MyPref {
    @DefaultString("John")
    String name();

    @DefaultInt(421)
    int age();

}
