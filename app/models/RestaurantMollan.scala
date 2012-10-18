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

	protected def dishSeparator = """(?i)<p\b[^>]*><font\b[^>]*><span\b[^>]*><span\b[^>]*>[A-Z]\."""

	override protected def cleanupPattern = """(&nbsp;)+"""

}
