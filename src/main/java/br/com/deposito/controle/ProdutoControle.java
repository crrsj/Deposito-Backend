package br.com.deposito.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.deposito.modelo.Produto;
import br.com.deposito.servico.ProdutoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("produtos")
@CrossOrigin(origins = "*") 
public class ProdutoControle {

	@Autowired
	private ProdutoServico produtoServico;
	
	@PostMapping
	@Operation(summary = "Endpoint responsável pelo cadastro de produtos.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Produto>criarProduto(@RequestBody Produto produto){
		var criar = produtoServico.criarProduto(produto);
		return new ResponseEntity<>(criar,HttpStatus.CREATED);
	}
	@GetMapping
	@Operation(summary = "Endpoint responsável pela listagem de produtos.") 
    @ApiResponse(responseCode = "200",description = "sucesso",content = {
   		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<Produto>>listarProdutos(){
		var listar = produtoServico.listarProdutos();
		return new ResponseEntity<List<Produto>>(listar,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Endpoint responsável pela busca de produtos pelo ID.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Produto>buscarProduto(@PathVariable Long id){
		var busca = produtoServico.buscarProduto(id);
		return new ResponseEntity<>(busca,HttpStatus.OK);
		
	}
	@PutMapping
	@Operation(summary = "Endpoint responsável pela compra de produtos.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Produto>comprarProduto(@RequestBody Produto produto){
		var compra = produtoServico.comprarProduto(produto);
		return new ResponseEntity<>(compra,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Endpoint responsável por excluir um produto.") 
    @ApiResponse(responseCode = "204",description = "sucesso",content = {
   		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Void>excluirProduto(@PathVariable Long id){
		 produtoServico.excluirProduto(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
