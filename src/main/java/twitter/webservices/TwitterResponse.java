package twitter.webservices;

import lombok.Data;

import java.lang.reflect.Field;
import java.security.InvalidParameterException;

@Data
public class TwitterResponse {

    private String created_at;
    private String retweet_count;
    private String text;
    private String id;

    public String getFieldValue(String name) {
        String value = null;
        try {
            Field field = null;
            field = this.getClass().getDeclaredField(name);
            field.setAccessible(true);
            value = (String) field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidParameterException();
        }
        return value;
    }

}
