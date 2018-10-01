package com.gabchak.web;

import java.util.Objects;

public class Requeast {

    private final String method;
    private final String uri;

    public Requeast(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public static Requeast of(String method, String uri) {
        return new Requeast(method, uri);
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Requeast)) return false;
        Requeast requeast = (Requeast) o;
        return Objects.equals(method, requeast.method) &&
                Objects.equals(uri, requeast.uri);
    }

    @Override
    public int hashCode() {

        return Objects.hash(method, uri);
    }
}
