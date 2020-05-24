package com.jtw.homestay.domain;

public class ResultInfo {
    private boolean flag; //后端返回结果正常为true,异常返回false
    private Object data;  //后端返回数据结果对象
    private String errorMsg;  //发生异常的错误消息
    //无参构造方法
    public ResultInfo() {
    }

    /**
     * 有参构造方法
     * @param flag
     * @param data
     */
    public ResultInfo(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    /**
     * 有参构造方法
     * @param data
     * @param errorMsg
     */
    public ResultInfo(Object data, String errorMsg) {
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "flag=" + flag +
                ", data=" + data +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
