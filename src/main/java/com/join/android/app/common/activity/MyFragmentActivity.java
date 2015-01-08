package com.join.android.app.common.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import com.join.android.app.mgps.R;
import com.join.android.app.common.fragment.DetailFragment_;
import com.join.android.app.common.fragment.OtherDetailFragment_;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-25
 * Time: 上午11:03
 */
@EActivity(R.layout.my_fragment_activity_layout)
public class MyFragmentActivity extends FragmentActivity {

    @ViewById
    Button switchBtn;

    DetailFragment_ detailFragment;

    OtherDetailFragment_ otherDetailFragment;

    FragmentManager fragmentManager;

    @AfterViews
    void afterViews() {
        fragmentManager = getSupportFragmentManager();
        detailFragment = new DetailFragment_();
        otherDetailFragment = new OtherDetailFragment_();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.detailFragment, otherDetailFragment);
        transaction.add(R.id.detailFragment, detailFragment);
        transaction.show(detailFragment);
        transaction.commit();

    }
    boolean w;

    @Click
    void switchBtn() {

        if(w)
            switchFragment(detailFragment,otherDetailFragment);
        else
            switchFragment(otherDetailFragment,detailFragment);
        w = !w;
    }

    void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(from).show(to).commit();
    }
}
