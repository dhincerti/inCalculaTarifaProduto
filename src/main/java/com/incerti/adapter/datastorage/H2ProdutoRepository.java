package com.incerti.adapter.datastorage;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface H2ProdutoRepository extends CrudRepository<ProdutoEntity, UUID> {
}
