package ar.edu.datos.controller

import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.json.JSONUtils
import org.uqbar.xtrest.api.XTRest
import org.uqbar.xtrest.api.Result
import org.uqbar.xtrest.api.annotation.Get
import org.uqbar.xtrest.http.ContentType
import ar.edu.datos.home.EncuestaHome

@Controller
class ResponderController {
	
	extension JSONUtils = new JSONUtils
	
	def static void main(String[]args){
		XTRest.start(ResponderController,9200)
	}
	
//	@Get('/chequear/:mail')
//	def Result chequearEmail(){
//		response.contentType=ContentType.APPLICATION_JSON
//		//var boolean completo = EncuestaHome.instance.chequearSiCompleto(String.valueOf(mail))
//		ok(true.toJson)
//	}

//	@Get('/carreras')
//	def Result carreras(){
//		response.contentType=ContentType.APPLICATION_JSON
//		ok(EncuestaHome.instance.todasCarreras.toJson)
//	}
//
//	@Get('/turnos')
//	def Result turnos(){
//		
//	}
}