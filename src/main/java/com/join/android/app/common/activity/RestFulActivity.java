package com.join.android.app.common.activity;

import com.BaseActivity;
import com.join.android.app.common.exception.RPCErrorHandler;
import com.join.android.app.common.manager.DialogManager;
import com.join.android.app.common.utils.NetWorkUtils;
import com.join.android.app.common.utils.SystemInfoUtils;
import com.kingnet.android.app.mugo.R;
import com.join.android.app.common.rest.BookmarkClient;
import com.join.android.app.common.rest.RPCResult;
import com.join.android.app.common.rest.dto.Recommend;
import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.StringRes;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 下午4:23
 */
@EActivity(R.layout.restful_activity)
public class RestFulActivity extends BaseActivity {
    @RestService
    BookmarkClient bookmarkClient;
    @Bean
    RPCErrorHandler rpcErrorHandler;
    @StringRes(resName = "net_excption")
    String netExcption;
    @StringRes(resName = "connect_server_excption")
    String netConnectExcption;

    @AfterViews
    void afterViews() {
//        bookmarkClient.setRestErrorHandler(rpcErrorHandler);
        recommendThread();
        showLoading();
    }

    @Background
    void recommendThread() {
        if (NetWorkUtils.isNetworkConnected(this)) {
            try {
                RPCResult<List<Recommend>> recommendRPCResult = bookmarkClient.getAccounts();
                updateUI(recommendRPCResult);
            } catch (RestClientException e) {
                e.printStackTrace();
                serverConnectionException();
            }
        }else{
            netWorkException();
        }
    }

    @UiThread
    void updateUI(RPCResult<List<Recommend>> result) {
        //do something
        dismissLoading();
    }

    @UiThread
    void serverConnectionException(){
        DialogManager.getInstance().makeText(this,netConnectExcption,DialogManager.DIALOG_TYPE_WARRING);
        dismissLoading();
    }

    @UiThread
    void netWorkException(){
        DialogManager.getInstance().makeText(this,netExcption,DialogManager.DIALOG_TYPE_WARRING);
        dismissLoading();
    }
}
