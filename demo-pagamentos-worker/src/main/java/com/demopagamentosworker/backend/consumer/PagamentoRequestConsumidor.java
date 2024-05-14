package com.demopagamentosworker.backend.consumer;

import com.demopagamentosworker.backend.producer.PagamentoErrorProdutor;
import com.demopagamentosworker.backend.producer.PagamentoSuccessProdutor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PagamentoRequestConsumidor {

    @Autowired private PagamentoErrorProdutor erroProdutor;
    @Autowired private PagamentoSuccessProdutor successProdutor;

    @RabbitListener(queues = { "pagamento-request-queue"})
    public void receberMensagem(@Payload Message messagem) {
        System.out.println(messagem);
       try {
           boolean b = new Random().nextBoolean();
           if(b) {
               successProdutor.gerarResposta("Mensagem de Sucesso Pagamento!" + messagem);
           } else {
               erroProdutor.gerarResposta("Erro no Pagamento " + messagem);
           }
       } catch(Exception e) {
           System.out.println("Ocorreu um erro ao receber mensagem..." + e.getMessage());
       }
    }
}
