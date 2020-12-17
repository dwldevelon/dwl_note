package dwl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenlong.ding
 * @date 2020/12/17 10:05
 */
@SpringBootApplication
@RestController
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

    @PostMapping("/test1")
    public Object test1(@RequestBody String abc){
        return "sss" + abc;
    }
}
