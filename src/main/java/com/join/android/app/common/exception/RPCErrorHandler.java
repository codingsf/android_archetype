package com.join.android.app.common.exception;

import android.util.Log;
import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;

/**
 * Created by lala on 14/12/22.
 */
@EBean
public class RPCErrorHandler implements RestErrorHandler {
    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        Log.e("network error",e.getMessage());
    }
}
