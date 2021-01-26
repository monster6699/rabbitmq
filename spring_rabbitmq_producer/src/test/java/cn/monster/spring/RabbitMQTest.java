package cn.monster.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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


    @Test
    public void confirmQueueTest() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack) {
                    System.out.println("发送成功");
                } else {
                    System.out.println("发送失败: " + cause);
                }
            }
        });
        rabbitTemplate.convertAndSend("confirm_spring_exchange", "monster.confirm.test", "消息可靠传递confirm");
    }


    @Test
    public void returnQueueTest() {
        //设置交换机失败处理模式
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             *
             * @param message 发送的消息对象
             * @param replyCode 错误码
             * @param replyText 错误信息
             * @param exchange 交换机
             * @param routingKey 路由key
             */
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("发送的消息对象: " + new String(message.getBody()));
                System.out.println("replyCode: " + replyCode);
                System.out.println("replyText: " + replyText);
                System.out.println("exchange: " + exchange);
                System.out.println("routingKey: " + routingKey);
            }
        });
        rabbitTemplate.convertAndSend("return_spring_exchange", "aaa", "消息可靠传递return");
    }








}
