package com.ssm.entity;

import java.util.*;

public class Message {
    private int code;
    private String message;
    private Map<String, Object> data = new HashMap<>();
    private Message(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Message message(int code, String message) { return new Message(code, message); }

    public static Message success() {
        return new Message(200, "success");
    }
    public static Message success(String message) {
        return new Message(200, message);
    }

    public static Message error() { return new Message(400, "fail"); }
    public static Message error(String message) { return new Message(400, message); }

    public Message put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Map<String, Object> getData() { return data; }
    public void setData(Map<String, Object> map) { this.data = data; }

    @Override
    public String toString() {
        return "Message { " + "code = " + code + ", message = '" + message + '\'' +
                " , data = " + data + '}';
    }
}
