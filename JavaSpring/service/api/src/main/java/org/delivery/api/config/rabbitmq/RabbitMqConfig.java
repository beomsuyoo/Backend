package org.delivery.api.config.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // producer가 메시지를 보낼 exchange 생성
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("delivery.exchange");
    }


    // exchange가 보낸 메세지를 보관할 큐 생성
    @Bean
    public Queue queue(){
        return new Queue("delivery.queue");
    }

    /*
    exchange와 큐를 연결하는 작업
    매개변수는 ApplicationContext에 있는 빈 객체가 자동 주입됨
     */
    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("delivery.key");
    }

    // Producer가 exchange로 메시지를 보낼때 사용되는 template
    @Bean
    public RabbitTemplate rabbitTemplate(
            //ConnectionFactor는 yaml에서 관리
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ){
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    //RabbitTemplate 매개변수로 주입
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper){
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
