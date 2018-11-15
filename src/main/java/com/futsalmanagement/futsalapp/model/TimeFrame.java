package com.futsalmanagement.futsalapp.model;

public class TimeFrame {
    private String start_time;
    private String end_time;

    public TimeFrame(String start_time, String end_time) {
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public TimeFrame() {
    }


    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
