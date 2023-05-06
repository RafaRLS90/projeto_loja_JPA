package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos") // ensinando o JPA a reconhecer o nome para buscar na tabela
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "valor_total")//para nome no banco de dados seguir o nome passado aqui no caso, e não o camelCase abaixo
	private BigDecimal valorTotal = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY)//Carregamento preguiçoso.Só o necessário
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)//cascade, que é para falarmos para fazer o efeito cascata: tudo o que acontecer com o "pedido", faça também no ItemPedido
	private List<ItemPedido> itens = new ArrayList<>();// inicializando lista

	public Pedido() {

	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public void adicionarItem(ItemPedido item) {
		item.setPedido(this); //é usado para referenciar a um atributo externo fora do metodo
		this.itens.add(item);//lista de itens esta tendo adicionado o item
		this.valorTotal = this.valorTotal.add(item.getValor());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
