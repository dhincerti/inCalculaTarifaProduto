package com.incerti.infrastructure;

import com.incerti.domain.exception.BusinessException;
import com.incerti.domain.exception.ProdutoNaoEncontratoException;
import com.incerti.domain.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = { ProdutoNaoEncontratoException.class} )
    protected ResponseEntity<Object> handleConflict(ProdutoNaoEncontratoException ex, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).build();
    }

    @ExceptionHandler(value = { BusinessException.class} )
    protected ResponseEntity<Object> handleConflict(BusinessException ex, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(ex.getMessage());
    }

    @ExceptionHandler(value = { SystemException.class} )
    protected ResponseEntity<Object> handleConflict(SystemException ex, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(ex.getMessage());
    }
}
