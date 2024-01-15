package com.incerti.domain.exception;

public class ProdutoNaoEncontratoException extends BusinessException {
    public ProdutoNaoEncontratoException(String message) {
        super(message);
    }

    public static ProdutoNaoEncontratoException produtoNaoEncontrato() {
        return new ProdutoNaoEncontratoException("Produto não encontrado");
    }
}
