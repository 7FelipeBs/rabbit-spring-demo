package com.demopagamentosworker.backend.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoErrorProdutor {

    @Autowired private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void gerarResposta(String mensagem)  throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "pagamento-response-error-exchange",
                "pagamento-response-error-rout-key",
                objectMapper.writeValueAsString(mensagem)
        );
    }
}
