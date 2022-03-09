package com.spot.task.services.response;

import java.util.HashMap;
import java.util.Map;

public class Number {

    private Integer length;
    private Boolean luhn;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getLuhn() {
        return luhn;
    }

    public void setLuhn(Boolean luhn) {
        this.luhn = luhn;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}