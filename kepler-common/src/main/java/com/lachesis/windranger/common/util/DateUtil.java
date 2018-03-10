/**
 * Project Name: windranger-common
 * File Name: DateUtil.java
 * Package Name: com.lachesis.windranger.common.util
 * Date: 2017年4月13日上午9:54:40
 * Copyright (c) 2017, Shenzhen Lachesis Mhealth Co., Ltd All Rights Reserved.
 *
 */
package com.lachesis.windranger.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName: DateUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月13日 上午9:54:40 <br/>
 * @author 胡诗蔚
 * @version
 * @since JDK 1.7.0.45
 * @see
 */
public class DateUtil {
	
	/**
	 * 获取当前日 前n天的日期
	 * @param date
	 * @param skip
	 * @return
	 */
	public static String getPreDay(String date,int skip){
		SimpleDateFormat format=new SimpleDateFormat("yyy-MM-dd");
		String preDay = null;
		try {
			Date parseDate= format.parse(date);
			Calendar ca=Calendar.getInstance();
			ca.setTime(parseDate);
			ca.add(Calendar.DATE, skip);
			Date wantTime=ca.getTime();
			preDay=format.format(wantTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return preDay;
	}

	public enum DateFormat {
		YMDHMSS("yyyy-MM-dd HH:mm:ss:sss"), YMD("yyyy-MM-dd"), // e.g.
																// 2012-11-01
		FULL("yyyy-MM-dd HH:mm:ss"), // e.g. 2012-11-01 23:12:15(24小时制)
		YMDHM("yyyy-MM-dd HH:mm"), // e.g. 2012-11-01 23:12(24小时制)
		HMS("HH:mm:ss"), HM("HH:mm"),YMDC("yyyy年MM月dd日"),
		MS("mm:ss");//分秒

		private String format;

		private DateFormat(String format) {
			this.format = format;
		}

		public String getFormatName() {
			return format;
		}
	}
	
	
	public static Date getDateAfter(Date d, int day) {  
		Calendar now = Calendar.getInstance();  
		now.setTime(d);  
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
		return now.getTime();  
	}
	
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();  
		now.setTime(d);  
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);  
		return now.getTime();  
	}
	
	public static Date getDateAfterHour(Date d, int hour) { 
		d = getDateAfter(d, hour/24);
		Calendar now = Calendar.getInstance();  
		now.setTime(d); 
		now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) + hour % 24);  
		return now.getTime();  
	}
	
	public static Date getDateBeforeHour(Date d, int hour) { 
		d = getDateBefore(d, hour/24);
		Calendar now = Calendar.getInstance();  
		now.setTime(d); 
		now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) - hour % 24);  
		return now.getTime();  
	}

	public static Date getZeroClockOfDate(Date d)  {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(d);

		try {
			
			return sdf.parse(s);
			
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		} 
	}
	
	public static String YMD = "yyyy-MM-dd";
	public static String YM = "yyyy-MM";
	public static String YMDHFM = "yyyy-MM-dd HH:mm:ss";
	public static String YYMMDDHHMM = "yyMMddHHmm";
	public static String MDHF="MM-dd HH:mm";
	public static String HF="HH:mm";
	
	/**
	 * 日期转换
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date,String format){
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	/**
	 * 日期
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseDate(String date,String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date out = null;
		try {
			out = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return out;
	}
	/**
	 * 
	 * @Title: datetimeDiff 
	 * @Description: 记录起始时间和结束时间的差值 
	 * @param start
	 * @param end
	 * @return int    差值 以秒为单位 
	 * @throws
	 */
	public static int datetimeDiff(Date start, Date end) {
		long res = (end.getTime() - start.getTime()) / 1000;
		return Integer.parseInt(String.valueOf(res));
	}


	public static int daysOfTwo(Date fDate, Date oDate) {

		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(fDate);

		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

		aCalendar.setTime(oDate);

		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

		return day2 - day1;

	}
	
	/**
	 * 日期校验
	 * 校验日期是否属于合法的日期格式
	 * @param date
	 * @param format
	 * @return
	 */
	public static boolean validDate(String date,String format){
		SimpleDateFormat df = new SimpleDateFormat(format);
		boolean valid = true;
		try {
			df.parse(date);
		} catch (ParseException e) {
			valid = false;
		}
		return valid;
	}
	
	public enum Menu{
		week1("周一",1),
		week2("周二",2),
		week3("周三",3),
		week4("周四",4),
		week5("周五",5),
		week6("周六",6),
		week7("周日",7);
		private String name;
		private int index;
		
		Menu(String name,int index){
			this.name = name;
			this.index = index;
		}
		
		// 普通方法
        public static String getName(int index) {
            for (Menu c : Menu.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }


		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}
	
	
	public static int daysBetween(Date smallDate, Date bigDate)  {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			smallDate = sdf.parse(sdf.format(smallDate));
			bigDate = sdf.parse(sdf.format(bigDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(smallDate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(bigDate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public static int getWeekOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return week;
	}
}
