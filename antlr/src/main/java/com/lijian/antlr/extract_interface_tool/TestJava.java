package com.lijian.antlr.extract_interface_tool;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.StringEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ren7wei
 */
@Slf4j
public class TestJava {

    /**
     * 发送消息到交换
     *
     * @param messageDefineId 消息类型标识
     * @param obj             实体信息
     * @return
     */
    public void sendInfo(String messageDefineId, Object obj) {
        try {


            Map<String, String> bodys = new HashMap<String, String>(5);
            Map<String, String> headers = new HashMap<String, String>(5);
            Map<String, Object> content = new HashMap<String, Object>(5);

            bodys.put("messageDefineId", messageDefineId);

            Map<String, Object> options = new HashMap<String, Object>(5);
            options.put("method", "PUT");

            log.info("dec push message response:{}", "");
        } catch (Exception e) {
            log.error("send message to dec exception.", e);
        }
    }

}