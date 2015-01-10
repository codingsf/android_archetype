package com.join.android.app.common.activity;

import android.widget.TextView;
import com.BaseActivity;
import com.kingnet.android.app.mugo.R;
import com.join.android.app.common.pref.MyPref_;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-25
 * Time: 上午10:10
 */
@EActivity(R.layout.pref_activity_layout)
public class PrefActivity extends BaseActivity {

    @Pref
    MyPref_ myPref;
    @ViewById
    TextView msg;

    @AfterViews
    void afterViews() {
        myPref.name().put("after update name");
        msg.setText(myPref.name().get() + ";age=" + myPref.age().get());
    }
}
