package Desafio.Crud.demo.mensageria.producer;

import Desafio.Crud.demo.dto.EstagiarioDTO;
import Desafio.Crud.demo.mensageria.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EstagiarioProducer {
    private final RabbitTemplate rabbitTemplate;

    public EstagiarioProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviar(EstagiarioDTO dto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "estagiario.novo", dto);
    }
}
