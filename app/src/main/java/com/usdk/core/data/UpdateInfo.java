package com.usdk.core.data;

/**
 * Created by huhl on 2018/2/26 0026.
 */

public class UpdateInfo {


    /**
     *  返回码
     */
    private String respCode ;

    /**
     * 返回错误信息
     */
    private String respErrMsg ;

    /**
     *  sdk 更新url
     */
    private String updateSDKURL ;

    /**
     * sdk 更新版本号
     */
    private String updateSDKVersion ;

    /**
     * sdk 更新名称
     */
    private String updateSDKName;
    /**
     * cash 更新版本号
     */
    private String updateCashURL ;
    /**
     * cash 更新版本号
     */
    private String  updateCashVersion ;

    /**
     * cash 更新名称
     */
    private String updateCashName;

    public String getUpdateSDKURL() {
        return updateSDKURL;
    }

    public UpdateInfo setUpdateSDKURL(String updateSDKURL) {
        this.updateSDKURL = updateSDKURL;
        return this;
    }

    public String getUpdateSDKVersion() {
        return updateSDKVersion;
    }

    public UpdateInfo setUpdateSDKVersion(String updateSDKVersion) {
        this.updateSDKVersion = updateSDKVersion;
        return this;
    }

    public String getUpdateCashURL() {
        return updateCashURL;
    }

    public UpdateInfo setUpdateCashURL(String updateCashURL) {
        this.updateCashURL = updateCashURL;
        return this;
    }

    public String getUpdateCashVersion() {
        return updateCashVersion;
    }

    public UpdateInfo setUpdateCashVersion(String updateCashVersion) {
        this.updateCashVersion = updateCashVersion;
        return this;
    }

    public String getUpdateSDKName() {
        return updateSDKName;
    }

    public UpdateInfo setUpdateSDKName(String updateSDKName) {
        this.updateSDKName = updateSDKName;
        return this;
    }

    public String getUpdateCashName() {
        return updateCashName;
    }

    public UpdateInfo setUpdateCashName(String updateCashName) {
        this.updateCashName = updateCashName;
        return this;
    }

    public String getRespCode() {
        return respCode;
    }

    public UpdateInfo setRespCode(String respCode) {
        this.respCode = respCode;
        return this;
    }

    public String getRespErrMsg() {
        return respErrMsg;
    }

    public UpdateInfo setRespErrMsg(String respErrMsg) {
        this.respErrMsg = respErrMsg;
        return this;
    }
}
