
package com.soft.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FormatDate {
	
	public static Date changeDate(String date) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.parse(date);
	}
	public static Date changeDate(String date,String format) throws ParseException{
		if(!"".equals(date)){
			if(checkDateForm(date,format)){
				SimpleDateFormat dateFormat = new SimpleDateFormat(format);
				return dateFormat.parse(date);
			}else{
				throw new ParseException(format, 0);
			}
		}else{
			return null;
		}
	}

	public static boolean checkDateForm(String date,String format){
		int year = Integer.parseInt(date.substring(0,4));
		int month = Integer.parseInt(date.substring(4,6));
		int day = Integer.parseInt(date.substring(6,8));

		Calendar cal  =  Calendar.getInstance();   
		cal.set(year, month, day);
		int max_day =  cal.getActualMaximum(Calendar.DATE);
		

		if(FormatDate.changeDate_Long(date,format)>System.currentTimeMillis()) return false;

		if(month>12) return false;

		if(day>max_day) return false;
		return true;
	}

	public static String formatDate(Date myDate,String format) {
		if(format==null)format="yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String getWeek() {
		int ll_1 = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		String WeekName = "";
		switch (ll_1) {
		case 1:
			WeekName = "星期一";
			break;
		case 2:
			WeekName = "星期二";
			break;
		case 3:
			WeekName = "星期三";
			break;
		case 4:
			WeekName = "星期四";
			break;
		case 5:
			WeekName = "星期五";
			break;
		case 6:
			WeekName = "星期六";
			break;
		case 7:
			WeekName = "星期日";
			break;
		}
		return WeekName;
	}

	public static String getTime()
	{
		String str2=null;
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		if (c.get(Calendar.AM_PM) == Calendar.PM)
			hour += 12;
		str2 = (hour < 10 ? "0" + hour : "" + hour)
				+ (minute < 10 ? ":0" + minute : ":" + minute)
				+ (second < 10 ? ":0" + second : ":" + second);
		return (str2);
	}	


	public static String getYueminDay()
	{
		  Calendar   cal   =   Calendar.getInstance();   
		
		  int   minDate   =   cal.getActualMinimum(Calendar.DATE);   
		  
		  cal.set(Calendar.DATE,minDate);
		  
		  
		  return formatDate(cal.getTime(),"yyyy-MM-dd");

	}


	public static String getYuemaxDay()
	{
		  Calendar   cal   =   Calendar.getInstance();   
		
		  int   maxDate   =   cal.getActualMaximum(Calendar.DATE);   
		  
		  cal.set(Calendar.DATE,maxDate);
		    
		  return formatDate(cal.getTime(),"yyyy-MM-dd");

	}

	public static String changeDate(String str,int num,String format) {
		
		long aaL=1000*60*60*24*num;
		long date = 0;
		SimpleDateFormat formatter = new SimpleDateFormat(format);	
		try {
			date=formatter.parse(str).getTime();    
		} catch (ParseException e) {					
			e.printStackTrace();
		}
		return formatDate(new Date(date+aaL),format);
	}

	public static String changeLong_Date(Long time,String format){
		
		return formatDate(new Date(time),format);
		
	}

	public static Long changeDate_Long(String Mydate,String format) {

		SimpleDateFormat sdm = new SimpleDateFormat(format);

		 try {
			return sdm.parse(Mydate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0l;
	}


	public static String day(int tian){
		Calendar Cal= Calendar.getInstance();
		Cal.setTime(new Date(System.currentTimeMillis())); 
		Cal.add(Calendar.DAY_OF_YEAR,tian);
		return formatDate(Cal.getTime(),"yyyyMMdd");
	}
	
	
	public static String fotmatDate1(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate2(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate3(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = formatter.format(myDate);
		return strDate;
	} 

	public static String fotmatDate4(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate5(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate6(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate7(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate8(Date myDate,int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - hour);// 让日期加1
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
		String strDate = formatter.format(calendar.getTime());
		return strDate;
	}

	public static String fotmatDate9(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate10(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate11(Date myDate,int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - year);// 让日期加1
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String strDate = formatter.format(calendar.getTime());
		return strDate;
	}

	public static String fotmatDate12(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate13(Date myDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String strDate = formatter.format(myDate);
		return strDate;
	}

	public static String fotmatDate14(Date myDate,int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - day);// 让日期加1
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(calendar.getTime());
		return strDate;
	}

	public static long ToYesterday(){ 
		Calendar nowDate = new java.util.GregorianCalendar(); 
		nowDate.set(Calendar.HOUR_OF_DAY,0); 
		nowDate.set(Calendar.MINUTE,0); 
		nowDate.set(Calendar.SECOND,0); 
		nowDate.set(Calendar.MILLISECOND,0); 
		return  nowDate.getTimeInMillis()/1000; 
		}

	public static void main(String[] args) {
		String yestday = FormatDate.fotmatDate14(new Date(System.currentTimeMillis()), 1);
		String jobStartTime = yestday + " 00:00:01";
		String jobEndTime = yestday + " 23:59:59";
		System.out.println(jobStartTime);
	}

}