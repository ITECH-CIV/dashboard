package org.itechciv.dashboard.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public final class DateUtility {

	public static final String yyyyMMddHHmmssSSSSSS = "yyyyMMddHHmmssSSSSSS";
	public static final String yyyyMMddHHmmssSSSSSS_FORMAT1 = "yyyy/MM/dd HH:mm:ss.SSSSSS";
	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String yyyyMMddHHmmssSSS_FORMAT1 = "yyyy/MM/dd HH:mm:ss.SSS";
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String yyyyMMddHHmmss_FORMAT1 = "yyyy/MM/dd HH:mm:ss";
	public static final String yyyyMMddHHmm = "yyyy/MM/dd HH:mm";
	public static final String yyyyMMdd = "uuuu/MM/dd";
	public static final String yyyyMMdd_FORMAT1 = "uuuuMMdd";
	public static final String yyyyMMdd_FORMAT2 = "M月d日";

	private DateUtility() {
	}

	public static final LocalDateTime now() {
		return LocalDateTime.now();
	}

	public static final String now(final String format) {
		if(StringUtils.isEmpty(format)) {
			return null;
		}
		LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return DateUtility.now().format(formatter);
	}

	public static final String toString(final LocalDateTime localDateTime) {
	if (localDateTime == null) {
			return "";
		}
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern(yyyyMMddHHmmssSSSSSS);
		//return formatter.format(localDateTime);
		return format(localDateTime, yyyyMMddHHmmssSSS);
	}

	public static final String format(final LocalDateTime localDateTime, final String format) {
		if (localDateTime == null || StringUtils.isEmpty(format)) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return formatter.format(localDateTime);
	}

	public static final LocalDateTime toLocalDateTime(final String text) {
		if (StringUtils.isEmpty(text) || text.length() != 17) {
			return null;
		}
		final String format = yyyyMMddHHmmss;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		int len = text.length();
		LocalDateTime result = LocalDateTime.parse(text.substring(0, len-3),  dtf);
		int millis = Integer.parseInt(text.substring(len-3, len));
		result = result.plus(millis, ChronoUnit.MILLIS);
		return result;
	}

	public static final LocalDateTime toLocalDateTime(final String text, final String format) {
		if (StringUtils.isEmpty(text)) {
			return null;
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.STRICT);
		LocalDateTime result = LocalDateTime.parse(text, formatter);
		return result;
	}

	public static final int compareTo(final LocalDateTime date1, final String textDate2) {
		LocalDateTime date2 = DateUtility.toLocalDateTime(textDate2);
		return date1.compareTo(date2);
	}

	public static final int compareTo(final String textDate1, final String textDate2, final String format) {
		LocalDateTime date1 = DateUtility.toLocalDateTime(textDate1, format);
		LocalDateTime date2 = DateUtility.toLocalDateTime(textDate2, format);
		return date1.compareTo(date2);
	}

	public static final String addDay(final String date, final int days, final String inputFormat, final String outputFormat) {
		if(StringUtils.isEmpty(date) || date.length() != inputFormat.length()) {
			return date;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inputFormat).withResolverStyle(ResolverStyle.STRICT);
		LocalDate localDate = LocalDate.parse(date, formatter);
		LocalDate addLocalDate = localDate.plusDays(days);

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(outputFormat);
		return formatter2.format(addLocalDate);
	}

	public static final String get7DaysAgo(final String startDay) {
		return DateUtility.addDay(startDay, -6, DateUtility.yyyyMMdd, DateUtility.yyyyMMdd);
	}

	public static final List<String> getAWeekWithLastDay(final String startDay) {
		List<String> result = new ArrayList<>();

		String days7 = DateUtility.get7DaysAgo(startDay);
		for(int i=0; i<=6; i++) {
			result.add(addDay(days7, i, yyyyMMdd, yyyyMMdd_FORMAT2));
		}
		return result;
	}

}