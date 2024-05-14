package com.demobackendapi.backend.consumer;

import com.demobackendapi.backend.facade.PagamentoFacade;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class PagamentoResponseSuccessConsumidor {

    @Autowired private PagamentoFacade pagamentoFacade;

    @RabbitListener(queues = { "pagamento-response-success-queue"})
    public void receiver(@Payload Message messagem) {
        System.out.println(messagem);
        String payLoad = String.valueOf(messagem.getPayload());

        pagamentoFacade.successPagamento(payLoad);
    }
}
