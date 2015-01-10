package com.join.android.app.mugo.rpc;

import android.util.Log;
import com.join.android.app.common.rest.MyMappingJacksonHttpMessageConverter;
import com.join.android.app.common.rest.RPCResult;
import com.join.android.app.common.rest.dto.Recommend;
import com.join.android.app.mugo.dto.AD;
import com.join.android.app.mugo.dto.APKUrl;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * Created by lala on 15/1/8.
 */
@Rest(rootUrl = "http://ad.mg3721.com", converters = MyMappingJacksonHttpMessageConverter.class, interceptors = HttpBasicAuthenticatorInterceptor.class, requestFactory = RPCRequestFactory.class)
public interface MURPCService extends RestClientHeaders, RestClientErrorHandling {

    RestTemplate getRestTemplate();

    void setRestTemplate(RestTemplate restTemplate);

    @Get("/?aid={id}&ac=apk")
    APKUrl getAPKUrl(String id);

    @Get("/?aid=WpwQHg&ac=mg")
    AD getAD();

}

class HttpBasicAuthenticatorInterceptor implements ClientHttpRequestInterceptor {
    public HttpBasicAuthenticatorInterceptor() {
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] data, ClientHttpRequestExecution execution) throws IOException {
        Log.d(MURPCService.class.getName(), "retrieve from" + request.getURI().toString());
        ClientHttpResponse response = execution.execute(request, data);
        return response;
    }
}

class RPCRequestFactory extends org.springframework.http.client.HttpComponentsClientHttpRequestFactory {
    public RPCRequestFactory() {
        setConnectTimeout(20000);
    }
}
