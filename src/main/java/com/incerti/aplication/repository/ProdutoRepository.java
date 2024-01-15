package com.incerti.aplication.repository;

import com.incerti.domain.model.Produto;

import java.util.UUID;

// TODO: Doc
public interface ProdutoRepository {

    Produto cria(Produto produto);

    Produto consulta(UUID id);

    Produto atualiza(Produto produto);

    void deleta(UUID id);
}
