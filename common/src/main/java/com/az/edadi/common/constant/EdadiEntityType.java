package com.az.edadi.common.constant;


public enum EdadiEntityType {
    USER("user"),
    ROOMMATE("roommate"),
    POST("post"),
    UNIVERSITY("university"),
    SPECIALITY("speciality"),
    CONVERSATION("conversation"),
    TOPIC("topic"),
    FILE("file");

    private final String type;


    EdadiEntityType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
