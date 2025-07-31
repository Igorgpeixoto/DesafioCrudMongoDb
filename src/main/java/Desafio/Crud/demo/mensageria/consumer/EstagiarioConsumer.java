package Desafio.Crud.demo.mensageria.consumer;

import Desafio.Crud.demo.dto.EstagiarioDTO;
import Desafio.Crud.demo.mensageria.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstagiarioConsumer {
    @RabbitListener(queues = RabbitMQConfig.ESTAGIARIO_QUEUE)
    public void consumir(EstagiarioDTO dto) {
        System.out.println("📨 Estagiário recebido: " + dto.getMatricula());
    }
}
