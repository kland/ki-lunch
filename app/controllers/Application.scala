package controllers

import lib.Util
import play.api._
import play.api.mvc._

object Application extends Controller {
  
	def index = Action {
		val weekday = Util.currentWeekday.capitalize
		Ok(views.html.index(weekday))
	}
  
}
