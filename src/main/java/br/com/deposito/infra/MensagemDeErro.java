package br.com.deposito.infra;

import org.springframework.http.HttpStatus;

public record MensagemDeErro(HttpStatus status,String mensagem) {

}
