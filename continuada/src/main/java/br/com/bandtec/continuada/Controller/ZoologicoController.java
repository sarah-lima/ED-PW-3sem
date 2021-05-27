package br.com.bandtec.continuada.Controller;

import br.com.bandtec.continuada.LeArquivo;
import br.com.bandtec.continuada.Models.IngressoAdulto;
import br.com.bandtec.continuada.Models.IngressoEstudante;
import br.com.bandtec.continuada.Repository.IngressoAdultoRepository;
import br.com.bandtec.continuada.Repository.IngressoEstudanteRepository;
import br.com.bandtec.continuada.Repository.IngressoRepository;
import br.com.bandtec.continuada.Repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/zoologico")
public class ZoologicoController {
    @Autowired
    private IngressoAdultoRepository ingressoAdultoRepository;
    @Autowired
    private IngressoEstudanteRepository ingressoEstudanteRepository;
    @Autowired
    private ProdutosRepository produtosRepository;


    @PostMapping("/import")
    public ResponseEntity getImportProduct(@RequestParam MultipartFile arquivo) throws IOException {
        byte[] conteudo = arquivo.getBytes();
        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);
        System.out.println(conteudo);
        System.out.println(arquivo.getOriginalFilename());
        LeArquivo.leArquivo(arquivo.getOriginalFilename(), ingressoAdultoRepository,
                ingressoEstudanteRepository, produtosRepository);
        return ResponseEntity.status(201).build();
    }
}
