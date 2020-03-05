package br.entrega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.entrega.model.Sla;
import br.entrega.service.EntregaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


@RestController
@RequestMapping(value = "/entrega/", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntregaController {
	
	@Autowired
	private EntregaService entregaService;

	@PostMapping(path = "alterarPrazoEntrega/{uf}/{sla}")
	@ApiImplicitParams({
		@ApiImplicitParam(dataType = "String",name = "uf" , value = "UF" , required = true),
		@ApiImplicitParam(dataType = "int",name = "sla" , value = "SLA" , required = true)})
	public void alterarPrazoEntrega(@RequestParam("uf") String uf, @RequestParam("sla") int sla) {
		entregaService.alterarPrazoEntrega(uf, sla);
	}
	
	@GetMapping(path = "consultarPrazoEntrega/{uf}")
	public @ResponseBody Sla consultarPrazoEntrega(@PathVariable("uf") String uf) {
		return entregaService.consultarPrazoEntrega(uf);
	}
}
