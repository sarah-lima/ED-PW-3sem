package br.com.bandtec.continuada.Repositório;

import br.com.bandtec.continuada.Models.IngressoEstudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoEstudanteRepository extends JpaRepository<IngressoEstudante, Integer> {
}
