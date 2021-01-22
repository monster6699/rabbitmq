package cn.monster.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    //交换机名称
    public static final String SPRINGBOOT_TOPIC_EXCHANGE = "springboot_topic_exchange";
    //队列名称
    private static final String SPRINGBOOT_QUEUE = "springboot_queue";


    @Bean("topicExchange")
    public Exchange topicExchange() {
        System.out.println("执行到交换机。。。。。。。。。。。。。。。。。。。。。");
        return  ExchangeBuilder.topicExchange(SPRINGBOOT_TOPIC_EXCHANGE).durable(true).build();
    }

    @Bean("topicQueue")
    public Queue topicQueue() {
        return new Queue(SPRINGBOOT_QUEUE, true, false, false);
    }


    @Bean
    public Binding topicExchangeBindQueue(@Qualifier("topicExchange") Exchange exchange, @Qualifier("topicQueue") Queue queue) {
        System.out.println("绑定成功。。。。。。。。。。。。。。。。。。。。");
        return BindingBuilder.bind(queue).to(exchange).with("monster.#").noargs();
    }

}
