package com.demobackendapi.backend.producer;

import com.demobackendapi.backend.dto.PagamentoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoRequestProducer  {

    @Autowired private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void integrar(PagamentoDto pagamento) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "pagamento-request-exchange",
                "pagamento-request-rout-key",
                objectMapper.writeValueAsString(pagamento)
        );
    }
}
