package br.com.fluentesoftware.tcglminer.domain;

public class Route {
    private String code;
    private String name;

    public Route(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
