package controllers

import lib.Util
import models.Restaurants
import play.api._
import play.api.mvc._

object Application extends Controller {

	def index = Action {
		val weekday = Util.currentWeekday
		val restaurants = Restaurants.all
		Ok(views.html.index(weekday, restaurants))
	}

}
