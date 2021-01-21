package cn.monster.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class StartConsumer1 {
    private  static String EXCHANGE_NAME = "routing_exchange";
    private static String QUEUE_NAME_1 = "routing_queue_1";
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接工厂
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

        //5.声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

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


        //7. 队列绑定交换机
        /**
         * queueBind(String queue, String exchange, String routingKey)
         */
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, "error");


        DefaultConsumer consumer = new DefaultConsumer(channel){
            /**
             *
             * @param consumerTag 标识
             * @param envelope 获取一些信息， 交换机，路由key
             * @param properties 配置信息
             * @param body 消费的信息
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("交换机为：" + envelope.getExchange());
                System.out.println("error msg body: " + new String(body));
            }
        };


        // String basicConsume(String queue, Consumer callback)
        /*
        queue: 队列名称
         */
        channel.basicConsume(QUEUE_NAME_1, consumer);



    }
}
