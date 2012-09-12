package models

import lib.Util

class RestaurantJönsJacob extends Restaurant {

	def name = "Jöns Jacob"
	
	def url = "http://gastrogate.com/restaurang/jonsjacob/page/3"

	override protected def charset = "ISO-8859-1"

	protected def startPattern(weekday: String) = """(?i)\b""" + weekday + """\b(\w|\s)*"""

	protected def endPattern(weekday: String) =
		if (weekday != "fredag") 
			startPattern(Util.nextWeekday(weekday))
		else
			"""(?i)<a .*?'Boka bord på din iPhone'"""

	protected def dishSeparator = "<br */?>"
	
	override protected def cleanupPattern = """(?m)\d+:-|^\s*(Dagens .*|Soppa|Vegetariskt)\s*$""" //removal of prices and headings

}
