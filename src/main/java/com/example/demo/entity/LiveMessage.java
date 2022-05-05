package com.example.demo.entity;

public class LiveMessage {

    public static final byte TYPE_HEART = 1;
    public static final byte TYPE_MESSAGE = 2;

    private byte type;
    private int length;
    private String content;

    public LiveMessage(byte type, int length, String content) {
        this.type = type;
        this.length = length;
        this.content = content;
    }

    public LiveMessage() {
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
