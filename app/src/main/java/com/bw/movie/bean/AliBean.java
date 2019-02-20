package com.bw.movie.bean;

/**
 * author:author${车文飞}
 * data:2019/2/18
 */
public class AliBean {
    private String status;
    private String message;
    private String result;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AliBean{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
