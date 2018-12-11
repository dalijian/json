package com.lijian.json.jackon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Service {


    ObjectMapper mapper = new ObjectMapper();

    Friend friend = new Friend("lijian", 25);

    String text = mapper.writeValueAsString(friend);

    public Service() throws JsonProcessingException {
    }
}
