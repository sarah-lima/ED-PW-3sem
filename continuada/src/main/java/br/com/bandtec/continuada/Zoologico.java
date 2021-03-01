package br.com.bandtec.continuada;
/*
Foi utilizado a interface para forçar duas classes que não tem relação
(não conseguem se relacionar por herança mas que irão implementar o
mesmo método de formas diferentes) a terem o mesmo método
 que foi inserido como assinatura dentro da interface Cursável
*/
/*
 Foi utilizado a classe abstrata para que as classes que herdassem
 ela recebessem obrigatóriamente seus métodos e atributos
*/

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class Zoologico {

    private List<Vendavel> vendas = new ArrayList<>();

    public Zoologico() {
        vendas.add(new Produtos(12,"sorvete","baunilha com chocolate",10.0,3));
        vendas.add(new Produtos(15,"maça do amor","maça caramelizada com cobertura de chocolate",15.0,2));
        vendas.add(new Adulto(100,25.0,9));
        vendas.add(new Crianca(500,25.0,10));
        vendas.add(new Estudante(250,25.0,2));
    }

    @GetMapping
    public List<Vendavel> getVendavel(){
        return vendas;
    }

    @GetMapping("/total-lucro")
    public String getLucro(){
        double total=0.0;
        for (Vendavel v:
             vendas) {
            total+=v.calcTotalLucro();
        }
        return String.format("Seu lucro total foi de %.2f",total);
    }
    @GetMapping("/{posicao}")
    public Vendavel getVendavel(@PathVariable int posicao){
        if(posicao-1<vendas.size()){
            return vendas.get(posicao-1);
        } else{
            return null;
        }
    }

    @DeleteMapping("/{posicao}")
    public String deleteVendavel(@PathVariable int posicao){
        if(posicao-1<vendas.size()){
            vendas.remove(posicao-1);
            return "Removido com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }

    @PostMapping("/adulto")
    public String postVendavelAdulto(@RequestBody Adulto c){
        vendas.add(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping("/{posicao}/adulto")
    public String putVendavelAdulto(@PathVariable int posicao,
                              @RequestBody Adulto c){
        if(posicao-1<vendas.size()){
            vendas.set(posicao-1,c);
            return "Atualizado com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }
    @PostMapping("/estudante")
    public String postVendavelEstudante(@RequestBody Estudante c){
        vendas.add(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping("/{posicao}/estudante")
    public String putVendavelEstudante(@PathVariable int posicao,
                              @RequestBody Estudante c){
        if(posicao-1<vendas.size()){
            vendas.set(posicao-1,c);
            return "Atualizado com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }

    @PostMapping("/crianca")
    public String postVendavelCriancas(@RequestBody Crianca c){
        vendas.add(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping("/{posicao}/crianca")
    public String putVendavelCriancas(@PathVariable int posicao,
                                      @RequestBody Crianca c){
        if(posicao-1<vendas.size()){
            vendas.set(posicao-1,c);
            return "Atualizado com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }

    @PostMapping("/produtos")
    public String postVendavelProdutos(@RequestBody Produtos c){
        vendas.add(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping("/{posicao}/produtos")
    public String putVendavelProdutos(@PathVariable int posicao,
                              @RequestBody Produtos c){
        if(posicao-1<vendas.size()){
            vendas.set(posicao-1,c);
            return "Atualizado com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }
}