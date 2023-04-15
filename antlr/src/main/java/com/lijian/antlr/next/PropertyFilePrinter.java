package com.lijian.antlr.next;

import com.lijian.antlr.next.auto.PropertyFileParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

public class PropertyFilePrinter extends PropertyFileParser {
    public PropertyFilePrinter(TokenStream input) {
        super(input);
    }

    void defineProperty(Token name, Token value) {
        System.out.println(name.getText() + "=" + value.getText());
    }
}
