package lib

import java.text
import java.util

object Util {

	def currentWeekday = {
		val today = util.Calendar.getInstance().getTime()
		val weekdayFormat = new text.SimpleDateFormat("EEEE", new util.Locale("sv"))
		weekdayFormat.format(today)
	}
	
	def nextWeekday(weekday: String) = {
		val days = Array("måndag", "tisdag", "onsdag", "torsdag", "fredag", "lördag", "söndag")
		val nextIndex = (days.indexOf(weekday) + 1) % days.length
		days(nextIndex)
	}

}
