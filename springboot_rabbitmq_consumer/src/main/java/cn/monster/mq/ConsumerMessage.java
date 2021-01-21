package cn.monster.mq;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMessage {

    @RabbitListener(queues = "springboot_queue")
    public void listener(String message) {
        System.out.println("消费者消费消息: " + message);
    }
}
