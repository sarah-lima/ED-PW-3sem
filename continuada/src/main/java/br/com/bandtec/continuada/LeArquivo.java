package br.com.bandtec.continuada;

import br.com.bandtec.continuada.Models.Ingresso;
import br.com.bandtec.continuada.Models.IngressoAdulto;
import br.com.bandtec.continuada.Models.IngressoEstudante;
import br.com.bandtec.continuada.Models.Produtos;
import br.com.bandtec.continuada.Repository.IngressoAdultoRepository;
import br.com.bandtec.continuada.Repository.IngressoEstudanteRepository;
import br.com.bandtec.continuada.Repository.ProdutosRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeArquivo {
    public static void leArquivo(String nomeArq, IngressoAdultoRepository repository,
                                 IngressoEstudanteRepository estudanteRepository, ProdutosRepository produtosRepository) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;

        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        // Lê os registros do arquivo
        try {
            // Lê um registro
            registro = entrada.readLine();
            System.out.println(registro);
            while (registro != null) {
                // Obtém o tipo do registro
                tipoRegistro = registro.substring(0, 2); // obtém os 2 primeiros caracteres do registro

                if (tipoRegistro.equals("02")) {
                    Integer quantidadeIngresso = Integer.valueOf(registro.substring(2, 6).trim());
                    System.out.println(quantidadeIngresso);
                    Boolean premium = Boolean.valueOf(registro.substring(6, 11).trim());
                    System.out.println(premium);
                    Integer tipoIngresso = Integer.parseInt(registro.substring(11, 12));
                    System.out.println(tipoIngresso);
                    Ingresso ingresso = new Ingresso();
                    ingresso.setId(tipoIngresso);
                    IngressoAdulto ingressoAdulto = new IngressoAdulto();
                    ingressoAdulto.setQuantidade(quantidadeIngresso);
                    ingressoAdulto.setPremium(premium);
                    ingressoAdulto.setIngresso(ingresso);
                    repository.save(ingressoAdulto);
                } else if (tipoRegistro.equals("03")) {
                    Integer quantidadeIngresso = Integer.valueOf(registro.substring(2, 6).trim());
                    System.out.println(quantidadeIngresso);
                    Integer tipoIngresso = Integer.parseInt(registro.substring(6, 7));
                    System.out.println(tipoIngresso);
                    Ingresso ingresso = new Ingresso();
                    ingresso.setId(tipoIngresso);
                    IngressoEstudante ingressoEstudante = new IngressoEstudante();
                    ingressoEstudante.setQuantidade(quantidadeIngresso);
                    ingressoEstudante.setIngresso(ingresso);
                    estudanteRepository.save(ingressoEstudante);
                } else if (tipoRegistro.equals("04")) {
                    String tipoProduto = registro.substring(2, 12).trim();
                    System.out.println(tipoProduto);
                    String descricao = registro.substring(12, 25).trim();
                    System.out.println(descricao);
                    Integer quantidade = Integer.valueOf(registro.substring(25, 27));
                    System.out.println(quantidade);
                    Double valor = Double.valueOf(registro.substring(27, 33));
                    System.out.println(valor);
                    Produtos produtos = new Produtos();
                    produtos.setQuantProdutos(quantidade);
                    produtos.setDescricao(descricao);
                    produtos.setTipo(tipoProduto);
                    produtos.setValor(valor);
                    produtosRepository.save(produtos);
                } else if (tipoRegistro.equals("00") || tipoRegistro.equals("01")) {
                } else {
                    System.out.println("Tipo de registro inválido");
                }
                // lê o próximo registro
                registro = entrada.readLine();
                System.out.println(registro);

            }

            // Fecha o arquivo
            entrada.close();
            File file = new File(nomeArq);
            file.delete();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }

    }
}
