package dwl.httpmessage;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wenlong.ding
 * @date 2021/2/1 11:25
 */
@Component
public class MyHttpMessageConverter extends AbstractHttpMessageConverter<HttpMessageUserInfo> {

    public MyHttpMessageConverter() {
        super(MediaType.ALL);
//        super(new MediaType("application", "xxx-junlin", Charset.forName("UTF-8")));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return HttpMessageUserInfo.class.isAssignableFrom(clazz);
    }

    @Override
    protected HttpMessageUserInfo readInternal(Class<? extends HttpMessageUserInfo> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        HttpMessageUserInfo httpMessageUserInfo = new HttpMessageUserInfo();
        httpMessageUserInfo.setUsername("lucy");
        httpMessageUserInfo.setAge(17);
        return httpMessageUserInfo;
    }

    @Override
    protected void writeInternal(HttpMessageUserInfo messageUserInfo, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

}
