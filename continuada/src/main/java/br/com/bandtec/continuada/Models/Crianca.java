package br.com.bandtec.continuada.Models;

import br.com.bandtec.continuada.Ingressos;

public class Crianca extends Ingressos {
    private int quantTotalCrianca;
    public Crianca(int id, double valor, int quantTotalCrianca) {
        super(id, valor);
        this.quantTotalCrianca = quantTotalCrianca;
    }

    @Override
    public double calcIngresso() {
        return getValor()/3;
    }

    public String getIngresso() {
        return String.format("%.2f",calcIngresso());
    }

    @Override
    public double calcTotalLucro() {
        return calcIngresso()*quantTotalCrianca;
    }

    public String getTotalLucro(){
        return String.format("%.2f",calcTotalLucro());
    }

    public int getQuantTotalCrianca() {
        return quantTotalCrianca;
    }
}
