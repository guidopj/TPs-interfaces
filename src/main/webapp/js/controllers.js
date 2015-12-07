'use strict';
var app = angular.module('encuestaApp');

app.controller('LoginCtrl',function($location,$scope,EncuestaService){

	var self = this;
	
	$scope.autenticar=function(){
		EncuestaService.chequearSiYaLaHizo(
			$scope.user.mail,
			function(data) {
	    		if(!data.data){
	    			$location.path('responder/'+$scope.user.mail);
	    		}
	    	}
		);
	}
	
    this.obtenerPosiciones = function(){
    	DuelosService.obtenerPosifciones(
    		function(data) {
    			self.posicionesDuelos = data.data;
    		}
    	);
    }
	
});
  