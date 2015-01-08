package com.join.android.app.common.rest;

/**
 * User: mawanjin@join-cn.com
 * Date: 13-11-27
 * Time: 上午11:04
 */
public class RPCResult<E> {
    //真正的返回结果看这个字段
    private boolean breturn;
    private boolean success;
    private String errorinfo;
    //结果码
    private int ireturn;
    private E object;

    public boolean isBreturn() {
        return breturn;
    }

    public void setBreturn(boolean breturn) {
        this.breturn = breturn;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorinfo() {
        return errorinfo;
    }

    public void setErrorinfo(String errorinfo) {
        this.errorinfo = errorinfo;
    }

    public int getIreturn() {
        return ireturn;
    }

    public void setIreturn(int ireturn) {
        this.ireturn = ireturn;
    }

    public E getObject() {
        return object;
    }

    public void setObject(E object) {
        this.object = object;
    }

    public static RPCResult getNoNetworkObj() {
        RPCResult rpcResult = new RPCResult();
        rpcResult.setBreturn(false);
        rpcResult.setErrorinfo("网络异常");
        return rpcResult;
    }

    public static RPCResult getLoadFailed() {
        RPCResult rpcResult = new RPCResult();
        rpcResult.setBreturn(false);
        rpcResult.setErrorinfo("加载失败");
        return rpcResult;
    }
}
