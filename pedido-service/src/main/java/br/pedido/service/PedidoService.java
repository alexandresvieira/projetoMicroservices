package br.pedido.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.pedido.model.Pedido;
import br.pedido.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public void criarPedido(Pedido pedido) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		LocalDate localDate = LocalDate.parse(sdf.format(pedido.getDataEntrega()));		
		Integer sla =  consultaPrazoEntrega(pedido.getUf());		
		localDate.plusDays(sla);
				
		pedido.setDataEntrega(new Date(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()));

		pedidoRepository.save(pedido);
		
	}
	
	private Integer consultaPrazoEntrega(String uf) {		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("http://localhost:8085/entrega-service/entrega/consultarPrazoEntrega/", Integer.class, uf);
	}

	public List<Pedido> listarPedidoPorUF(String uf) {
		return pedidoRepository.findByUf(uf);
	}

	public Pedido listarPedidoPorId(Integer id) {
		Optional<Pedido> pedioOptional = pedidoRepository.findById(id);
		return pedioOptional.orElse(null);  
	}

}
