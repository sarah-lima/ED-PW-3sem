package br.com.bandtec.continuada;

public class Bolsista extends Aluno{
    private double descontoBolsa;
    private int quantAulasExtraCurricularSemanais;

    public Bolsista(String nome, int quantSemestre, double valorSemestre, int horaAulaDia, double descontoBolsa,
                    int quantAulasExtraCurricularSemanais) {
        super(nome, quantSemestre, valorSemestre, horaAulaDia);
        this.descontoBolsa = descontoBolsa;
        this.quantAulasExtraCurricularSemanais = quantAulasExtraCurricularSemanais;
    }

    @Override
    public double calcValorTotalCurso() {
        return descontoBolsa*6*getValorMes()*getQuantSemestre();
    }

    @Override
    public int calcTotalHorasSemanais() {
        return (getHoraAulaDia()*5)+(quantAulasExtraCurricularSemanais*5);
    }

    public double getDescontoBolsa() {
        return descontoBolsa;
    }

    public int getQuantAulasExtraCurricularSemanais() {
        return quantAulasExtraCurricularSemanais;
    }
    public double getTotalAulaSemanal(){
        return calcTotalHorasSemanais();
    }

    public double getValorTotalCurso(){
        return calcValorTotalCurso();
    }
}
