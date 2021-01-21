package cn.monster.workQueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class StartProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //2.设置参数
        connectionFactory.setHost("39.106.78.168");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/monster"); //虚拟机
        connectionFactory.setUsername("monster");
        connectionFactory.setPassword("zaq1xsw2");

        //3.创建连接connection
        Connection connection = connectionFactory.newConnection();

        //4.创建channel
        Channel channel = connection.createChannel();

        //5.创建队列Queue
        // queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        /**
         * queue 队列名称
         * durable 是否持久化，重启后信息存在
         * exclusive 是否独占端口
         * autoDelete 是否自动删除，没有consumer时删除
         * arguments 参数
         */
        channel.queueDeclare("workQueues", true, false, false, null);

        //6. 发送消息
        //basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        /**
         * exchange 交换机名称, 简单模式下交换机使用默认: ""
         * routingKey 路由器名称
         * BasicProperties 配置信息
         * body 发送消息数据
         */
        for (int i = 0; i < 10; i++) {
            String body = i + "hello_workQueues";
            channel.basicPublish("", "workQueues", null, body.getBytes());
        }



        channel.close();
        connection.close();
    }
}
