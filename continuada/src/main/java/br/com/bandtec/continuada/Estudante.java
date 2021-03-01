package br.com.bandtec.continuada;

public class NaoBolsista extends Aluno{
    public NaoBolsista(String nome, int quantSemestre, double valorSemestre, int horaAulaDia) {
        super(nome, quantSemestre, valorSemestre, horaAulaDia);
    }

    @Override
    public double calcValorTotalCurso() {
        return 6*getValorMes()*getQuantSemestre();
    }

    @Override
    public int calcTotalHorasSemanais() {
        return getHoraAulaDia()*5;
    }

    public int getTotalAulaSemanal(){
        return calcTotalHorasSemanais();
    }

    public double getValorTotalCurso(){
        return calcValorTotalCurso();
    }
}
