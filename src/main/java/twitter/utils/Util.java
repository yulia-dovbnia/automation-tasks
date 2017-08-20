package twitter.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Util {
    public static final String PATTERN = "EEE MMM dd HH:mm yyyy";

    public static long getTimeStamp(String version) {
        long targetValue;
        if (version.equals("old")) {
            targetValue = ScenarioSession.getTimestamp();
        } else {
            long timeStamp = System.currentTimeMillis();
            ScenarioSession.setTimestamp(timeStamp);
            targetValue = timeStamp;
        }
        return targetValue;
    }


    public static String getStringDateFromTimeStamp(long timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date(timeStamp);
        return sdf.format(date);
    }


    public static <T extends Object> T getLast(List<T> list) {
        return list.get(list.size() - 1);
    }
}
