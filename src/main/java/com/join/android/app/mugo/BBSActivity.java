package com.join.android.app.mugo;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.BaseActivity;
import com.join.android.app.common.utils.MetaUtils;
import com.join.android.app.common.view.ProgressWebView;
import com.join.android.app.mugo.dialog.ADDialog;
import com.join.android.app.mugo.dto.AD;
import com.join.android.app.mugo.rpc.MURPCService;
import com.kingnet.android.app.mugo.R;
import org.androidannotations.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by lala on 15/1/9.
 */

@EActivity(R.layout.bbs_activity)
public class BBSActivity extends BaseActivity {
    String url = "http://adapi.mg3721.com/account/forum?appid=";

    @ViewById
    ProgressWebView webView;

    @RestService
    MURPCService murpcService;

    @AfterViews
    void afterViews() {

        url += MetaUtils.getAppID(this);
        initWebView();
        webView.loadUrl(url);

        //加载广告
        ad();
    }


    @Click
    void previousClicked(){
        webView.goBack();
    }

    @Click
    void forwardClicked(){
        webView.goForward();
    }

    @Click
    void refreshClicked(){
        webView.reload();
    }

    @Click
    void homeClicked(){
        webView.loadUrl(url);
    }

    private void initWebView() {

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);


        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
    }

    @Background
    void ad(){
        AD ad = murpcService.getAD(MetaUtils.getAd(this));
        showAD(ad);
    }

    @UiThread
    void showAD(final AD ad){
        if(ad!=null&& StringUtils.isNotEmpty(ad.getImgUrl())){
            ADDialog dialog = new ADDialog(this,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar,ad);
            dialog.show();
        }
    }
}
