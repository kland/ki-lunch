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

}
