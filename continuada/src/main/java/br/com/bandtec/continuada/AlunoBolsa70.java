package br.com.bandtec.continuada;

public class AlunoBolsa70 implements BolsaEstudos{
    private Double valorBolsaTotal;

    @Override
    public Double calcBolsa() {
        return valorBolsaTotal*0.7;
    }
}
