package com.lijian.design_pattern.structure.decorator;

public class Decorator implements Component {
    private Component component;
    
    public Decorator(Component component) {
        this.component = component;
    }
    
    @Override
    public void sampleOpreation() {
        //委派给构件
        component.sampleOpreation();
    }

}