package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {
	
public static void main(String[] args) {
	popularBandoDeDados();
	EntityManager em = JPAUtil.getEntityManager();
	ProdutoDao produtoDao = new ProdutoDao(em);
	
	Produto p = produtoDao.buscarPorId(1l);
	System.out.println(p.getPreco());
	
	List<Produto> todos = produtoDao.buscarTodos();
	todos.forEach(p2 -> System.out.println(p2.getNome()));
	}

private static void popularBandoDeDados() {
	Categoria celulares = new Categoria("CELULARES");
	Produto celular = new Produto("Xiaomi Redmi","Muito legal", new BigDecimal("800"), celulares);
	
	
	EntityManager em = JPAUtil.getEntityManager();
	ProdutoDao produtoDao = new ProdutoDao(em);
	CategoriaDao categoriaDao = new CategoriaDao(em);
	
	em.getTransaction().begin();
	
	categoriaDao.cadastrar(celulares);
	produtoDao.cadastrar(celular);
	em.getTransaction().commit();
	em.close();
}	
	
}


//Informações importantes
//Transient -> entidade desconhecida
//Managed -> já conhecida, porém pode ser modificada
//Detached -> não pode ser modificada, após um clear ou close
//Commit -> sobe para banco de dados
//Merge -> posso modficar um detached
