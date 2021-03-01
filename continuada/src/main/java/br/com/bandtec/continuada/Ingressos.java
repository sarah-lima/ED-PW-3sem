package br.com.bandtec.continuada;

public abstract class Ingressos implements Vendavel {
    private int id;
    private double valor;

    public Ingressos(int id, double valor) {
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public abstract double calcIngresso();
}