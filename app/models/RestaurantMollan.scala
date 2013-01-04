package models

class RestaurantMollan extends Restaurant {

	def name = "Mollan Asian Kök"
	
	def url = "http://www.mollanasiankok.se/veckansmeny.html"
	
	override protected def charset = "ISO-8859-1"

	protected def startPattern(weekday: String) = """(?i)\b""" + weekday + """\b"""

	protected def endPattern(weekday: String) = 
		if (weekday != "fredag")
			startPattern(lib.Util.nextWeekday(weekday))
		else
			"""\bG.=Gluten\b"""

	protected def dishSeparator = "\n"

	override protected def cleanupPattern = """(&nbsp;)+"""

}
