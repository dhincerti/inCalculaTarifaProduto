package com.incerti.aplication.repository;

import com.incerti.domain.model.Produto;

import java.util.UUID;

public interface ProdutoRepository {

    /**
     * Cria um novo {@link Produto}.
     *
     * @param produto Objeto contendo os atributos para o novo {@link Produto}.
     *                Obs: O ID, será automaticamente gerado.
     *
     * @return Objeto representando o {@link Produto} criado.
     */
    Produto cria(Produto produto);

    /**
     * Recupera os valores de um {@link Produto}, baseado em seu ID.
     *
     * @param id Identificador do {@link Produto}.
     *
     * @return Objeto representando o {@link Produto} consultado.
     */
    Produto consulta(UUID id);

    /**
     * Atualiza {@link Produto} existente
     *
     * @param produto Objeto contendo os atributos para o novo {@link Produto}.
     * @return Objeto representando o {@link Produto} atualizado.
     */
    Produto atualiza(Produto produto);

    /**
     * Delete um {@link Produto} existente.
     *
     * @param id
     */
    void deleta(UUID id);
}
