package br.com.bandtec.continuada.Controller;

import br.com.bandtec.continuada.*;
import br.com.bandtec.continuada.Models.IngressoAdulto;
import br.com.bandtec.continuada.Repositório.IngressoAdultoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/adulto")
public class AdultoController {

    private PilhaObj<IngressoAdulto> pilhaAdulto = new PilhaObj<>(10);
    private PilhaObj<IngressoAdulto> pilhaAdultoUpdate = new PilhaObj<>(10);
    private Fila<IngressoAdulto> fila = new Fila(10);
    @Autowired
    private IngressoAdultoRepository adultoRepository;

    private Optional<IngressoAdulto> ingressoAdulto;

    @GetMapping
    public ResponseEntity getAdulto() {
        return ResponseEntity.status(200).body(adultoRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProdutos(@PathVariable int id) {
        if (adultoRepository.existsById(id)) {
            Optional<IngressoAdulto> ingressoAdulto = adultoRepository.findById(id);
            adultoRepository.deleteById(id);
            return ResponseEntity.ok(ingressoAdulto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
//
//    @GetMapping("/fila")
//    public ResponseEntity getFilaAdulto(){
//    }
    @PostMapping
    public ResponseEntity postIngressoAdulto(@RequestBody IngressoAdulto c) {
        c.setTempId(UUID.randomUUID().toString());
        pilhaAdulto.push(c);
        System.out.println(c);
        return ResponseEntity.status(201).body(adultoRepository.save(c));
    }

    @PutMapping
    public ResponseEntity putVendavelEstudante(@RequestBody IngressoAdulto c) {
        if (adultoRepository.existsById(c.getId())) {
            pilhaAdultoUpdate.push(adultoRepository.findById(c.getId()).get());
            adultoRepository.save(c);
            return ResponseEntity.ok(pilhaAdultoUpdate.peek());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/desfazer/update")
    public ResponseEntity desfazerUpdate() {
        if (pilhaAdultoUpdate.isEmpty()) {
            return ResponseEntity.status(404).body("Não há mais operação a ser desfeita");
        } else {
            IngressoAdulto ingresso = pilhaAdultoUpdate.pop();
            adultoRepository.save(ingresso);
            return ResponseEntity.ok(ingresso);
        }
    }

    @GetMapping("/desfazer")
    public ResponseEntity desfazer() {
        if (pilhaAdulto.isEmpty()) {
            return ResponseEntity.status(404).body("Não há mais operação a ser desfeita");
        } else {
            adultoRepository.delete(pilhaAdulto.pop());
            return ResponseEntity.ok().build();
        }
    }
}
