package Desafio.Crud.demo.mensageria.producer;

import Desafio.Crud.demo.dto.PessoaDTO;
import Desafio.Crud.demo.mensageria.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PessoaProducer {
    private final RabbitTemplate rabbitTemplate;

    public PessoaProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviar(PessoaDTO dto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "pessoa.novo", dto);
    }
}
