package org.example.lambdas;

public class StringToLengthTransformer implements Transformer<String, Integer> {

    @Override
    public Integer transform(String string) {
        return string.length();
    }
}
