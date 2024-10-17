package br.com.deposito.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.deposito.modelo.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{

}
