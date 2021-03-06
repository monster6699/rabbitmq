package cn.monster.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

public class TopicListenerWell2 implements MessageListener {
    public void onMessage(Message message) {
        try {
            String s = new String(message.getBody(), "utf-8");
            System.out.printf("topicWell2, 接收路由名称为：%s，路由键为：%s，队列名为：%s的消息：%s \n",
                    message.getMessageProperties().getReceivedExchange(),
                    message.getMessageProperties().getReceivedRoutingKey(),
                    message.getMessageProperties().getConsumerQueue(),
                    s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
