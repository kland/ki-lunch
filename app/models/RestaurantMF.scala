package models

class RestaurantMF extends Restaurant {

	def name = "MF"
	
	def url = "http://mmcatering.nu/mfs-kafe-kok/meny"

	protected def startPattern(weekday: String) = """(?i)<h6.*?>.*\b""" + weekday + """\b.*</h6>"""

	protected def endPattern(weekday: String) =
		if (weekday != "fredag") 
			startPattern(lib.Util.nextWeekday(weekday))
		else
			"""\bPris\b"""

	protected def dishSeparator = """<br\s*/?>"""

}
