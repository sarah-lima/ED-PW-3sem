package br.com.bandtec.continuada.Controller;

import br.com.bandtec.continuada.Models.Produtos;
import br.com.bandtec.continuada.PilhaObj;
import br.com.bandtec.continuada.Repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosRepository repository;


    private PilhaObj<Produtos> pilha = new PilhaObj<>(10);

    @GetMapping
    public ResponseEntity getProdutos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public String postProdutos(@RequestBody Produtos c) {
        repository.save(c);
        pilha.push(c);
        return "Usuário adicionado com sucesso";
    }

    @PutMapping
    public ResponseEntity putProdutos(@RequestBody @Valid Produtos c) {
        if (repository.existsById(c.getId())) {
            return ResponseEntity.ok(repository.save(c));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProdutos(@PathVariable int id) {
        if (repository.existsById(id)) {
            Optional<Produtos> product = repository.findById(id);
            repository.deleteById(id);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
