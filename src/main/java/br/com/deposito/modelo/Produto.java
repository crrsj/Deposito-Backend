package br.com.deposito.modelo;

import br.com.deposito.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produto {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String imagem;
	private int quantidade;
	private double valor;
	private Integer estoque;
	private double total;
	private Status Status;
	
	public void atualizando(Produto produto) {
		if(produto.estoque != null) {
			this.estoque = this.estoque + produto.estoque;
		}
		
	}
	
}
