var app = angular.module('encuestaApp')
app.service('EncuestaService',function($http){

	return{
		
		chequearSiYaLaHizo:function(mail){
			$http.get('/chequear',mail).success(callback);
		}
	
		responderEncuesta:function(respuesta,callback){
			$http.get('/responder',respuesta).success(callback);
		},

		getCarreras:function(){
			//obtienelalistadecarrerasconeldetalledelasmateriasylodevuelve
		},

		getTurnos:function(){
			//obtienelalistadeturnosylodevuelve
	
		}
	}
});