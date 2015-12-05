package controller

import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.api.XTRest
import org.uqbar.xtrest.json.JSONUtils

@Controller
class DatosController {
	
	extension JSONUtils = new JSONUtils
	
	def static void main(String[] args){
		XTRest.start(DatosController,9000)
	}
}