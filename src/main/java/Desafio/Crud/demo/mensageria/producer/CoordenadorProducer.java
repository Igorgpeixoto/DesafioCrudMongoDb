package Desafio.Crud.demo.mensageria.producer;

import Desafio.Crud.demo.dto.CoordenadorDTO;
import Desafio.Crud.demo.mensageria.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CoordenadorProducer {
    private final RabbitTemplate rabbitTemplate;

    public CoordenadorProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviar(CoordenadorDTO dto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "coordenador.novo", dto);
    }
}
