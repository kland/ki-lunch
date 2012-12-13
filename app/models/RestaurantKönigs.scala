package models

class RestaurantKönigs extends Restaurant {

	def name = "Königs"
	
	def url = "http://restaurangkonigs.se"

	protected def startPattern(weekday: String) = """(?i)<h3>.*?\b""" + weekday + """\b.*?</h3>"""

	protected def endPattern(weekday: String) =
		if (weekday != "fredag") 
			startPattern(lib.Util.nextWeekday(weekday))
		else
			"""(?i)<strong>Dagens rätt\b"""

	protected def dishSeparator = "<br */?>"
	
}
