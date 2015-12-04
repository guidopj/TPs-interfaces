package duelo_web_xtend

import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.json.JSONUtils
import org.uqbar.xtrest.api.XTRest
import org.uqbar.xtrest.api.annotation.Get
import org.uqbar.xtrest.api.Result
import org.uqbar.xtrest.http.ContentType

@Controller
class DatosController {
	
	extension JSONUtils = new JSONUtils
	
	def static void main(String[] args){
		XTRest.start(DatosController,9000)
	}
}