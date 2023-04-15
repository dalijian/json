package com.lijian.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum State {
     VALID(1,"success"),INVALID(0, "fail");
    Integer type ;
    String message;
    State(int i, String success) {
        this.type=i;
        this.message=success;
    }

    /**
     * 使用 枚举
     * @param value
     * @return
     */
    @JsonCreator
    public static State get(int value) {
        for (State state : State.values()) {
            if (state.type == value) {
                return state;
            }
        }
        return null;
    }

}
