package dwl.WebMvcConfigurer.cloud.stream;

import dwl.WebMvcConfigurer.configurePathMatch.CommonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wenlong.ding
 * @date 2021/4/15 10:19
 */
@SpringBootApplication
@Slf4j
public class MainApplication extends CommonConfig {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream =  Thread.currentThread().getContextClassLoader().getResourceAsStream("dwl/WebMvcConfigurer/cloud/stream/ab.properties");
        Properties p = new Properties();
        p.load(resourceAsStream);
        p.forEach((k,v)->MAP.put(String.valueOf(k),v));

        wrapper(()->{
            MyTest myTest = CTX.getBean(MyTest.class);
            myTest.sendMsg("ding");

        });


    }

}


