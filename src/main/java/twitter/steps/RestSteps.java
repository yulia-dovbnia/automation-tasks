package twitter.steps;


import twitter.webservices.ResponseWrapper;
import twitter.webservices.TwitterRestClient;

public class RestSteps {
    private TwitterRestClient twitterRestClient = new TwitterRestClient();


    public ResponseWrapper sentGetRequest() {
        return twitterRestClient.sentGetRequest();
    }

    public ResponseWrapper sentUpdateRequest(String textToPost) {
        return twitterRestClient.sentUpdateRequest("status", textToPost);
    }

    public ResponseWrapper sentDestroyRequest(String id){
        return twitterRestClient.sentDestroyRequest(id);
    }

}
