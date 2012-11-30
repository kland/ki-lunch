package models

class RestaurantJönsJacob extends Restaurant {

	def name = "Jöns Jacob"
	
	def url = "http://gastrogate.com/restaurang/jonsjacob/page/3"

	protected def startPattern(weekday: String) = """(?i)\b""" + weekday + """\b(\w|\s)*"""

	protected def endPattern(weekday: String) =
		if (weekday != "fredag") 
			startPattern(lib.Util.nextWeekday(weekday))
		else
			"""<div class="restright grid_6">"""

	protected def dishSeparator = """<br */?>|</td>"""
	
	override protected def cleanupPattern = """(?im)^\s*(\d+:-|A la Minute|Dagens\s+\S+|Soppa|Vegetariskt)\s*$""" //removal of prices and headings

}
