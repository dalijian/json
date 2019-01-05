package com.lijian.page;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class PageData extends HashMap implements Map {
    HttpServletRequest request;

    public PageData() {

    }

    public PageData(HttpServletRequest request) {
        this.request =request;
        Map properties = request.getParameterMap();

    }
}
