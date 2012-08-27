package models

import models.RestaurantHjulet
import models.RestaurantMF

object Restaurants {

	def all = List(new RestaurantHjulet, new RestaurantMF)

}
