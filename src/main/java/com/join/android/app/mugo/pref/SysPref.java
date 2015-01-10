package com.join.android.app.mugo.pref;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-25
 * Time: 上午10:34
 */

@SharedPref
public interface SysPref {

    @DefaultString("-1")
    String packageName();

    @DefaultBoolean(false)
    boolean active();

}
