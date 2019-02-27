package com.cyk;

public class Grammar {

    private String Key;
    private String Value;

    public Grammar(String _key, String _value){
        this.Key = _key;
        this.Value = _value;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
