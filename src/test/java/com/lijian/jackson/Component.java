package com.lijian.jackson;

public class Component {

    String name;
    State state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}
