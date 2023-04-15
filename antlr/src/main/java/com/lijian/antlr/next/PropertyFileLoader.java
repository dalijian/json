package com.lijian.antlr.next;

import com.lijian.antlr.next.auto.PropertyFileParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

import java.util.LinkedHashMap;
import java.util.Map;

public class PropertyFileLoader extends PropertyFileParser {

    Map<String,String> props = new LinkedHashMap<>();
    public PropertyFileLoader(TokenStream input) {
        super(input);
    }
    void defineProperty(Token name,Token value){
        props.put(name.getText(), value.getText());
    }

}
