package com.gmrxus.zhidouke.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Gmrxus on 2018/1/10.
 */

public class CalendarUtil {
  /**
   * 日期相减
   *
   * @param sDate
   * @param eDate
   * @return
   */
  public static int sub(String sDate, String eDate) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    try {
      long sParse = format.parse(sDate).getTime();
      long eParse = format.parse(eDate).getTime();
      return (int) ((eParse - sParse) / (1000 * 3600 * 24));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * 日期增加天数
   */
  public static String add(String date, int day) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    try {
      long time = format.parse(date).getTime();
      long l = day * 1000 * 3600 * 24;
      return format.format((time - l) / 1000 / 3600 / 24);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
