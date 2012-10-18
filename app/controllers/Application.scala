package controllers

import play.api.mvc

object Application extends mvc.Controller {

	def index = mvc.Action {
		val weekday = lib.Util.currentWeekday
		val restaurants = models.Restaurants.all
		Ok(views.html.index(weekday, restaurants))
	}

}
