package com.demobackendapi.backend.facade;

import com.demobackendapi.backend.dto.PagamentoDto;
import com.demobackendapi.backend.producer.PagamentoRequestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoFacade {

    @Autowired private PagamentoRequestProducer pagamentoRequestProducer;

    public String solicitarPagamento(PagamentoDto request) {
        try{
            pagamentoRequestProducer.integrar(request);
        } catch(Exception e) {
            return "Ocorreu um erro ao tentar integrar..." + e.getMessage();
        }
        return "Pagamento aguardando confirmação!";
    }

    public void successPagamento(String payLoad) {
        System.out.println("RESPOSTA SUCCESS...." + payLoad);
    }

    public void erroPagamento(String payLoad) {
        System.out.println("RESPOSTA ERRO...." + payLoad);
    }
}
