package br.com.bandtec.continuada;

public class Professor implements Cursavel{
    private String nome;
    private int horaAulaDia;
    private double valorHora;
    private int quantAulas;


    public Professor(String nome, int horaAulaDia, double valorHora, int quantAulas) {
        this.nome = nome;
        this.horaAulaDia = horaAulaDia;
        this.valorHora = valorHora;
        this.quantAulas = quantAulas;
    }

    public double getCalcTotalSalario(){
        return valorHora*calcTotalHorasSemanais()*4;
    }


    @Override
    public int calcTotalHorasSemanais() {
        return quantAulas * horaAulaDia;
    }

    public String getNome() {
        return nome;
    }

    public double getValorHora() {
        return valorHora;
    }

    public int getHoraAulaDia() {
        return horaAulaDia;
    }

    public int getQuantAulas() {
        return quantAulas;
    }

    public double getTotalAulaSemanal(){
        return calcTotalHorasSemanais();
    }
}