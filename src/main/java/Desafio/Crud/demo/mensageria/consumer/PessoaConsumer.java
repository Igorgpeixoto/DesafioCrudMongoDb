package Desafio.Crud.demo.mensageria.consumer;

import Desafio.Crud.demo.dto.PessoaDTO;
import Desafio.Crud.demo.mensageria.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PessoaConsumer {
    @RabbitListener(queues = RabbitMQConfig.PESSOA_QUEUE)
    public void consumir(PessoaDTO dto) {
        System.out.println(" Pessoa recebida: " + dto.getCpf());
    }
}
