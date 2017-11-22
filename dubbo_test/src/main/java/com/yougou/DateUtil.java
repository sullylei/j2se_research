package com.yougou;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DateUtil {
	
	private static final String TIME_PATTERN = "HH:mm";
	public static final String VIEW_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
	public static final String LONG_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_DATE_TIME_PATTERN2= "yyyy/MM/dd HH:mm:ss";
	public static final String YEAR_MONTH_DAY_PATTERN="yyyy-MM-dd";
    public static final String LONG_DATE_TIME_PATTERN_BILL = "yyyyMMddHHmmss";
    public static final String YEAR_MONTH_DAY_PATTERN_1="yyyyMMdd";

	/**
	 * utility classes should not have public constructor
	 */
	private DateUtil() {
	}

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		return YEAR_MONTH_DAY_PATTERN;
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

    public static String getDate(Date aDate,String pattern) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(pattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see SimpleDateFormat
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM
	 * a
	 *
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}
    public static String getDateTimeForBill(Date theTime){
        return getDateTime(LONG_DATE_TIME_PATTERN_BILL, theTime);
    }
	/**
	 * 把date转换为yyyy-MM-dd HH:mm:ss字符串
	 * @param theTime
	 * @return
	 */
	public static String getDateTime(Date theTime){
		return getDateTime(LONG_DATE_TIME_PATTERN,theTime);
	}
	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 *
	 * @return the current date
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 *
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 *
	 * @see SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null){
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based on the
	 * System Property 'dateFormat' in the format you specify on input
	 *
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 *
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {

		if (strDate == null || strDate.equals("")) {
			return null;
		}

		Date aDate = null;

		try {
			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return aDate;
	}

	/**
	 * 根据年月日获得指定的日期
	 *
	 * @param year
	 *            年份
	 * @param month
	 *            月份
	 * @param day
	 *            日期
	 * @return 相应的 Date 型日期
	 */
	public static Date getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 根据给定的时间和日期获取一个包含了 日期 + 时间 的新的 Date
	 *
	 * @param date
	 *            指定的日期
	 * @param time
	 *            指定的时间
	 * @return 返回一个日期 + 时间的 Date
	 */
	public static Date getDateTime(Date date, Date time) {
		if (null == date && null == time) {
			return null;
		}
		if (null == date) {
			date = getSystemDate();
		}
		if (null != date && null == time) {
			time = getSystemTimestamp();
		}
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(formatDate(date, "yyyy"));
		int month = Integer.parseInt(formatDate(date, "M"));
		int day = Integer.parseInt(formatDate(date, "dd"));
		int hour = Integer.parseInt(formatDate(time, "h"));
		int minute = Integer.parseInt(formatDate(time, "m"));
		cal.set(year, month, day, hour, minute);
		return cal.getTime();
	}

	/**
	 * 判断两个日期是否相等
	 *
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 相等时返回 true
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		return !(null == date1) && !(null == date2)
				&& 0 == resetTime(date1).compareTo(resetTime(date2));
	}

	/**
	 * 判断给定日期是否为当月的最后一天
	 *
	 * @param date
	 *            指定的日期
	 * @return 当该日期为当月最后一天则返回 true
	 */
	public static boolean isEndOfTheMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return cal.get(Calendar.DATE) == maxDay;
	}

	/**
	 * 判断给定日期是否为当年的最后一天
	 *
	 * @param date
	 *            指定的日期
	 * @return 当该日期为当年最后一天则返回 true
	 */
	public static boolean isEndOfTheYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (11 == cal.get(Calendar.MONTH))
				&& (31 == cal.get(Calendar.DATE));
	}

	/**
	 * 获得给定日期的月份的最后一天
	 *
	 * @param date
	 *            指定的日期
	 * @return 指定日期月份的最后一天
	 */
	public static int getLastDayOfTheMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得当前时间之后的下一个工作日的时间
	 *
	 * @return 返回当前时间之后的下一个工作日的时间
	 */
	public static Date getNextWorkingDay() {
		Date nextWorkingDay = addDaysToDate(getSystemDate(), 1);
		Calendar c = Calendar.getInstance();
		c.setTime(nextWorkingDay);
		int day = c.get(Calendar.DAY_OF_WEEK);
		if (day == Calendar.SUNDAY) {
			nextWorkingDay = addDaysToDate(nextWorkingDay, 1);
		}
		return nextWorkingDay;
	}

	/**
	 * 判断开始日期是否比结束日期早
	 *
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 当开始日期比结束日期早时返回 true
	 */
	public static boolean isStartBeforeEndDate(Date startDate, Date endDate) {
		Assert.notNull(startDate, "StartDate is null");
		Assert.notNull(endDate, "EndDate is null");
		return resetTime(startDate).compareTo(resetTime(endDate)) < 0;
	}

	/**
	 * 判断开始日期是否比结束日期早
	 *
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return 当开始时间比结束时间早时返回 true
	 */
	public static boolean isStartBeforeEndTime(Date startTime, Date endTime) {
		Assert.notNull(startTime, "StartTime is null");
		Assert.notNull(endTime, "EndTime is null");
		return startTime.getTime() <= endTime.getTime();
	}

	/**
	 * 判断给定日期是否为对应日期月份的第一天
	 *
	 * @param date
	 *            给定日期
	 * @return 当给定日期是否为对应日期月份的第一天返回 true
	 */
	public static boolean isStartOfTheMonth(Date date) {
		Assert.notNull(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return 1 == cal.get(Calendar.DATE);
	}

	/**
	 * 判断给定日期是否为对应日期年份的第一天
	 *
	 * @param date
	 *            给定日期
	 * @return 当给定日期是否为对应日期年份的第一天返回 true
	 */
	public static boolean isStartOfTheYear(Date date) {
		Assert.notNull(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (1 == cal.get(Calendar.MONTH)) && (1 == cal.get(Calendar.DATE));
	}

	/**
	 * 获取给定日期的月份
	 *
	 * @param date
	 *            给定日期
	 * @return 给定日期的月份
	 */
	public static int getMonth(Date date) {
		Assert.notNull(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	/**
	 * 获取给定日期的年份
	 *
	 * @param date
	 *            给定日期
	 * @return 给定日期的年份
	 */
	public static int getYear(Date date) {
		Assert.notNull(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取不含不含小时分钟秒的系统日期
	 *
	 * @return 系统当前日期，不含小时分钟秒
	 */
	public static Date getSystemDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new java.sql.Date(cal.getTime().getTime());
	}

	/**
	 * 获取系统的 Timestamp
	 *
	 * @return 系统当前时间的 Timestamp
	 */
	public static Timestamp getSystemTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 给指定日期加几天
	 *
	 * @param date
	 *            指定的日期
	 * @param numDays
	 *            需要往后加的天数
	 * @return 加好后的日期
	 */
	public static Date addDaysToDate(Date date, int numDays) {
		Assert.notNull(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, numDays);
		return c.getTime();
	}

	/**
	 * 给指定日期加几个小时
	 *
	 * @param date
	 *            指定的日期
	 * @param numHours
	 *            需要往后加的小时数
	 * @return 加好后的日期
	 */
	public static Date addHoursToDate(Date date, int numHours) {
		Assert.notNull(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, numHours);
		return c.getTime();
	}

	/**
	 * 给指定日期加几分钟
	 *
	 * @param date
	 *            指定的日期
	 * @param numMins
	 *            需要往后加的分钟数
	 * @return 加好后的日期
	 */
	public static Date addMinutesToDate(Date date, int numMins) {
		Assert.notNull(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, numMins);
		return c.getTime();
	}

	/**
	 * 给指定日期加几个月
	 *
	 * @param date
	 *            指定的日期
	 * @param numMonths
	 *            需要往后加的月数
	 * @return 加好后的日期
	 */
	public static Date addMonthsToDate(Date date, int numMonths) {
		Assert.notNull(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, numMonths);
		return c.getTime();
	}

	/**
	 * 给指定日期加几年
	 *
	 * @param date
	 *            指定的日期
	 * @param numYears
	 *            需要往后加的年数
	 * @return 加好后的日期
	 */
	public static Date addYearsToDate(Date date, int numYears) {
		Assert.notNull(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, numYears);
		return c.getTime();
	}

	/**
	 * 将一个指定的日期格式化成指定的格式
	 *
	 * @param date
	 *            指定的日期
	 * @param pattern
	 *            指定的格式
	 * @return 格式化好后的日期字符串
	 */
	public static String formatDate(Date date, String pattern) {
		if(date==null)
		{
			return "";
		}
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 将指定日期的小时、分钟、秒清零
	 *
	 * @param date
	 *            指定的日期
	 * @return 小时、分钟、秒被清零的日期
	 */
	public static Date resetTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 将一个字符串转换成指定格式的日期
	 *
	 * @param strDateTime
	 *            需要转换的日期字符串
	 * @param pattern
	 *            转换的格式
	 * @return 当转换成功返回转换成功后的日期，否则返回 null
	 */
	public static Date parseDate(String strDateTime, String pattern) {
		try {
			return DateUtils.parseDate(strDateTime, new String[] { pattern });
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将一个字符串转换成指定格式的日期
	 *
	 * @param strDateTime
	 *            需要转换的日期字符串
	 * @param patterns
	 *            转换的格式数组，包含多个转换格式，其中任何一种匹配都可以转换成功
	 * @return 当转换成功返回转换成功后的日期，否则返回 null
	 */
	public static Date parseDate(String strDateTime, String[] patterns) {
		try {
			return DateUtils.parseDate(strDateTime, patterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将一个字符串转换成指定格式的Timestamp
	 *
	 * @param strDateTime
	 *            需要转换的时间字符串
	 * @param pattern
	 *            转换的格式
	 * @return 当转换成功返回转换成功后的 Timestamp，否则返回 null
	 */
	public static Timestamp parseTimestamp(String strDateTime, String pattern) {
		return toTimestamp(parseDate(strDateTime, pattern));
	}

	/**
	 * 将一个字符串转换成指定格式的Timestamp
	 *
	 * @param strDateTime
	 *            需要转换的时间字符串
	 * @param patterns
	 *            转换的格式数组，包含多个转换格式，其中任何一种匹配都可以转换成功
	 * @return 当转换成功返回转换成功后的 Timestamp，否则返回 null
	 */
	public static Timestamp parseTimestamp(String strDateTime, String[] patterns) {
		return toTimestamp(parseDate(strDateTime, patterns));
	}

	/**
	 * 将 Date 转换为 Timestamp
	 *
	 * @param date
	 *            需要转换的 Date
	 * @return 转换后的 Timestamp
	 */
	public static Timestamp toTimestamp(Date date) {
		Assert.notNull(date);
		return new Timestamp(date.getTime());
	}

	/**
	 * 将 Timestamp 转换为 Date
	 *
	 * @param timestamp
	 *            需要转换的 Timestamp
	 * @return 转换后的 Date
	 */
	public static Date toDate(Timestamp timestamp) {
		Assert.notNull(timestamp);
		return new Date(timestamp.getTime());
	}

	/**
	 * 获取当前年份
	 *
	 * @return
	 */
	public static int getYearNow() {
		return getYear(new Date());
	}

	public static Timestamp StrToTimestamp(String timestampStr, String pattern)
			throws ParseException {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(timestampStr);
		} catch (ParseException e) {
			throw e;
		}
		return date == null ? null : new Timestamp(date.getTime());
	}

	/**
	 * 日期转为数字
	 */
	public static String convertDateToString(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 数组拼装成逗号分隔
	 * @param arr
	 * @return
	 */
	public static String arrayToString(String[] arr)
	{
		String arrstr = "";
		if(arr.length > 0)
		{
			for(int i=0;i<arr.length;i++)
			{
				arrstr += ","+arr[i];
			}
			arrstr = arrstr.substring(1);
		}
		return arrstr;
	}

	public static Date formatDateToStartDateTime(String strDate) {
		Date date = null;
		try {
			date = convertStringToDate(LONG_DATE_TIME_PATTERN, strDate + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将传过来的日期yyyy-MM-dd HH:mm:ss 转换为 yyyy-MM-dd HH:mm:ss
	 * @param strDate
	 * @return
	 */
	public static Date formatDateToStartDateTime2(String strDate) {
		Date date = null;
		try {
			date = convertStringToDate(LONG_DATE_TIME_PATTERN, strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 将传过来的日期yyyy-MM-dd HH:mm 转换为 日期类型yyyy-MM-dd HH:mm
	 * @param strDate
	 * @return
	 */
	public static Date formatDateToStartDateTime3(String strDate) {
		Date date = null;
		try {
			date = convertStringToDate(VIEW_DATE_TIME_PATTERN, strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date formatDateToEndDateTime(String strDate) {
		Date date = null;
		try {
			date = convertStringToDate(LONG_DATE_TIME_PATTERN, strDate + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}



	/**
	 * strDate 带时分秒
	 * @param strDate
	 * @return
	 */
	public static Date formatDateToEndDateTime2(String strDate) {
		Date date = null;
		try {
			date = convertStringToDate(LONG_DATE_TIME_PATTERN, strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static int getMaxDaybyYearAndMonth(int year, int month) {
        int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (2 == month && 0 == (year % 4)
                && (0 != (year % 100) || 0 == (year % 400))) {
            days[1] = 29;
        }
        return (days[month - 1]);
    }

	public static int[] getMonth() {
        int[] months = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        return months;
    }

	public static String[] getTwoHoursByCurrencyHours() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		Date startDateTime = calendar.getTime();
		return new String[]{DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss", startDateTime), DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss", new Date())};
	}

	public static String[] getTwoDaysByCurrencyDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date startDateTime = calendar.getTime();
		return new String[]{DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss", startDateTime), DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss", new Date())};
	}

	public static String[] getTwoDaysByCurrencyDay(int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, day);
		Date startDateTime = calendar.getTime();
		return new String[]{DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss", startDateTime), DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss", new Date())};
	}


	/**
	 * 根据指定日期字符串获取本月第一天
	 * @throws ParseException
	 */
	public static String getFirstDayOfMonth(String monthFirstDate) throws ParseException{
	       String str = "";
	       SimpleDateFormat sdf=new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);
	       SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM");
	       Date date = sdf2.parse(monthFirstDate);
	       Calendar lastDate = Calendar.getInstance();
	       lastDate.setTime(date);
	       lastDate.set(Calendar.DATE,1);//设为当前月的1号
	       str=sdf.format(lastDate.getTime());
	       return str;
	}
	/**
	 * 根据指定日期字符串获取本月最后一天
	 * @param monthLastDate
	 * @return
	 * @throws ParseException
	 */
    public static String getLastDayOfMonth(String monthLastDate)throws ParseException{
       String str = "";
       SimpleDateFormat sdf=new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);
       SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM");
       Calendar lastDate = Calendar.getInstance();
       Date date = sdf2.parse(monthLastDate);
       lastDate.setTime(date);
       lastDate.set(Calendar.DATE,1);//设为当前月的1号
       lastDate.add(Calendar.MONTH,1);//加一个月，变为下月的1号
       lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

       str=sdf.format(lastDate.getTime());
       return str;
    }
   /**
    * 根据当前日期获取前一天的日期
    * @return
    * @throws ParseException
    */
    public static String getBeforeDateByNow(String nowDate)throws ParseException{
    	String str = "";
    	SimpleDateFormat sdf=new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);
    	Calendar cal=Calendar.getInstance();
    	Date date = sdf.parse(nowDate);
    	cal.setTime(date);
        cal.add(Calendar.DATE,-1);
        str=sdf.format(cal.getTime());
    	return str;
    }


    /**
     * 根据当前日期获取后一天的日期
     * @return
     * @throws ParseException
     */
     public static String getAfterDateByNow(String nowDate)throws ParseException{
     	String str = "";
     	SimpleDateFormat sdf=new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);
     	Calendar cal=Calendar.getInstance();
     	Date date = sdf.parse(nowDate);
     	cal.setTime(date);
         cal.add(Calendar.DATE,1);
         str=sdf.format(cal.getTime());
     	return str;
     }

    /**
     * 根据年月 获取该月份上个月26号的日期
     * @param year_month
     * @return
     * @throws ParseException
     */
    public static String getBeforeMonth26day(String year_month) throws ParseException{
    	DateFormat df = new SimpleDateFormat("yyyy-MM");
    	DateFormat df2 = new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);
    	Date date = df.parse(year_month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-1);
        calendar.set(Calendar.DATE,26);//设为当前月的26号
    	return df2.format(calendar.getTime());
    }
    /**
     * 给指定年月加一个月
     * @return
     * @throws ParseException
     */
    public static String getNextMonth(String yearMonthStr)throws ParseException{
    	DateFormat df = new SimpleDateFormat("yyyy-MM");
    	Date date = df.parse(yearMonthStr);
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,1);
        return df.format(calendar.getTime());
    }
    /**
     * 根据年月 获取该本月份25号的日期
     * @param year_month
     * @return
     * @throws ParseException
     */
    public static String getMonth25day(String year_month) throws ParseException{
    	DateFormat df = new SimpleDateFormat("yyyy-MM");
    	DateFormat df2 = new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);
    	Date date = df.parse(year_month);
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.DATE,25);//设为当前月的1号  
    	return df2.format(calendar.getTime());
    }
    /** 
     * 取得当前日期是多少周
     * @param date 
     * @return 
     */ 
     public static int getWeekOfYear(Date date) { 
	     Calendar c = new GregorianCalendar(); 
	     c.setFirstDayOfWeek(Calendar.MONDAY); 
	     c.setMinimalDaysInFirstWeek(7); 
	     c.setTime (date);
	     return c.get(Calendar.WEEK_OF_YEAR); 
     }
     /** 
     * 得到某一年周的总数 
     * @param year
     * @return 
     */ 
     public static int getMaxWeekNumOfYear(int year) { 
	     Calendar c = new GregorianCalendar(); 
	     c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
	     return getWeekOfYear(c.getTime()); 
     }
     /** 
     * 得到某年某周的第一天 
     * @param year
     * @param week
     * @return 
     */
     public static Date getFirstDayOfWeek(int year, int week) { 
	     Calendar c = new GregorianCalendar(); 
	     c.set(Calendar.YEAR, year); 
	     c.set (Calendar.MONTH, Calendar.JANUARY); 
	     c.set(Calendar.DATE, 1);
	     Calendar cal = (GregorianCalendar) c.clone(); 
	     cal.add(Calendar.DATE, week * 7);
	     return getFirstDayOfWeek(cal.getTime()); 
     }
     /** 
     * 得到某年某周的最后一天 
     * @param year
     * @param week
     * @return 
     */ 
     public static Date getLastDayOfWeek(int year, int week) { 
	     Calendar c = new GregorianCalendar(); 
	     c.set(Calendar.YEAR, year); 
	     c.set(Calendar.MONTH, Calendar.JANUARY); 
	     c.set(Calendar.DATE, 1);
	     Calendar cal = (GregorianCalendar) c.clone(); 
	     cal.add(Calendar.DATE , week * 7);
	     return getLastDayOfWeek(cal.getTime()); 
     }
     /** 
      * 取得指定日期所在周的第一天 
      * @param date
      * @return
      */ 
      public static Date getFirstDayOfWeek(Date date) { 
	      Calendar c = new GregorianCalendar(); 
	      c.setFirstDayOfWeek(Calendar.MONDAY); 
	      c.setTime(date); 
	      c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday 
	      return c.getTime (); 
      }
      /** 
      * 取得指定日期所在周的最后一天
      * @param date
      * @return
      */
      public static Date getLastDayOfWeek(Date date) { 
	      Calendar c = new GregorianCalendar(); 
	      c.setFirstDayOfWeek(Calendar.MONDAY); 
	      c.setTime(date); 
	      c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday 
	      return c.getTime(); 
      }
      public static void main(String[] args) {
    	  System.out.println(getDate(getFirstDayOfWeek(2012,5)));;
    	  System.out.println(getDate(getLastDayOfWeek(2012,5)));;
    	  
    	  //System.out.println(Integer.valueOf("09"));
    	  
	  }
      
  	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

  	/**
  	 * 日期相减(返回分钟)
  	 * 
  	 * @param date
  	 *            Date
  	 * @param date1
  	 *            Date
  	 * @return int
  	 * @author
  	 */
  	public static Long diffDateTimeMin(Date date, Date date1) {
  		return (Long) ((getMillis(date) - getMillis(date1)) / (60 * 1000));
  	}
  	/** 
     * 得到二个日期间的间隔天数 
     */  
  	public static int getTwoDay(String sj1, String sj2) {  
	     SimpleDateFormat myFormatter = new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);  
	     Long day = 0l;  
	     try {  
		      Date date = myFormatter.parse(sj1);
		      Date mydate = myFormatter.parse(sj2);
		      day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);  
	     } catch (Exception e) {  
	    	 return day.intValue();  
	     }  
	     return day.intValue(); 
  	}
  	public static int daysOfTwo(Date fDate, Date oDate) {
  		 Long day = 0l;  
	     try {  
		      day = (fDate.getTime() - oDate.getTime()) / (24 * 60 * 60 * 1000);  
	     } catch (Exception e) {  
	    	 return day.intValue();  
	     }  
	     return day.intValue(); 
  	}
  	/**
  	 * 获取开始日期的月份(针对 本月的上个月的26至本月25号)
  	 */
  	public static int getMonthBySatrtDate(String startDate){
  		int month = 0;
  		String dayStr =  startDate.substring(startDate.lastIndexOf("-")+1, startDate.length());
  		
  		String monthStr = startDate.substring(startDate.indexOf("-")+1, startDate.lastIndexOf("-"));
  		
  		if(Integer.valueOf(dayStr)>=26){
  			month = Integer.valueOf(monthStr)+1;
  		}else{
  			month = Integer.valueOf(monthStr);
  		}
  		return month;
  	}
  	/**
  	 * 获取年月之间相隔多少月
  	 * @param date1
  	 * @param date2
  	 * @return
  	 */
  	public static int getMonthNum(Date date1,Date date2) {
        Calendar cal1=Calendar.getInstance(); 
        cal1.setTime(date1); 
        Calendar cal2=Calendar.getInstance();
        cal2.setTime(date2);
        return (cal1.get(1)-cal2.get(1))*12+(cal1.get(2)-cal2.get(2));    

    }
  	
  	
  	/**

  	* 得到指定月的天数

  	* */
  	public static int getMonthLastDay(int year, int month){
	  	Calendar a = Calendar.getInstance();
	  	a.set(Calendar.YEAR, year);
	  	a.set(Calendar.MONTH, month - 1);
	  	a.set(Calendar.DATE, 1);//把日期设置为当月第一天
	  	a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
	  	int maxDate = a.get(Calendar.DATE);
	  	return maxDate;
  	}
  	
  	/**
  	 * 当前时间前一个月的时间.
  	 * @return
  	 */
  	public  static String  currentDateLastMonth(){
  		Calendar c = Calendar.getInstance();
  		c.add(Calendar.MONTH, -1);
  		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  		String preMonth = dateFormat.format(c.getTime());
  		return  preMonth;
  	}
}
