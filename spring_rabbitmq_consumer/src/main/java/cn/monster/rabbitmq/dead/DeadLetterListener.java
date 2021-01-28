package cn.monster.rabbitmq.dead;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadLetterListener implements MessageListener {
    public void onMessage(Message message) {
        System.out.println(new String(message.getBody()));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        System.out.println();
    }
}
