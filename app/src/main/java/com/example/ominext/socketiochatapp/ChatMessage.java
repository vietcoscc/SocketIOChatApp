package com.example.ominext.socketiochatapp;

/**
 * Created by Ominext on 12/7/2017.
 */

public class ChatMessage {
    private String fromName;
    private String toName;
    private String toClientID;
    private String message;

    public ChatMessage(String fromName, String toName, String toClientID, String message) {
        this.fromName = fromName;
        this.toName = toName;
        this.toClientID = toClientID;
        this.message = message;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToClientID() {
        return toClientID;
    }

    public void setToClientID(String toClientID) {
        this.toClientID = toClientID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
