package lib

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Util {

	def currentWeekday = {
		val today = Calendar.getInstance().getTime()
		val weekdayFormat = new SimpleDateFormat("EEEE", new Locale("sv"))
		weekdayFormat.format(today)
	}
	
	def nextWeekday(weekday: String) = {
		val days = Array("måndag", "tisdag", "onsdag", "torsdag", "fredag", "lördag", "söndag")
		val nextIndex = (days.indexOf(weekday) + 1) % days.length
		days(nextIndex)
	}

}
