package com.jacksen.volley.encapsulate;

import java.util.List;

/**
 * @author jacksen
 */

public abstract class BaseBean {

    /**
     * operFlag : 1000
     * errorCode : 0
     * totalCount : 0
     * from : 0
     * to : 0
     * data : []
     */

    private String operFlag;
    private int errorCode;
    private int totalCount;
    private int from;
    private int to;
    private List<?> data;

    public String getOperFlag() {
        return operFlag;
    }

    public void setOperFlag(String operFlag) {
        this.operFlag = operFlag;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public abstract List<?> getData();

    public void setData(List<?> data) {
        this.data = data;
    }
}
