package com.lijian.mytomcat;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<>();
    static {
        servletMappingList.add(new ServletMapping("findGirl", "/girl", "com.lijian.mytomcat.FindGirlServlet"));

    }
}
