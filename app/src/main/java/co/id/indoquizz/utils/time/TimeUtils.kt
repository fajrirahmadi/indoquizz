package co.id.indoquizz.utils.time

import android.annotation.SuppressLint
import co.id.indoquizz.constanta.MainConstanta
import co.id.indoquizz.utils.string.StringHelper
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Days
import org.joda.time.Instant
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TimeUtils {
    companion object {
        fun getDateTimeByInstantParse(dateTime: String): DateTime {
            return DateTime(Instant.parse(dateTime).getMillis())
        }

        fun getDateFormated(format: String, time: Long): String {
            return getDateFormated("in", format, time)
        }

        fun getDateFormatedWithMultiply(format: String, time: Long): String {
            return getDateFormated("in", format, time * 1000L)
        }

        fun getDateFormated(language: String, format: String, time: Long): String {
            val id = Locale(language, "ID")
            return SimpleDateFormat(format, id).format(Date(time))
        }

        fun compareBetween(start: Int, end: Int): Boolean {
            val dateTimeNow = DateTime.now()
            return dateTimeNow.dayOfMonth in start..end
        }

        fun compareBetween(endDate: Long, rangeTime: Int): Boolean {
            val timeNow =
                DateTime(Calendar.getInstance().timeInMillis, DateTimeZone.UTC).withHourOfDay(0).withMinuteOfHour(0)
                    .withSecondOfMinute(0)
                    .withMillisOfSecond(0)
            val timeExpired = DateTime(endDate * 1000).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                .withMillisOfSecond(0)
            return Days.daysBetween(timeNow, timeExpired).getDays() <= rangeTime
        }

        fun getDateTime(): String {
            val dateTimeNow = DateTime.now()
            return getDateFormated("yyyy-MM-dd HH:mm:ss", dateTimeNow.getMillis())
        }

        @SuppressLint("SimpleDateFormat")
        fun getSimpleDateFormat(format: String): SimpleDateFormat {
            return SimpleDateFormat(format)
        }

        fun isAfterNowWithMultiply(expiredDate: Long?): Boolean? {
            val timeNow =
                DateTime(Calendar.getInstance().timeInMillis).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                    .withMillisOfSecond(0)
            val timeExpired = DateTime(expiredDate!! * 1000).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
                .withMillisOfSecond(0).plusDays(1)
            return timeNow.isAfter(timeExpired)
        }

        fun getTodayFormatted(format: String): String {
            val dateTimeNow = DateTime.now()
            return getDateFormated(format, dateTimeNow.getMillis())
        }

        fun isThisMonth(date: Long?): Boolean {
            val dateNow = DateTime.now()
            val dateCompare = DateTime(date)
            return dateNow.monthOfYear() == dateCompare.monthOfYear() && dateNow.year() == dateCompare.year()
        }

        fun getStartedMonth(additionalMonth: Int): Long {
            var dateNow = DateTime.now().withZone(DateTimeZone.forOffsetHours(7)).withDayOfMonth(1).withMillisOfDay(0)
            dateNow = dateNow.plusMonths(additionalMonth)
            return dateNow.millis
        }


        fun isToday(financeDate: Long): Boolean {
            val dateNow = DateTime.now().withZone(DateTimeZone.forOffsetHours(7))

            return getDateFormated(MainConstanta.DATE_FORMAT, dateNow.millis) ==
                    getDateFormated(MainConstanta.DATE_FORMAT, financeDate)
        }

        fun getFormattedTime(seconds: Long): String {
            val time: String
            val day = TimeUnit.SECONDS.toDays(seconds).toInt()
            val hours = TimeUnit.SECONDS.toHours(seconds) - day * 24
            val minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60
            val second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(seconds) * 60
            if (day != 0) {
                time = StringHelper.getStringBuilderToString(
                    String.format(Locale.getDefault(), "%02d", day), ":",
                    String.format(Locale.getDefault(), "%02d", hours), ":",
                    String.format(Locale.getDefault(), "%02d", minute), ":",
                    String.format(Locale.getDefault(), "%02d", second)
                )
            } else {
                time = StringHelper.getStringBuilderToString(
                    String.format(Locale.getDefault(), "%02d", minute), ":",
                    String.format(Locale.getDefault(), "%02d", second)
                )
            }
            return time
        }

        fun getBirthDateLimit(): Long {
            var dateNow = DateTime.now().withZone(DateTimeZone.forOffsetHours(7)).withMillisOfDay(0)
            dateNow = dateNow.minusYears(10)

            return dateNow.millis
        }

        fun getTimeUTC(timeInMillis: Long): Long {
            val dateUtc = DateTime(timeInMillis, DateTimeZone.getDefault()).withMillisOfDay(0)
            return dateUtc.millis
        }
    }
}