package cn.monster.rabbitmq.ackListener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

public class AckConsumer {

//    public void onMessage(Message message, Channel channel) throws Exception {
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//
//        try {
//            System.out.println(new String(message.getBody()));
//            /**
//             * @param deliveryTag the tag from the received; 发送者的tag
//             * @param multiple true to acknowledge all messages 是否接受多条信息系
//             */
//            channel.basicAck(deliveryTag, true);
//
//        }catch (Exception e){
//            System.out.println(e);
//            /**
//             * @param requeue true if the rejected message(s) should be requeued rather
//             *                是否重回队列当中
//             */
//            channel.basicNack(deliveryTag, true, true);
//        }
//    }
}
