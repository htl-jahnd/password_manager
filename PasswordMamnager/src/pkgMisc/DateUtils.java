package pkgMisc;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils
{
	private static final String PATTERN = "d.MM.yyyy";
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

	public static String getStringOfLocalDate(LocalDate date)
	{
		return date.format(formatter);
	}

	public static LocalDate getLocalDateOfString(String dateString)
	{
		return LocalDate.parse(dateString, formatter);
	}
	
	public static LocalDate getLocalDateOfDate(Date date) {
		return LocalDate.parse(new SimpleDateFormat(PATTERN).format(date),formatter);
	}
}
