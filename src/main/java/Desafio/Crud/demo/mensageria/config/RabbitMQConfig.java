package Desafio.Crud.demo.mensageria.config;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "pessoa.exchange";
    public static final String PESSOA_QUEUE = "pessoa.queue";
    public static final String COORDENADOR_QUEUE = "coordenador.queue";
    public static final String ESTAGIARIO_QUEUE = "estagiario.queue";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue pessoaQueue() {
        return new Queue(PESSOA_QUEUE, true);
    }

    @Bean
    public Queue coordenadorQueue() {
        return new Queue(COORDENADOR_QUEUE, true);
    }

    @Bean
    public Queue estagiarioQueue() {
        return new Queue(ESTAGIARIO_QUEUE, true);
    }

    @Bean
    public Binding pessoaBinding() {
        return BindingBuilder.bind(pessoaQueue()).to(exchange()).with("pessoa.novo");
    }

    @Bean
    public Binding coordenadorBinding() {
        return BindingBuilder.bind(coordenadorQueue()).to(exchange()).with("coordenador.novo");
    }

    @Bean
    public Binding estagiarioBinding() {
        return BindingBuilder.bind(estagiarioQueue()).to(exchange()).with("estagiario.novo");
    }

    // 💬 Conversor para transformar objetos em JSON
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 📦 RabbitTemplate usando conversor JSON
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}

