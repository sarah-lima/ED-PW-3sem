package br.com.bandtec.continuada.Controller;
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

import br.com.bandtec.continuada.*;
import br.com.bandtec.continuada.Models.Crianca;
import br.com.bandtec.continuada.Estudante;
import br.com.bandtec.continuada.Models.IngressoAdulto;
import br.com.bandtec.continuada.Repositório.IngressoAdultoRepository;
import br.com.bandtec.continuada.Repositório.IngressoEstudanteRepository;
import br.com.bandtec.continuada.Repositório.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class ZoologicoController {

    private List<Vendavel> vendas = new ArrayList<>();
    private Integer count=0;
    private PilhaObj<Vendavel> pilha = new PilhaObj<>(count);

    @Autowired
    private IngressoAdultoRepository adultoRepository;

    @Autowired
    private IngressoEstudanteRepository estudanteRepository;

    public ZoologicoController() {
    }

    @GetMapping("/adulto")
    public ResponseEntity getAdulto(){
        return ResponseEntity.status(200).body(adultoRepository.findAll());
    }

    @GetMapping("/estudante")
    public ResponseEntity getVEstudante(){
        return ResponseEntity.status(200).body(estudanteRepository.findAll());
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
        count++;
        pilha.push(c);
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
        count++;
        pilha.push(c);
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
        count++;
        pilha.push(c);
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
        count++;
        pilha.push(c);
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
    @GetMapping("/desfazer")
    public ResponseEntity desfazer(){
        if(pilha.isEmpty()){
            return ResponseEntity.status(404).body("Nãohá mais operação a ser desfeita");
        }else{
            vendas.remove(pilha.peek());
            return ResponseEntity.ok().build();
        }
    }
}
