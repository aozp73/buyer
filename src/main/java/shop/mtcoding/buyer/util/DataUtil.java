package shop.mtcoding.buyer.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtil {

    public static String format(Timestamp stamp) {
        LocalDateTime nowTime = stamp.toLocalDateTime();
        String nowStr = nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return nowStr;
    }

}
