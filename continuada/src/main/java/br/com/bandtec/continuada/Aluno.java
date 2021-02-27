package br.com.bandtec.continuada;

public abstract class Aluno implements Cursavel{
    private String nome;
    private int quantSemestre;
    private double valorMes;
    private int horaAulaDia;

    public Aluno(String nome, int quantSemestre, double valorMes, int horaAulaDia) {
        this.nome = nome;
        this.quantSemestre = quantSemestre;
        this.valorMes = valorMes;
        this.horaAulaDia = horaAulaDia;
    }

    public abstract double calcValorTotalCurso();

    public String getNome() {
        return nome;
    }

    public int getQuantSemestre() {
        return quantSemestre;
    }

    public double getValorMes() {
        return valorMes;
    }

    public int getHoraAulaDia() {
        return horaAulaDia;
    }
}