package com.join.android.app.common.fragment;

import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.kingnet.android.app.mugo.R;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-25
 * Time: 上午11:19
 */
@EFragment(R.layout.detail_fragment_layout)
public class OtherDetailFragment extends Fragment {

    @ViewById
    TextView txt;

    @AfterViews
    void afterviews() {
        txt.setText("i'm other detail ");
    }
}
