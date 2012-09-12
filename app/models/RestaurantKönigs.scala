package models

import lib.Util

class RestaurantKönigs extends Restaurant {

	def name = "Königs"
	
	def url = "http://restaurangkonigs.se"

	def parseBegin(weekday: String) = """(?i)<h3>(<strong>)?""" + weekday + """:(</strong>)?</h3>"""

	def parseEnd(weekday: String) =
		if (weekday != "fredag") 
			parseBegin(Util.nextWeekday(weekday))
		else
			"""(?i)<strong>Dagens rätt\b"""

	def dishSeparator = "<br */?>"
	
}
