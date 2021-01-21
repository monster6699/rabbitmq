package cn.monster.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class StartProducer {
    private static String EXCHANGE_NAME = "topic_exchange";
    private static String QUEUE_NAME_1 = "topic_queue_1";
    private static String QUEUE_NAME_2 = "topic_queue_2";

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

        //5.创建交换机
        /**
         * public DeclareOk exchangeDeclare(String exchange, BuiltinExchangeType type, boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments)
         * exchange  交换机名称
         * type  交换机类型 fanout、topic、direct、headers
         * durable
         * autoDelete
         * internal
         * arguments
         */
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);


        //6.创建队列Queue
        /**
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
         * queue 队列名称
         * durable 是否持久化，重启后信息存在
         * exclusive 是否独占端口
         * autoDelete 是否自动删除，没有consumer时删除
         * arguments 参数
         */
        channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);


        //7.队列绑定交换机
//        /**
//         *  queueBind(String queue, String exchange, String routingKey)
//         *  queue 队列名称
//         *  exchange 交换机名称
//         *  routingKey 路由key
//         */
//        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, "item.info");
//        channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, "info");

        //6. 发送消息
        //basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        /**
         * exchange 交换机名称, 简单模式下交换机使用默认: ""
         * routingKey 路由器名称
         * BasicProperties 配置信息
         * body 发送消息数据
         */

        channel.basicPublish(EXCHANGE_NAME, "item.info", null, "item.info message".getBytes());
        channel.basicPublish(EXCHANGE_NAME, "item.error", null, "item.error message".getBytes());
        channel.basicPublish(EXCHANGE_NAME, "item.info.message", null, "item.info.message message".getBytes());
        channel.basicPublish(EXCHANGE_NAME, "item.error.message", null, "item.error.message message".getBytes());



        channel.close();
        connection.close();
    }
}
