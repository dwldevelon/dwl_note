package dwl.WebMvcConfigurer.cloud.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author wenlong.ding
 * @date 2021/4/15 17:31
 */
@Component
@EnableBinding(Processor.class)
public class MyTest {
    @Autowired
    Processor processor;

    public void sendMsg(String msg) {
        boolean send = processor.output().send(MessageBuilder.withPayload(msg).build());
        System.out.println("消息发送结果:"+send);
    }

}
