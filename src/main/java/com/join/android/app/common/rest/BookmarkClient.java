package com.join.android.app.common.rest;

import android.accounts.NetworkErrorException;
import android.util.Log;
import com.join.android.app.common.rest.dto.Recommend;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * User: mawanjin@join-cn.com
 * Date: 14-2-21
 * Time: 下午3:01
 */
@Rest(rootUrl = "http://www.baidu.com", converters = MappingJacksonHttpMessageConverter.class, interceptors = HttpBasicAuthenticatorInterceptor.class, requestFactory = RPCRequestFactory.class)
//@Rest(rootUrl = "http://aia.fortune-net.cn/app/", converters = MyMappingJacksonHttpMessageConverter.class, interceptors = {HttpBasicAuthenticatorInterceptor.class,})
public interface BookmarkClient extends RestClientHeaders, RestClientErrorHandling {

    RestTemplate getRestTemplate();

    void setRestTemplate(RestTemplate restTemplate);

    @Get("/json")
    RPCResult<List<Recommend>> getAccounts();



//    /**
//     * 登录
//     *
//     * @param userId
//     * @param password
//     * @param companyId
//     * @return
//     */
//    @Get("login.jsp?userId={userId}&code={password}&branchCode={companyId}")
//    public LoginDto login(String userId, String password, String companyId);


}

class HttpBasicAuthenticatorInterceptor implements ClientHttpRequestInterceptor {
    public HttpBasicAuthenticatorInterceptor() {
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] data, ClientHttpRequestExecution execution) throws IOException {
        Log.d(BookmarkClient.class.getName(), "retrieve from" + request.getURI().toString());
        ClientHttpResponse response = execution.execute(request, data);
        return response;
    }
}

class RPCRequestFactory extends org.springframework.http.client.HttpComponentsClientHttpRequestFactory {
    public RPCRequestFactory() {
        setConnectTimeout(10000);
    }
}
