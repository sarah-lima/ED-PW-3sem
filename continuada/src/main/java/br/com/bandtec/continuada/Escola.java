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
@RequestMapping("/cursaveis")
public class Escola {

    private List<Cursavel> cursos = new ArrayList<>();

    public Escola() {
        cursos.add(new Bolsista("Sarah",5,100.0,4,0.3,3));
        cursos.add(new Bolsista("Juliana",10,400.0,5,0.5,10));
        cursos.add(new NaoBolsista("Marcio",4,500.0,6));
        cursos.add(new Professor("Juliano", 3,100.0,10));
    }

    @GetMapping
    public List<Cursavel> getCursaveis(){
        return cursos;
    }

    @GetMapping("/{posicao}")
    public Cursavel getCursavel(@PathVariable int posicao){
        if(posicao-1<cursos.size()){
            return cursos.get(posicao-1);
        } else{
            return null;
        }
    }

    @DeleteMapping("/{posicao}")
    public String deleteCursavel(@PathVariable int posicao){
        if(posicao-1<cursos.size()){
            cursos.remove(posicao-1);
            return "Removido com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }

    @PostMapping("/bolsista")
    public String postCursavel(@RequestBody Bolsista c){
        cursos.add(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping("/{posicao}/bolsista")
    public String putCursavel(@PathVariable int posicao,
                              @RequestBody Bolsista c){
        if(posicao-1<cursos.size()){
            cursos.set(posicao-1,c);
            return "Atualizado com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }
    @PostMapping("/nao-bolsista")
    public String postCursavel(@RequestBody NaoBolsista c){
        cursos.add(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping("/{posicao}/nao-bolsista")
    public String putCursavel(@PathVariable int posicao,
                              @RequestBody NaoBolsista c){
        if(posicao-1<cursos.size()){
            cursos.set(posicao-1,c);
            return "Atualizado com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }
    @PostMapping("/professor")
    public String postCursavel(@RequestBody Professor c){
        cursos.add(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping("/{posicao}/professor")
    public String putCursavel(@PathVariable int posicao,
                              @RequestBody Professor c){
        if(posicao-1<cursos.size()){
            cursos.set(posicao-1,c);
            return "Atualizado com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }
}