package Desafio.Crud.demo.mensageria.consumer;

import Desafio.Crud.demo.dto.CoordenadorDTO;
import Desafio.Crud.demo.mensageria.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CoordenadorConsumer {
    @RabbitListener(queues = RabbitMQConfig.COORDENADOR_QUEUE)
    public void consumir(CoordenadorDTO dto) {
        System.out.println("📨 Coordenador recebido: " + dto.getMatricula());
    }
}
