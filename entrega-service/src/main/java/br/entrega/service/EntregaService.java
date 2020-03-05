package br.entrega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.entrega.model.Sla;
import br.entrega.repository.EntregaRepository;

@Service
public class EntregaService {
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Value("${cloudkarafka.topic}")
	private String topic;
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

	public void alterarPrazoEntrega(String uf, Integer sla) {		
		entregaRepository.save(new Sla(uf, sla));		
		send(uf);
	}

	public Sla consultarPrazoEntrega(String uf) {		
		return entregaRepository.findFirstByUfOrderByIdDesc(uf);
	}
	
	private void send(String message){
		System.out.println(String.format("sending message='{%s}' to topic='{%s}'", message, topic));       
        kafkaTemplate.send(topic, message);
    }

}
