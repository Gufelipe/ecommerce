package com.gufelipe.ecommerce.apis;

import com.gufelipe.ecommerce.repository.EstoqueRepository;
import com.gufelipe.ecommerce.entity.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueApis {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EstoqueRepository estoqueRepository;

    public EstoqueApis(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    @GetMapping
    public List<Produto> listarTodos(){
        logger.info("Listando todos os produtos");
        return estoqueRepository.listarProdutos();
    }

    @PostMapping
    public void inserirProduto(@RequestBody Produto produto){
        logger.debug("Inserindo o produto={}", produto);
        estoqueRepository.inserirItem(produto);
    }

    @PutMapping
    public void atualizarProduto(@RequestBody Produto produto){
        logger.info("Atualizando o produto de id={}", produto.getId());
        estoqueRepository.atualizarProduto(produto);
    }

    @DeleteMapping("/{idProduto}")
        public void deletarProduto(@PathVariable Long idProduto){
        logger.info("Deletando o produto de id={}", idProduto);
        estoqueRepository.removerItem(idProduto);
    }
}
