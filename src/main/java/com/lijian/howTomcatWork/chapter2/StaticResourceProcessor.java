package com.lijian.howTomcatWork.chapter2;

public class StaticResourceProcessor  {
    public void process(Request request, Response response) {
        response.sendStaticResource();

    }
}
