package cn.monster.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq.xml")
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void queueTest(){
        rabbitTemplate.convertAndSend("spring_queue", "spring_queue message");
    }


    @Test
    public void fanoutQueueTest() {
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "fanout 广播消息 message");
    }


    @Test
    public void topicQueueTest() {
        rabbitTemplate.convertAndSend("spring_topic_exchange", "monster.zero", "topic monster.zero message");
        rabbitTemplate.convertAndSend("spring_topic_exchange", "mq.zero", "fanout mq.zero message");
    }








}
