import dwl.DwlTestMainApplication;
import dwl.test.DwlMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author wenlong.ding
 * @date 2020/12/17 13:52
 */
@SpringBootTest(classes = DwlTestMainApplication.class)
public class MyTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    public void test(){
        System.out.println(webApplicationContext);
        DwlMock.of(webApplicationContext).test("/test1","ding");
    }
}
