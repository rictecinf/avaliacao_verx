package br.com.verex.avaliacao.entity;

/**
 * Excecao raiz para problemas de Dominio.
 * Avaliar sempre a criacao de excecoes especificas para os problemas de dominio, extendendo esta excecao.
 */
public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

}
