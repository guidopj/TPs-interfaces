'use strict';
var app = angular.module('duelosApp')
app.controller('DatosPersonajeController',function ($scope, DuelosService) {
	$scope.datos = DuelosService.datosDelJuego();
	$scope.jugadorSeleccionado = $scope.datos.jugadorSeleccionado;
	$scope.personajeSeleccionado = {};
	$scope.rivalElegido = null;
	$scope.rivales = $scope.datos.rivalesPosibles
	$scope.estadisticasPersonajeSeleccionado = {};
	$scope.hayUnPersonajeSeleccionado = false;
	//$scope.selectedmodal = "#mrXModal";
	$scope.seleccionarPersonaje = function(personaje) { 
        $scope.personajeSeleccionado = personaje;
        $scope.hayUnPersonajeSeleccionado = true;
        $scope.buscarRival($scope.personajeSeleccionado);
        //$scope.selectedmodal = "#encontreRivalModal";
    };
    
    $scope.darPersonaje = function(jugador){
    	return jugador.personajes;
    }
    
    /*
     * USE FOR EN FORS porque perdi bocha de tiempo usando map y filter y no los hice andar
     */

    $scope.agregarSiCumple = function (personajeSel,rivalesPosibles,arrayPersonajes){
    	for (var i = 0; i < arrayPersonajes.length; i++){
    		if (
    			personajeSel.estadisticas.Puntaje ==
    			arrayPersonajes[i].estadisticas.Puntaje	)
    			{
    			rivalesPosibles.push(arrayPersonajes[i]);
    			}
    	}
    }
    
    $scope.textoDelServicePostaPrueba = {};
    $scope.obtenerTextoDelServicePostaPrueba = function(){
    	DuelosService.obtenerDatos(
    		function(data) {
    			$scope.textoDelServicePostaPrueba = data;
    			alert($scope.textoDelServicePostaPrueba);
    		}
    	);
    }
    
    $scope.obtenerTextoDelServicePostaPrueba();
    
    $scope.buscarRival = function(personajeSel) {
    	var rivalesPosibles = [];
    	for (var i = 0; i < $scope.rivales.length; i++)
    	{
    		$scope.agregarSiCumple(personajeSel,rivalesPosibles,$scope.rivales[i].personajes)
    	}
    	if (rivalesPosibles.length > 0){
    		$scope.rivalElegido = rivalesPosibles[0];
    		$scope.selectedmodal = "#encontreRivalModal";
    	}
    	else {
    		$scope.selectedmodal = "#mrXModal";
    	}
    };
});
  