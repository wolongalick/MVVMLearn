package com.alick.commonlibrary.base.bean;

/**
 * @author 崔兴旺
 * @package com.alick.commonlibrary.base.bean
 * @title:
 * @description:
 * @date 2019/4/11 23:53
 */
public class BaseResponse<Data> {
    private Data data;
    private String errorMsg;

    public BaseResponse(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public BaseResponse(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
