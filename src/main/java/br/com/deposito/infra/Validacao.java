package br.com.deposito.infra;

import org.springframework.validation.FieldError;

public record Validacao(String campo,String mensagem) {
 public Validacao(FieldError erros) {
	 this(erros.getField(),erros.getDefaultMessage());
 }
}
