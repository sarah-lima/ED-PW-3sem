package br.com.bandtec.continuada;

public class Produtos implements Vendavel {
    private int id;
    private String nome;
    private String descricao;
    private int quantProdutos;
    private double valor;

    public Produtos(int id, String nome, String descricao, double valor, int quantProdutos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.quantProdutos = quantProdutos;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantProdutos() {
        return quantProdutos;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public double calcTotalLucro() {
        return (valor*quantProdutos)*0.5;
    }


    public String getTotalLucro(){
        return String.format("%.2f",calcTotalLucro());
    }
}