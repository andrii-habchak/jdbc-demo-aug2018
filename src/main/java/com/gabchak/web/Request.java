package com.gabchak.web;

import javax.servlet.http.Cookie;
import java.util.Map;
import java.util.Objects;

public class Request {

    private final String method;
    private final String uri;
    private final Map<String, String[]> params;
    private final Cookie[] cookies;

    public Request(String method, String uri, Map<String, String[]> params, Cookie[] cookies) {
        this.method = method;
        this.uri = uri;
        this.params = params;
        this.cookies = cookies;
    }



    public static Request of(String method, String uri) {
        return new Request(method, uri, null, null);
    }

    public static Request of(String method, String uri, Map<String, String[]> params)  {
        return new Request(method, uri, params, null);
    }

    public static Request of(String method, String uri, Map<String, String[]> params, Cookie[] cookies)  {
        return new Request(method, uri, params, cookies);
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

    public Cookie[] getCookies() {
        return cookies;
    }
}
