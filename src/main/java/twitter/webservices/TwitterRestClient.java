package twitter.webservices;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter.properties.Keys;
import twitter.properties.PropReader;

import java.util.ArrayList;

public class TwitterRestClient {
    Logger LOG = LoggerFactory.getLogger(this.getClass());
    private String get = "/home_timeline.json";
    private String destroy = "/destroy/%s.json";
    private String update = "/update.json";
    private String base = "https://api.twitter.com/1.1/statuses";
    private HttpClient httpClient = new DefaultHttpClient();


    private OAuthConsumer oAuthConsumer = null;

    public TwitterRestClient() {
        setUpConsumer();
    }

    private void setUpConsumer() {
        oAuthConsumer = new CommonsHttpOAuthConsumer(
                PropReader.getKeyProperty(Keys.CONSUMER_KEY),
                PropReader.getKeyProperty(Keys.CONSUMER_SECRET));
        oAuthConsumer.setTokenWithSecret(
                PropReader.getKeyProperty(Keys.ACCESS_TOKEN),
                PropReader.getKeyProperty(Keys.ACCESS_TOKEN_SECRET));
    }

    public ResponseWrapper sentGetRequest() {
        String URL = base + get;
        HttpGet httpGet = new HttpGet(URL);
        HttpResponse httpResponse = null;
        try {
            oAuthConsumer.sign(httpGet);
            LOG.info("Sending get request: {}", URL);
            httpResponse = httpClient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("Obtaining response: {}", httpResponse);
        return new ResponseWrapper(httpResponse);
    }

    public ResponseWrapper sentDestroyRequest(String id) {
        String URL = String.format(base + destroy, id);
        HttpPost httpPost = new HttpPost(URL);
        HttpResponse httpResponse = null;
        try {
            LOG.info("Sending destroy request: {}", URL);
            oAuthConsumer.sign(httpPost);
            httpResponse = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("Obtaining response: {}", httpResponse);
        return new ResponseWrapper(httpResponse);
    }


    public ResponseWrapper sentUpdateRequest(String field, String value) {
        String URL = base + update;

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair(field, value));

        HttpPost httpPost = new HttpPost(URL);
        HttpResponse httpResponse = null;
        try {
            LOG.info("Sending update request: {}", URL);
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            oAuthConsumer.sign(httpPost);
            httpResponse = httpClient.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseWrapper(httpResponse);
    }
}
