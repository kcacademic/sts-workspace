package com.sapient.learning.data;

public class Message {

    public String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Message{" +
                "key='" + key + '\'' +
                '}';
    }
}
