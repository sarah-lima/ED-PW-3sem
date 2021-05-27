package br.com.bandtec.continuada.Repository;

import br.com.bandtec.continuada.Models.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {
}
