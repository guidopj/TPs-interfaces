var app = angular.module('encuestaApp')
app.service('EncuestaService',function($http){

	return{
		
		/*
		 * $http.post('/login', $scope.userInfo)
        .success(function (data) {
            //Check if the authentication was a success or a failure
            //Successful POST here does not indicate success of authentication
            if (data.status === "authentication success") {

                //Proceed to load the dashboard for the user                    
                $location.path('/dashboard');

            } else if (data.status === "authentication error") {
                //Inform user of the error
                $scope.errorMessage = data.error;
                $scope.showErrorMessage = true;
            }

        })
        .error(function (error) {
            $scope.errorMessage = "Error while attempting to authenticate. Check  log.";
            $scope.showErrorMessage = true;

        });
    };

}]);
		 * */
	
//		responderEncuesta:function(respuesta,callback){
//			$http.get('/responder',respuesta).success(callback);
//		},

//		getCarreras:function(){
//			//obtienelalistadecarrerasconeldetalledelasmateriasylodevuelve
//		},
//
//		getTurnos:function(){
//			//obtienelalistadeturnosylodevuelve
//	
//		}
	}
});