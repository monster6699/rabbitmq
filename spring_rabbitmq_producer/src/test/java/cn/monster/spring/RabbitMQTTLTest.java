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
@ContextConfiguration(locations = "classpath:spring-ttl-rabbitmq.xml")
public class RabbitMQTTLTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void topicQueueTest() {
        rabbitTemplate.convertAndSend("spring_topic_exchange_ttl", "monster.zero", "topic monster.zero message", new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000"); //发送单个过期消息 注意：当该消息在头部时才会判断这个消息是否过期
                return message;
            }
        });
    }

    @Test
    public void topicQueueTtlTest() {
        rabbitTemplate.convertAndSend("spring_topic_exchange_ttl", "monster.aa", "topic queue ttl message");
    }









}
