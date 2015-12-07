//    this.obtenerPosiciones = function(){
//    	DuelosService.obtenerPosifciones(
//    		function(data) {
//    			self.posicionesDuelos = data.data;
//    		}
//    	);
//    }
//	

app.controller('ResponderCtrl',function($scope,$location,$routeParams,EncuestaService){

	$scope.turnos=EncuestaService.getTurnos();

	$scope.carreras=EncuestaService.getCarreras();

	$scope.respuesta={mail:$routeParams.mail,materias:[]};

	$scope.agregarMateria=function(){
		$scope.respuesta.materias.push({materia:$scope.materiaSeleccionada,turno:$scope.turnoSeleccionado});
		$scope.materiaSeleccionada={};
		$scope.turnoSeleccionado={};
	}

	$scope.contestar=function(){
		//Checkeamosloscamposobligatorios
		if($scope.respuesta.materias.length<=0){
			alert('Debe ingresar materias para continuar');
			return;
		}

		if(!$scope.carreraSeleccionada){
			alert('Debe seleccionar una carrera');
			return;
		}

		if(!$scope.respuesta.anioIngreso){
			alert('Debe	indicar el ano de ingreso a la facultad');
			return;
		}

		//Todo OK,impactamos en el server

		$scope.respuesta.carreraId=$scope.carreraSeleccionada.id;
		EncuestaService.responderEncuesta($scope.respuesta,function(data){
			$location.path('gracias');
		});
	}	
});