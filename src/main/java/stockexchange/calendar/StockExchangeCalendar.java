package stockexchange.calendar;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockexchange.brokerage.Brokerage;

@Component
public class StockExchangeCalendar {
	
	@Autowired
	private Brokerage brokerage;

	private static final int DAY_OF_WEEK_SATURDAY = 7;
	private static final int DAY_OF_WEEK_SUNDAY = 1;
	private Date firstDateOnSE = new Date();
	private Date lastDateOnSE  = new Date();
	private Date currentDate  = new Date();
	
	public Date getNextDay(Date currentDate){
		Date date = currentDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);	
		return calendar.getTime();
	}
	
	public Date getNextWorkingDay(Date currentDate){
		Date nextDdate =getNextDay(currentDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nextDdate);		while(calendar.get(Calendar.DAY_OF_WEEK) == DAY_OF_WEEK_SATURDAY ||
			  calendar.get(Calendar.DAY_OF_WEEK) == DAY_OF_WEEK_SUNDAY){			nextDdate = getNextDay(nextDdate);
			calendar.setTime(nextDdate);		}
		return calendar.getTime();
	}

	public Date getFirstDateOnSE() {
		return firstDateOnSE;
	}

	public void updateFirstDateOnSE() {
		this.firstDateOnSE = brokerage.getFirstDateOnSE();
	}

	public Date getLastDateOnSE() {
		return lastDateOnSE;
	}

	public void udpateLastDateOnSE() {
		this.lastDateOnSE = brokerage.getLastDateOnSE();
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
}
