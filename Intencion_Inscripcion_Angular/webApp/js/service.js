angular.module('intencionInscripcionApp')
.service('IntencionInscripcionService',function($http){
	this.obtenerDatos = function(callback){
		$http.get('/datos').success(function () {
            alert("PUDE TRAER LOS DATOS")
        });
	}
});