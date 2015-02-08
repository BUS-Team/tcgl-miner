package br.com.fluentesoftware.tcglminer.domain;

public class BusType {
    private String buscarlinha;
    private Float rnd;

    public BusType(String buscarlinha, Float rnd) {
        this.buscarlinha = buscarlinha;
        this.rnd = rnd;
    }

    public String getBuscarlinha() {
        return buscarlinha;
    }

    public Float getRnd() {
        return rnd;
    }
}
