package cn.monster.mq;

import cn.monster.config.RabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProducerStart {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void producerMessage(){
        rabbitTemplate.convertAndSend(RabbitMQConfig.SPRINGBOOT_TOPIC_EXCHANGE, "monster.mq", "数据message请查收");
    }
}
