package br.com.bandtec.continuada.Repository;

import br.com.bandtec.continuada.Models.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Integer> {
}
