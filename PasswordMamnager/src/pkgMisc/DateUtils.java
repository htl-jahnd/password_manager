package pkgMisc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils
{
	private static final String DATE_PATTERN = "d.MM.yyyy";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
	private static final String YEAR_MONTH_PATTER = "MM/yy";
	private static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern(YEAR_MONTH_PATTER);

	public static String getStringOfLocalDate(LocalDate date)
	{
		return date.format(DATE_FORMATTER);
	}

	public static LocalDate getLocalDateOfString(String dateString)
	{
		return LocalDate.parse(dateString, DATE_FORMATTER);
	}
	
	public static LocalDate getLocalDateOfDate(Date date) {
		return LocalDate.parse(new SimpleDateFormat(DATE_PATTERN).format(date),DATE_FORMATTER);
	}
	
	public static YearMonth getYearMonthOfString(String yearMonth) {
		return YearMonth.parse(yearMonth, YEAR_MONTH_FORMATTER);
	}
	public static String getStringOfYearMonth(YearMonth yearMonth) {
		return yearMonth.format(YEAR_MONTH_FORMATTER);
	}
}
