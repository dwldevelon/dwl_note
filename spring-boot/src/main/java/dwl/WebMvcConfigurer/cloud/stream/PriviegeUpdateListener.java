package dwl.WebMvcConfigurer.cloud.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Project: bot-speech
 * Package: com.msxf.bot.speech.service.kafka
 *
 * @author : ruifang.ran@msxf.com
 * @date : 2019/7/16 17:22
 */
@EnableBinding(value = {Sink.class})
@Slf4j
public class PriviegeUpdateListener {
//    @Autowired
//    private ITenantService tenantService;

    @StreamListener(Sink.INPUT)
    private void receive(Object object) {
        //呼叫中心初始化数据
        log.info("接收到权限变更消息，租户ID={}",object);
        System.out.println("===============");
    }
}
