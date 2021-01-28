package cn.monster.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-dead-letter-rabbitmq.xml")
public class RabbitMQDeadLetterTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Test
    public void topicQueueTtlTest() throws InterruptedException {
        for (int i = 0; i < 10 ; i++) {
            rabbitTemplate.convertAndSend("spring_topic_exchange_dead_letter", "monster.aa", "topic dead letter ttl message " + i);
            Thread.sleep(3000);
        }

    }









}
