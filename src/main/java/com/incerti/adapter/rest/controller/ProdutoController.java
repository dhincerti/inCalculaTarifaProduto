package com.incerti.adapter.rest.controller;

import com.incerti.adapter.mappers.ProdutoRequestMapper;
import com.incerti.adapter.mappers.ProdutoResponseMapper;
import com.incerti.aplication.usecases.ProdutoUseCase;
import com.incerti.adapter.rest.request.ProdutoRequest;
import com.incerti.adapter.rest.response.ProdutoResponse;
import com.incerti.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);

    private final ProdutoUseCase produtoUseCase;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoResponse> consultaProduto(@PathVariable UUID id) {
        LOGGER.info("Iniciando consulta do produto {}", id);

        Produto produto = produtoUseCase.consultaProduto(id);

        ProdutoResponse response = ProdutoResponseMapper.map(produto);
        LOGGER.info("Produto consultado com sucesso {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoResponse> criaProduto(@RequestBody ProdutoRequest request) {
        LOGGER.info("Iniciando criação do produto {}", request);

        Produto produto = ProdutoRequestMapper.map(request);

        Produto produtoCriado = produtoUseCase.criaProduto(produto);

        ProdutoResponse response = ProdutoResponseMapper.map(produtoCriado);
        LOGGER.info("Produto criado com sucesso {}", response);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoResponse> atualizaProduto(@PathVariable UUID id, @RequestBody ProdutoRequest request) {
        LOGGER.info("Iniciando atualizacao do produto {}", request);

        Produto produto = ProdutoRequestMapper.map(request);
        produto.setId(id);

        Produto produtoAtualizado = produtoUseCase.atualizaProduto(produto);

        ProdutoResponse response = ProdutoResponseMapper.map(produtoAtualizado);
        LOGGER.info("Produto atualizado com sucesso {}", response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deletaProduto(@PathVariable UUID id) {
        LOGGER.info("Iniciando delecao do produto {}", id);

        produtoUseCase.deletaProduto(id);

        LOGGER.info("Produto deletado com sucesso {}", id);
        return ResponseEntity.noContent().build();
    }
}
