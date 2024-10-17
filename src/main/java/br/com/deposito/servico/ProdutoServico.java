package br.com.deposito.servico;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.deposito.enums.Status;
import br.com.deposito.modelo.Produto;
import br.com.deposito.repositorio.ProdutoRepositorio;
import jakarta.transaction.Transactional;

@Service
public class ProdutoServico {
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	public Produto criarProduto(Produto produto) {
		produto.setTotal(0);
		return produtoRepositorio.save(produto);
	}
	
	public List<Produto>listarProdutos(){
		return produtoRepositorio.findAll();
	}
	
	public Produto buscarProduto(Long id) {
	Optional<Produto> buscar = produtoRepositorio.findById(id);
	return buscar.orElseThrow(NoSuchElementException::new);
	}
	
	@Transactional
	public Produto comprarProduto(Produto produto) {	
		if(produto.getQuantidade() > produto.getEstoque()) {
			throw new RuntimeException("Estoque insuficiente");
		}
		produto.setEstoque(produto.getEstoque()- produto.getQuantidade());
		produto.setTotal(produto.getValor() * produto.getQuantidade());
		produto.setStatus(Status.VENDIDO);
		return produtoRepositorio.save(produto);
		}
	
	   public void excluirProduto(Long id) {
		   produtoRepositorio.deleteById(id);
	   }
	   
	   
	   @Transactional
	   public Produto atualizarEstoque(Produto produto) {
		   var atualizar = produtoRepositorio.getReferenceById(produto.getId());
		   atualizar.atualizando(produto);
		   return produtoRepositorio.save(atualizar);
	   }
	}

   



