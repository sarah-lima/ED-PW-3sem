package br.com.bandtec.continuada;

import br.com.bandtec.continuada.Ingressos;


public class Estudante extends Ingressos {
    private int quantTotalEstudantes;

    public Estudante(int id, double valor, int quantTotalEstudantes) {
        super(id, valor);
        this.quantTotalEstudantes = quantTotalEstudantes;
    }

    @Override
    public double calcIngresso() {
        return getValor()/2;
    }

    public double getIngresso() {
        return calcIngresso();
    }

    @Override
    public double calcTotalLucro() {
        return quantTotalEstudantes*calcIngresso();
    }


    public String getTotalLucro(){
        return String.format("%.2f",calcTotalLucro());
    }

    public int getQuantTotalEstudantes() {
        return quantTotalEstudantes;
    }
}
