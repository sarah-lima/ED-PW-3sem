package br.com.bandtec.continuada.Repositório;

import br.com.bandtec.continuada.Models.IngressoAdulto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngressoAdultoRepository extends JpaRepository<IngressoAdulto, Integer> {
    Optional<IngressoAdulto> findByTempId(String uuid);
}
