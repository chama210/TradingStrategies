package com.trading.data;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RequestParameters {
    ConcurrentHashMap<String, String> parameters;

    public RequestParameters() {
        this.parameters = new ConcurrentHashMap<>();
    }

    public RequestParameters addParameter(String key, String value) {
        Objects.requireNonNull(key, "key is null");
        Objects.requireNonNull(value, "value associate with key %s is null".formatted(key));
        parameters.put(key, value);
        return this;
    }

    public String join() {
//        return parameters
//                .entrySet()
//                .stream()
//                .map("%s=%s"::formatted)
//                .collect(Collectors.joining("&"));
        return parameters.reduce(4, "%s=%s"::formatted, "%s&%s"::formatted);
    }

}
