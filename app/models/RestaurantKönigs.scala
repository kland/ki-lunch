package models

import lib.Util

class RestaurantKönigs extends Restaurant {

	def name = "Königs"
	
	def url = "http://restaurangkonigs.se"

	def startPattern(weekday: String) = """(?i)<h3>(<strong>)?""" + weekday + """:(</strong>)?</h3>"""

	def endPattern(weekday: String) =
		if (weekday != "fredag") 
			startPattern(Util.nextWeekday(weekday))
		else
			"""(?i)<strong>Dagens rätt\b"""

	def dishSeparator = "<br */?>"
	
}
