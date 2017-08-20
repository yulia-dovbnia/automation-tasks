package twitter.webservices;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ResponseWrapper  {

    private int statusCode;
    private String body;
    private String bodyEncoded;
    private HttpResponse rawResponse;

    public ResponseWrapper(final HttpResponse httpResponse) {
        rawResponse = httpResponse;
        statusCode = httpResponse.getStatusLine().getStatusCode();
        try {
            bodyEncoded = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            body = new String(bodyEncoded.getBytes("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBody() {
        return body;
    }

    public String getBodyEncoded() {
        return bodyEncoded;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpResponse getRawResponse() {
        return rawResponse;
    }
}
