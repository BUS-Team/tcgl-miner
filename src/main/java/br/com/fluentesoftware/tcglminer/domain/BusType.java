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

    public void setBuscarlinha(String buscarlinha) {
        this.buscarlinha = buscarlinha;
    }

    public Float getRnd() {
        return rnd;
    }

    public void setRnd(Float rnd) {
        this.rnd = rnd;
    }
}
