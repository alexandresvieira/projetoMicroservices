package br.entrega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.entrega.model.Sla;
import br.entrega.repository.EntregaRepository;

@Service
public class EntregaService {
	
	@Autowired
	private EntregaRepository entregaRepository;

	public void alterarPrazoEntrega(String uf, Integer sla) {		
		entregaRepository.save(new Sla(uf, sla));
	}

	public Sla consultarPrazoEntrega(String uf) {		
		return entregaRepository.findFirstByUfOrderByIdDesc(uf);
	}

}
