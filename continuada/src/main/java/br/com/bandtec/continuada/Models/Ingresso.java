package br.com.bandtec.continuada.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private double valor;

    @OneToMany(mappedBy = "ingresso")
    @JsonIgnore
    private List<IngressoAdulto> ingressoAdulto;

    public List<IngressoAdulto> getIngressoAdulto() {
        return ingressoAdulto;
    }

    public void setIngressoAdulto(List<IngressoAdulto> ingressoAdulto) {
        this.ingressoAdulto = ingressoAdulto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
