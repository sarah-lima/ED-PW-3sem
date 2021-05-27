package br.com.bandtec.continuada.Controller;

import br.com.bandtec.continuada.Models.IngressoEstudante;
import br.com.bandtec.continuada.PilhaObj;
import br.com.bandtec.continuada.Repository.IngressoEstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {

    @Autowired
    private IngressoEstudanteRepository estudanteRepository;

    private PilhaObj<IngressoEstudante> pilhaEstudante = new PilhaObj<>(10);

    @GetMapping
    public ResponseEntity getVEstudante() {
        return ResponseEntity.status(200).body(estudanteRepository.findAll());
    }


    @PostMapping
    public ResponseEntity postVendavelEstudante(@RequestBody IngressoEstudante c) {
        pilhaEstudante.push(c);
        return ResponseEntity.status(201).body(estudanteRepository.save(c));
    }

    @PutMapping
    public ResponseEntity putVendavelEstudante(@RequestBody @Valid IngressoEstudante c) {
        if (estudanteRepository.existsById(c.getId())) {
            return ResponseEntity.ok(estudanteRepository.save(c));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProdutos(@PathVariable int id) {
        if (estudanteRepository.existsById(id)) {
            Optional<IngressoEstudante> ingressoEstudante = estudanteRepository.findById(id);
            estudanteRepository.deleteById(id);
            return ResponseEntity.ok(ingressoEstudante);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/desfazer")
    public ResponseEntity desfazer() {
        if (pilhaEstudante.isEmpty()) {
            return ResponseEntity.status(404).body("Nãohá mais operação a ser desfeita");
        } else {
            IngressoEstudante i = pilhaEstudante.peek();
            estudanteRepository.deleteById(i.getId());
            return ResponseEntity.ok().build();
        }
    }
}
