package br.com.bandtec.continuada.Models;

import javax.persistence.*;

@Entity
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String descricao;

    private int quantProdutos;

    private double valor;

    @ManyToOne
    private IngressoAdulto ingressoAdulto;

}
