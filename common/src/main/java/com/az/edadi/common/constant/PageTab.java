package com.az.edadi.common.constant;

public enum PageTab {
    POSTS("page_post"),
    SPECIALITIES("page_specialities"),
    STUDENTS("page_students"),
    ABOUT("page_about"),
    UNIVERSITY("page_universities");

    private final String pageName;


    PageTab(String pageName) {
        this.pageName = pageName;
    }
    public String getType() {
        return pageName;
    }
}
