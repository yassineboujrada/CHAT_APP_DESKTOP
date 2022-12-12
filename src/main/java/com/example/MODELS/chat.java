package com.example.MODELS;

import java.io.Serializable;
import java.sql.Date;

public class chat implements Serializable{
    private String sender_id, reciver_id, message;
    private Date date_send;

    public chat(String sender_id, String reciver_id, String message, Date date_send) {
        this.sender_id = sender_id;
        this.reciver_id = reciver_id;
        this.message = message;
        this.date_send = date_send;
    }

    public String getSender_id() {
        return sender_id;
    }

    public String getReciver_id() {
        return reciver_id;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate_send() {
        return date_send;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public void setReciver_id(String reciver_id) {
        this.reciver_id = reciver_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate_send(Date date_send) {
        this.date_send = date_send;
    }

    // toString method
    @Override
    public String toString() {
        return "chat [date_send=" + date_send + ", message=" + message + ", reciver_id=" + reciver_id + ", sender_id="
                + sender_id + "]";
    }

}
