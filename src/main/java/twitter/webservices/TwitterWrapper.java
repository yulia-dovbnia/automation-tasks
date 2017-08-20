package twitter.webservices;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class TwitterWrapper {

    public static TwitterResponse getTwitterResponse(ResponseWrapper responseWrapper) {
        return new Gson().fromJson(responseWrapper.getBody(), TwitterResponse.class);
    }

    public static List<TwitterResponse> getTwitterResponseList(ResponseWrapper responseWrapper) {
        return new Gson().fromJson(responseWrapper.getBody(), new TypeToken<List<TwitterResponse>>() {
        }.getType());
    }


}
