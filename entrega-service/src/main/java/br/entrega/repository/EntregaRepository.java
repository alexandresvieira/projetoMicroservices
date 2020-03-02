package br.entrega.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.entrega.model.Sla;

@Repository
public interface EntregaRepository extends CrudRepository<Sla, Integer> {
	
	Sla findByUf(String uf);

}
