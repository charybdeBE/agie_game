package be.ac.ulg.montefiore.group03.agilegame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by charybde on 14.03.16.
 */
public class DateUtil {

    public static int getDay(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        return Integer.parseInt(sdf.format(d));

    }
    public static int getMonth(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("M");
        return Integer.parseInt(sdf.format(d));
    }
    public static int getYear(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("y");
        return Integer.parseInt(sdf.format(d));
    }
    public static Date dateFromString(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date ret = new Date();
        try {
            ret = sdf.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static Date getFirstDayOfMonth(Date d){
        return DateUtil.dateFromString("1."+DateUtil.getMonth(d)+"."+DateUtil.getYear(d), "d.M.y");
    }
}
