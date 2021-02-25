package dwl.jsoncomponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @author wenlong.ding
 * @date 2021/2/1 16:22
 */
@JsonComponent
public class JsonUserConfig {
    public static class JsonUserSerialize extends JsonSerializer<JsonUser> {
        @Override
        public void serialize(JsonUser jsonUser, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("abc", "ding");
            jsonGenerator.writeEndObject();
        }
    }

    public static class JsonUserDeSerialize extends JsonDeserializer<JsonUser>{
        @Override
        public JsonUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
            TextNode favoriteColor = (TextNode) treeNode.get(
                    "favoriteColor");
            return new JsonUser("lucy",17);
        }
    }

}
