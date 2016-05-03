package be.ac.ulg.montefiore.group03.agilegame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by charybde on 14.03.16.
 */
public class DateUtil {

    /**
     * get the day corresponding to a date
     * @param d the date
     * @return the day id (integer)
     */
    public static int getDay(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        return Integer.parseInt(sdf.format(d));

    }

    /**
     * get the month corresponding to a date
     * @param d the date
     * @return the id of the month (between TODO and TODO)
     */
    public static int getMonth(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("M");
        return Integer.parseInt(sdf.format(d));
    }

    /**
     * Get the year corresponding to a date
     * @param d the date
     * @return the year
     */
    public static int getYear(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("y");
        return Integer.parseInt(sdf.format(d));
    }

    /**
     * Parse a date from a string
     * @param date the date in string format
     * @param pattern the pattern respected by date
     * @return the date (object)
     */
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

    /**
     * Transform a date instance to a date in string format
     * @param date the date (Date object)
     * @param pattern (the pattern in wich the date must be output
     * @return the date in a string in the format given by pattern
     */
    public static String dateToString(Date date, String pattern){
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return  df.format(date);
    }

    /**
     * Get the first date of the month
     * @param d the date
     * @return the first day in a date object
     * TODO: verify this description
     */
    public static Date getFirstDayOfMonth(Date d){
        return DateUtil.dateFromString("1."+DateUtil.getMonth(d)+"."+DateUtil.getYear(d), "d.M.y");
    }
}
