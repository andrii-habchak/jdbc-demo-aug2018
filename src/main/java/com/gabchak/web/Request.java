package com.gabchak.web;

import java.util.Map;
import java.util.Objects;

public class Request {

    private final String method;
    private final String uri;
    private final Map<String, String[]> params;


    public Request(String method, String uri, Map<String, String[]> params) {
        this.method = method;
        this.uri = uri;
        this.params = params;
    }

    public static Request of(String method, String uri) {
        return new Request(method, uri, null);
    }

    public static Request of(String method, String uri, Map<String, String[]> params)  {
        return new Request(method, uri, params);
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getParamByName(String name) {
        return params.get(name)[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return Objects.equals(method, request.method) &&
                Objects.equals(uri, request.uri);
    }

    @Override
    public int hashCode() {

        return Objects.hash(method, uri);
    }

}
