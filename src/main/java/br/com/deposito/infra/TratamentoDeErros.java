package br.com.deposito.infra;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeErros {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<MensagemDeErro>ObjetoNaoEncontrado(){
		var erros = new MensagemDeErro(HttpStatus.NOT_FOUND,"Objeto não encontrado !");
		return new ResponseEntity<>(erros,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<MensagemDeErro>saldoInsuficiente(){
		var saldo = new MensagemDeErro(HttpStatus.BAD_REQUEST, "Saldo insuficiente !");
		return new ResponseEntity<>(saldo,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>validarCampos(MethodArgumentNotValidException ex){
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(Validacao::new).toList());
	}
}
