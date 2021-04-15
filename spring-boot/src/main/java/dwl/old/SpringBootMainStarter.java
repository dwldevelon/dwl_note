package dwl.old;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wenlong.ding
 * @date 2021/1/26 15:59
 */
@SpringBootApplication
public class SpringBootMainStarter {
    public static void main(String[] args) {
//        SpringApplication springApplication = new SpringApplication();
//        springApplication.addListeners();   nack message requeue true Fanout
        SpringApplication
                .run(SpringBootMainStarter.class,args);
    }


}
