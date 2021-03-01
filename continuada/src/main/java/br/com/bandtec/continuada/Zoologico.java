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
public class Zoologico {

    private List<Vendavel> cursos = new ArrayList<>();

    public Zoologico() {

    }

    @GetMapping
    public List<Vendavel> getCursaveis(){
        return cursos;
    }

    @GetMapping("/{posicao}")
    public Vendavel getCursavel(@PathVariable int posicao){
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
    public String postCursavel(@RequestBody Adulto c){
        cursos.add(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping("/{posicao}/bolsista")
    public String putCursavel(@PathVariable int posicao,
                              @RequestBody Adulto c){
        if(posicao-1<cursos.size()){
            cursos.set(posicao-1,c);
            return "Atualizado com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }
    @PostMapping("/nao-bolsista")
    public String postCursavel(@RequestBody Estudante c){
        cursos.add(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping("/{posicao}/nao-bolsista")
    public String putCursavel(@PathVariable int posicao,
                              @RequestBody Estudante c){
        if(posicao-1<cursos.size()){
            cursos.set(posicao-1,c);
            return "Atualizado com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }
    @PostMapping("/professor")
    public String postCursavel(@RequestBody Produtos c){
        cursos.add(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping("/{posicao}/professor")
    public String putCursavel(@PathVariable int posicao,
                              @RequestBody Produtos c){
        if(posicao-1<cursos.size()){
            cursos.set(posicao-1,c);
            return "Atualizado com sucesso";
        } else {
            return "Elemento não encontrado";
        }
    }
}