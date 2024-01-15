package com.incerti.adapter.persistence;

import com.incerti.adapter.mappers.ProdutoEntityMapper;
import com.incerti.aplication.repository.ProdutoRepository;
import com.incerti.domain.exception.ProdutoNaoEncontratoException;
import com.incerti.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.incerti.adapter.mappers.ProdutoEntityMapper.map;

@Component
@RequiredArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoRepository {
    private final H2ProdutoRepository h2ProdutoRepository;

    @Override
    public Produto cria(Produto produto) {
        ProdutoEntity entity = ProdutoEntityMapper.map(produto);

        ProdutoEntity produtoCriado = h2ProdutoRepository.save(entity);

        return ProdutoEntityMapper.map(produtoCriado);
    }

    @Override
    public Produto consulta(UUID id) {
        ProdutoEntity produto = findByIdOrThrows(id);

        return ProdutoEntityMapper.map(produto);
    }

    @Override
    public Produto atualiza(Produto produto) {
        ProdutoEntity produtoAtual = findByIdOrThrows(produto.getId());
        ProdutoEntity produtoAtualizado = ProdutoEntityMapper.map(produtoAtual.getId(), produto);

        produtoAtualizado = h2ProdutoRepository.save(produtoAtualizado);

        return ProdutoEntityMapper.map(produtoAtualizado);
    }

    @Override
    public void deleta(UUID id) {
        h2ProdutoRepository.deleteById(id);
    }


    private ProdutoEntity findByIdOrThrows(UUID id) {
        return h2ProdutoRepository.findById(id)
                .orElseThrow(ProdutoNaoEncontratoException::produtoNaoEncontrato);
    }
}
