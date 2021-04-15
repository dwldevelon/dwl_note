package dwl.old.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dwl.old.httpmessage.HttpMessageUserInfo;
import dwl.old.jsoncomponent.JsonUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wenlong.ding
 * @date 2021/2/1 11:32
 */
@RestController
public class TestController {
    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/a")
    public Object a(HttpMessageUserInfo messageUserInfo) {
        System.out.println(messageUserInfo);
        return "success";
    }

    @GetMapping("/b")
    public Object b() throws Exception {
        JsonUser jsonUser = new JsonUser("jerry",20);
        String s = objectMapper.writeValueAsString(jsonUser);
        System.out.println(s);
        System.out.println("---");
        JsonUser jsonUser1 = objectMapper.readValue("{}", JsonUser.class);
        System.out.println(jsonUser1);
        return "success";
    }
}
