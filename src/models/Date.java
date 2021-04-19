package models;

public class Date {
	// incapsulazione: i campi di Date sono gestiti solo da Date
	private int day;
	private int month;
	private int year;

	// costante di classe
	private final static String[] mesi =
		{ "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
			"Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"}; 

	private final static int[] daysInMonth = {
			31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
	};

	// costruttore
	public Date(final int day, final int month, final int year) {
		this.day = day;
		this.month = month;
		this.year = year;
		//this.checkIfLegal();
	}

	public String toString() {
			return day + "/" + month + "/" + year;
	}

	public boolean equals(Date other) {
		return day == other.day &&
				month == other.month &&
				year == other.year;
	}

	/*public int compareTo(Date other) {
	if (this.year < other.year)
	    return -1;
	else if (this.year > other.year)
	    return 1;
	else if (this.month < other.month)
	    return -1;
	else if (this.month > other.month)
	    return 1;
	else if (this.day < other.day)
	    return -1;
	else if (this.day > other.day)
	    return 1;
	else
	    return 0;
	    }*/

	public int compareTo(Date other) {
		int diff = year - other.year;
		if (diff != 0)
			return diff;

		diff = month - other.month;
		if (diff != 0)
			return diff;

		return day - other.day;
	}

	public boolean checkIfLegal() {
		boolean validation = false;
		
		if(month < 1 || month > 12 || day < 1 || day > numberOfDaysInMonth()) {
			validation = false;
		} else {
			validation = true;
		}
		
		return validation;
	}
	
	private int numberOfDaysInMonth() {
		if (month == 2 && isLeapYear())
			return 29;
		else
			return daysInMonth[month - 1];
	}

	public boolean checkIfSevenDaysHavePassed(models.Date date2) {
		int day_diff, mon_diff, year_diff;
		
		if(date2.checkIfLegal()) {
			if(date2.day < day) {
				date2.day += numberOfDaysInMonth();
				date2.month = date2.month - 1;
			}
		}
		
		if(date2.month < month) {
			date2.month += 12;
			date2.year -= 1;
		}
		
		day_diff = date2.day - day;
	    mon_diff = date2.month - month;
	    year_diff = date2.year - year;
	    
	    if(day_diff < 0 || mon_diff < 0 || year_diff < 0) {
	    	return false;
	    } else {
	    	if(day_diff > 7 || mon_diff > 0 || year_diff > 0) {
	    		return true;
	    	} else {
	    		return false;
	    	}
		}
	}

	private boolean isLeapYear() {
		return year % 4 == 0 &&
			(year % 100 != 0 || year % 400 == 0);
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public static String[] getMesi() {
		return mesi;
	}

	public static int[] getDaysinmonth() {
		return daysInMonth;
	}
}