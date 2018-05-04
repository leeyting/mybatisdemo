package com.mybatis.demo.base.utils;


import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	public static String defaultFormat = "yyyy-MM-dd HH:mm:ss";
	public static String formatYMD = "yyyy-MM-dd";
	private static DateTimeFormatter format = DateTimeFormat.forPattern(defaultFormat);

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static String getStrNow() {
		DateTime dt = new DateTime();
		return dt.toString(defaultFormat);
	}

	public static String getStrNow(String pattern){
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(new Date());
	}

	public static Date getNow() {
		DateTime dt = new DateTime();
		return dt.toDate();
	}

	public static Timestamp getTimestampNow() {
		DateTime dt = new DateTime();
		return new Timestamp(dt.getMillis());
	}


	public static Date parse(String dateStr) {
		return DateTime.parse(dateStr, format).toDate();
	}

	public static String getDateStr(Date date) {
		return new DateTime(date).toString(defaultFormat);
	}

	public static String getDateStr(Timestamp timestamp) {
		return new DateTime(timestamp).toString(defaultFormat);
	}


	public static String getDateStrYMD(Timestamp timestamp) {
		String format = "yyyy-MM-dd";
		return new DateTime(timestamp).toString(format);
	}

	public static String getDateStrYMD(String dateStr) {
		String format = "yyyy-MM-dd";
		Timestamp timestamp = transformStr(dateStr);
		return new DateTime(timestamp).toString(format);
	}

	/**
	 * 将日期字符串重新格式化
	 *
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String transformDateStr(String pattern, String date) {
		Timestamp timestamp = transformStr(pattern, date);
		return new DateTime(timestamp == null ? 0L : timestamp.getTime()).toString(defaultFormat);
	}

	/**
	 * 传入日期的字符串和模式生成timestamp
	 *
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static Timestamp transformStr(String pattern, String date) {
		Timestamp result = null;
		try {
			Date tmp = new SimpleDateFormat(pattern).parse(date);
			result = new Timestamp(tmp.getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 将日期字符串重新格式化 默认yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 */
	public static Timestamp transformStr(String date) {
		return transformStr(defaultFormat, date);
	}

	public static final Timestamp string2Timestamp(String format, String time) {
		if (format == null) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

		Date date = null;
		try {
			date = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			return null;
		}
		return new Timestamp(date.getTime());
	}

	public static Pair<String, String> getBeforeDay(int day){
		DateTime start = new DateTime();
		start = start.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
		DateTime end = new DateTime();
		end = end.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
		String startTime = start.toString("yyyy-MM-dd HH:mm:ss");
		String endTime = end.toString("yyyy-MM-dd HH:mm:ss");

		if(day > 0 ) {
			if(day == 1) {
				start = start.minusDays(1);
				end = end.minusDays(1);
				startTime = start.toString("yyyy-MM-dd HH:mm:ss");
				endTime = end.toString("yyyy-MM-dd HH:mm:ss");
			}else {
				start = start.minusDays(day);
				startTime = start.toString("yyyy-MM-dd HH:mm:ss");
			}
		}

		return Pair.of(startTime, endTime);
	}

	public static String getBeforeMinute(String dateStr, int minute){
		Date date = DateUtil.parse(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -30);
		return DateUtil.getDateStr(calendar.getTime());
	}

	public static String getNextDay(String pattern){
		DateTime now = new DateTime();
		now = now.plusDays(1);
		return now.toString(pattern);
	}

	public static void main(String[] args) {
		System.out.println(getBeforeMinute(getStrNow(), 30));
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmssSSS");//设置日期格式
//		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//		System.out.println( RandomStringUtils.randomNumeric(4));
		Timestamp timestampNow = DateUtil.string2Timestamp("yyyy-MM-dd HH:mm:ss", DateUtil.getStrNow());
		Timestamp timestampEnd = DateUtil.string2Timestamp("yyyy-MM-dd HH:mm:ss", "2017-09-22 13:07:11");
		long remain = (timestampEnd.getTime() - timestampNow.getTime()) / 1000;
		System.out.println(remain);
		System.out.println(remain / 3600 + ":" + (remain % 3600) / 60 + ":" + (remain % 3600) % 60);
	}
}