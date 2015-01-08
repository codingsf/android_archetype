package com.join.android.app.common.fragment;

import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.join.android.app.mgps.R;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-25
 * Time: 上午10:58
 */
@EFragment(R.layout.detail_fragment_layout)
public class DetailFragment extends Fragment {

    @ViewById
    TextView txt;

    @AfterViews
    void afterViews(){
        txt.setText(txt.getText().toString()+" :)");
    }
}
