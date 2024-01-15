package com.incerti.aplication.usecases;

import com.incerti.adapter.rest.controller.ProdutoController;
import com.incerti.aplication.repository.ProdutoRepository;
import com.incerti.domain.exception.BusinessException;
import com.incerti.domain.exception.SystemException;
import com.incerti.domain.model.Produto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

    private final ProdutoRepository produtoRepository;
    private final TarifasUseCase tarifasUseCase;

    public Produto consultaProduto(UUID id) {
        try {
            LOGGER.info("Consultando o produto {}", id);

            Produto produto = produtoRepository.consulta(id);

            LOGGER.info("Produto encontrado {}", produto);
            return produto;
        } catch (BusinessException e) {
            LOGGER.info("Falha ao consultar produto {}", id);
            throw e;
        } catch (Exception e) {
            LOGGER.info("Falha ao consultar produto {}", id);
            throw new SystemException("Falha ao consultar produto", e);
        }
    }

    @Transactional
    public Produto criaProduto(Produto produto) {
        try {
            LOGGER.info("Criando produto {}", produto);

            produto.setPrecoTarifado(tarifasUseCase.calculaPrecoTarifado(produto));

            Produto novoProduto = produtoRepository.cria(produto);

            LOGGER.info("Produto criado {}", novoProduto);
            return novoProduto;
        } catch (BusinessException e) {
            LOGGER.info("Falha ao criar produto {}", produto);
            throw e;
        } catch (Exception e) {
            LOGGER.info("Falha ao criar produto {}", produto);
            throw new SystemException("Falha ao criar produto", e);
        }
    }

    @Transactional
    public Produto atualizaProduto(Produto produto) {
        try {
            LOGGER.info("Atualizando produto {}", produto);

            produto.setPrecoTarifado(tarifasUseCase.calculaPrecoTarifado(produto));

            Produto produtoAtualizado = produtoRepository.atualiza(produto);

            LOGGER.info("Produto atualizado {}", produtoAtualizado);
            return produtoAtualizado;
        } catch (BusinessException e) {
            LOGGER.info("Falha ao atualizar produto {}", produto);
            throw e;
        } catch (Exception e) {
            LOGGER.info("Falha ao atualizar produto {}", produto);
            throw new SystemException("Falha ao atualizar produto", e);
        }
    }

    @Transactional
    public void deletaProduto(UUID id) {
        try {
            LOGGER.info("Deletando produto {}", id);

            produtoRepository.deleta(id);

            LOGGER.info("Produto deletado {}", id);
        } catch (BusinessException e) {
            LOGGER.info("Falha ao deletar produto {}", id);
            throw e;
        } catch (Exception e) {
            LOGGER.info("Falha ao deletar produto {}", id);
            throw new SystemException("Falha ao deletar produto", e);
        }
    }
}
