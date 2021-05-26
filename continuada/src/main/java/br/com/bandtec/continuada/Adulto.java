package br.com.bandtec.continuada;



public class Adulto extends Ingressos {
    private int quantTotalAdultos;
    public Adulto(int id, double valor, int quantTotalAdultos) {
        super(id, valor);
        this.quantTotalAdultos = quantTotalAdultos;
    }

    @Override
    public double calcIngresso() {
        return (getValor()*0.1)+getValor();
    }

    public double getIngresso() {
        return calcIngresso();
    }

    @Override
    public double calcTotalLucro() {
        return calcIngresso()*quantTotalAdultos;
    }


    public String getTotalLucro(){
        return String.format("%.2f",calcTotalLucro());
    }

    public int getQuantTotalAdultos() {
        return quantTotalAdultos;
    }
}
