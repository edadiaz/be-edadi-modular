package com.az.edadi.model.constant;

public enum PageTab {
    POSTS("page_post"),
    SPECIALITIES("page_specialities"),
    PEOPLE("page_people"),
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
