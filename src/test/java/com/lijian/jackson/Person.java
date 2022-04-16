package com.lijian.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = PersonDeserializer.class)
public class Person {
}
