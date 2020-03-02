package br.pedido.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.pedido.model.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

	List<Pedido> findByUf(String uf);

	
}
