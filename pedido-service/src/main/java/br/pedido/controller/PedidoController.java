package br.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.pedido.model.Pedido;
import br.pedido.service.PedidoService;

@RestController
@RequestMapping(value = "/pedido/", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService; 

	@PostMapping(path = "criarPedido", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void criarPedido(@RequestBody Pedido pedidoVO) {		
		pedidoService.criarPedido(pedidoVO);		
	}
	
	@GetMapping(path = "listarPedidoPorUF/{uf}")
	public @ResponseBody List<Pedido> listarPedidoPorUF(@PathVariable("uf") String uf) {
		return pedidoService.listarPedidoPorUF(uf);
	}
	
	@GetMapping(path = "listarPedidoPorId/{id}")
	public Pedido listarPedidoPorId(@PathVariable("id") Integer id) {
		return pedidoService.listarPedidoPorId(id);
	}

}
