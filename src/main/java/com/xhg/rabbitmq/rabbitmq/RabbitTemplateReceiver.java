package com.xhg.rabbitmq.rabbitmq;

import com.xhg.rabbitmq.config.MQConfig;
import com.xhg.rabbitmq.pojo.Information;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

@Component
public class RabbitTemplateReceiver {
    private Logger logger = LoggerFactory.getLogger(RabbitTemplateReceiver.class);

    @RabbitListener(queues = MQConfig.FANOUT_QUEUE_2)
    public void receiveFanout2(String value) {
        logger.info("Fanout2 Receive : " + value);
    }


    /**
     * 接收对象
     * @param bytes
     * @throws Exception
     */
    @RabbitListener(queues = MQConfig.TOPIC_QUEUE_3)
    public void receiveTopic3(byte[] bytes) throws Exception{
        logger.info("Topic3 Receive : " + bytes);
        //字节码转化为对象
        Information information=(Information) getObjectFromBytes(bytes);
        System.out.println(information);
        System.out.println("messages ："+information.toString());
        System.out.println(Thread.currentThread().getName()+"接收到来自topic_queue_3队列的消息: "+information);
    }
    //字节码转化为对象
    public  Object getObjectFromBytes(byte[] objBytes) throws Exception {
        if (objBytes == null || objBytes.length == 0) {
            return null;
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }
}
