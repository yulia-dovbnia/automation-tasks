package twitter.utils;

import twitter.webservices.ResponseWrapper;
import twitter.webservices.TwitterResponse;

import java.util.ArrayList;
import java.util.List;


public class ScenarioSession {

    private static long timestamp;
    private static List<TwitterResponse> twitterResponseList = new ArrayList<>();
    private static ResponseWrapper responseWrapper;
    private static List<String> listOfPostedText = new ArrayList<>();
    private static String lastID;
    private static String lastDate;

    public static String getLastID() {
        return lastID;
    }

    public static void setLastID(String lastID) {
        ScenarioSession.lastID = lastID;
    }

    public static void setTwitterResponseList(List<TwitterResponse> twitterResponseList) {
        ScenarioSession.twitterResponseList = twitterResponseList;
    }

    public static List<TwitterResponse> getTwitterResponseList() {
        return twitterResponseList;

    }

    public static ResponseWrapper getResponseWrapper() {
        return responseWrapper;
    }

    public static void setResponseWrapper(ResponseWrapper responseWrapper) {
        ScenarioSession.responseWrapper = responseWrapper;
    }

    public static long getTimestamp() {
        return timestamp;
    }


    public static void setTimestamp(long timestamp) {
        ScenarioSession.timestamp = timestamp;
    }


    public static List<String> getListOfPostedText() {
        return listOfPostedText;
    }

    public static void setLastDate(String lastDate) {
        ScenarioSession.lastDate = lastDate;
    }

    public static String getLastDate() {
        return lastDate;
    }
}
