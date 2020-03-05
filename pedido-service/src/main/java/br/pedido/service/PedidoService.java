package br.pedido.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.pedido.model.Pedido;
import br.pedido.model.Sla;
import br.pedido.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;	
	
	public void criarPedido(Pedido pedido) {
		pedidoRepository.save(calculaNovaDataEntrega(pedido));		
	}

	public List<Pedido> listarPedidoPorUF(String uf) {
		return pedidoRepository.findByUf(uf.toUpperCase());
	}

	public Pedido listarPedidoPorId(Integer id) {
		Optional<Pedido> pedioOptional = pedidoRepository.findById(id);
		return pedioOptional.orElse(new Pedido());  
	}
	
	@KafkaListener(topics = "${cloudkarafka.topic}")
    public void processMessage(String message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), message);        
        String uf = message;        
        atualizaSlaPedidos(consultaPrazoEntrega(uf));        
    }
	
	private void atualizaSlaPedidos(Sla sla) {
		List<Pedido> listaPedido = pedidoRepository.findByUf(sla.getUf());		
		listaPedido.stream()
			.map(pedido -> calculaNovaDataEntrega(pedido))
			.collect(Collectors.toList())
			.forEach(pedido -> pedidoRepository.save(pedido));
	}
	
	private Sla consultaPrazoEntrega(String uf) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("http://localhost:8080/entrega-service/entrega/consultarPrazoEntrega/"+uf, Sla.class);
	}
	
	private Pedido calculaNovaDataEntrega(Pedido pedido) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		LocalDate localDate = LocalDate.parse(sdf.format(pedido.getDataEntrega()));		
		Sla sla =  consultaPrazoEntrega(pedido.getUf());		
		localDate = localDate.plusDays(sla.getSla());
		ZoneId defaultZoneId = ZoneId.systemDefault();
		pedido.setDataEntrega(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));		
		return pedido;
	}

}
