package br.entrega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.entrega.model.Sla;
import br.entrega.service.EntregaService;


@RestController
@RequestMapping(value = "/entrega/", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntregaController {
	
	@Autowired
	private EntregaService entregaService;
	
	@PostMapping(path = "alterarPrazoEntrega/{uf}/{sla}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void criarPedido(@PathVariable("uf") String uf, @PathVariable("sla") Integer sla) {
		entregaService.alterarPrazoEntrega(uf, sla);
	}
	
	@GetMapping(path = "consultarPrazoEntrega/{uf}")
	public @ResponseBody Sla consultarPrazoEntrega(@PathVariable("uf") String uf) {
		return entregaService.consultarPrazoEntrega(uf);
	}
}
