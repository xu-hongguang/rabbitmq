package com.xhg.rabbitmq.rabbitmq;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Console;
import com.xhg.rabbitmq.config.MQConfig;
import com.xhg.rabbitmq.pojo.Information;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.GregorianCalendar;

@Component
public class RabbitTemplateSender {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitTemplateSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendString() {
        String value = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        Console.log("send message {}", value);
        rabbitTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", value);
    }

    public void sendInf() throws Exception {
        Information information = new Information(3, "we12", "好消息", new GregorianCalendar(2018, 11 - 1, 1).getTime());
        byte[] bytes = getBytesFromObject(information);
        rabbitTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE,"",bytes);
    }


    /**
     * 发送对象
     *
     * @throws Exception
     */
    public void sendObject() throws Exception {
        Information information = new Information(2, "we12", "好消息", new GregorianCalendar(2018, 11 - 1, 1).getTime());
        byte[] bytes = getBytesFromObject(information);
        Console.log("send message {}", bytes);
        rabbitTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE2, MQConfig.ROUTINE_KEY_3, bytes);
    }

    //对象转化为字节码
    public byte[] getBytesFromObject(Serializable obj) throws Exception {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        return bo.toByteArray();
    }
}

