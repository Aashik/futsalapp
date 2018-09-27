package com.futsalmanagement.futsalapp.model;

public class GlobalResponse<T> {

    private Status status;
    private String messsage;
    private T data;

    public GlobalResponse() {
    }

    public GlobalResponse(Status status, String messsage, T data) {
        this.status = status;
        this.messsage = messsage;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
