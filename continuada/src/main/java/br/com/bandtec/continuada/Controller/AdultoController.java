package br.com.bandtec.continuada.Controller;

import br.com.bandtec.continuada.*;
import br.com.bandtec.continuada.Models.IngressoAdulto;
import br.com.bandtec.continuada.Repository.IngressoAdultoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/adulto")
public class AdultoController {

    private PilhaObj<IngressoAdulto> pilhaAdulto = new PilhaObj<>(10);
    private PilhaObj<IngressoAdulto> pilhaAdultoUpdate = new PilhaObj<>(10);
    private Fila<IngressoAdulto> fila = new Fila(10);
    private List<IngressoAdulto> resultado = new ArrayList<>();

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

    @GetMapping("/fila")
    public ResponseEntity assincrona(@RequestBody IngressoAdulto c){
        c.setTempId(UUID.randomUUID().toString());
        fila.insert(c);
        System.out.println(c);
        return ResponseEntity.ok(c.getTempId());
    }

    /*
    * ID 1-2c5a7d9d-d68f-4d2d-ac81-4c8433d384b7
    * ID 2-17e161f2-8ade-4152-96d3-1aef2a7f2c4b
    * ID 3-796f591d-66ca-4f42-aabe-2bb4cf421c7f
    * ID 4-236174b0-2cce-4497-af76-95b787dc78e4
    * ID 5-
    * */

    @PostMapping("/fila")
    public ResponseEntity post(){
        if(fila.isEmpty()){
            return ResponseEntity.ok("Não há nenhuma requisição a tratar");
        }else{
            IngressoAdulto ingresso=fila.pool();
            pilhaAdulto.push(ingresso);
            resultado.add(ingresso);
            ingresso.setId(0);
            adultoRepository.save(ingresso);
            return ResponseEntity.status(201).build();
        }
    }

    @GetMapping("/fila/{uuid}")
    public ResponseEntity getConsulta(@PathVariable UUID uuid){
        for(int i=0; i< resultado.size();i++){
            if(resultado.get(i).getTempId().equals(uuid.toString())){
                System.out.println("Foi");
                resultado.remove(i);
                return ResponseEntity.ok("Resultado processado com sucesso");
            }
        }
        return ResponseEntity.noContent().build();
    }

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
            IngressoAdulto naoModificado = adultoRepository.findById(c.getId()).get();
            IngressoAdulto teste= new IngressoAdulto();
            teste.setIngresso(naoModificado.getIngresso());
            teste.setPremium(naoModificado.isPremium());
            teste.setQuantidade(naoModificado.getQuantidade());
            teste.setTempId(naoModificado.getTempId());
            teste.setId(naoModificado.getId());

            pilhaAdultoUpdate.push(teste);
            System.out.println(pilhaAdultoUpdate.peek().getQuantidade());
            adultoRepository.save(c);
            System.out.println(pilhaAdultoUpdate.peek().getQuantidade());

            return ResponseEntity.ok(c);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/desfazer/update")
    public ResponseEntity desfazerUpdate() {
        if (pilhaAdultoUpdate.isEmpty()) {
            return ResponseEntity.status(404).body("Não há mais operação a ser desfeita");
        } else {
            adultoRepository.save(pilhaAdultoUpdate.pop());
            return ResponseEntity.ok(adultoRepository.findAll());
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
