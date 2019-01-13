package com.xhg.rabbitmq;

import com.xhg.rabbitmq.amqp.MQSender;
import com.xhg.rabbitmq.rabbitmq.RabbitTemplateReceiver;
import com.xhg.rabbitmq.rabbitmq.RabbitTemplateSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    private MQSender mqSender;

    @Autowired
    private RabbitTemplateSender rabbitTemplateSender;

    @Test
    public void testMQRabbit() {
        mqSender.sendDirect();
        mqSender.sendTopic();
        mqSender.sendFanout();
        mqSender.sendHeaders();
    }

    @Test
    public void testMQRabbitTe() throws Exception {
//		rabbitTemplateSender.sendString();

//        发送对象
        rabbitTemplateSender.sendObject();
    }

    @Test
    public void testRabbitTe() throws Exception {

        rabbitTemplateSender.sendInf();

    }


}
