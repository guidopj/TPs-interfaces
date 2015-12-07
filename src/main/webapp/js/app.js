angular.module('encuestaApp', ['ngRoute'])

.config(function($routeProvider){
	$routeProvider.when('/',{templateUrl:'pages/login.html',controller:'LoginCtrl'})
	.when('/responder/:mail',{templateUrl:'pages/responder.html',controller :'ResponderCtrl'})
	.when('/gracias',{templateUrl:'pages/gracias.html'})
	.otherwise({redirectTo:'/'});
})

.controller('LoginCtrl',function($scope,$location){

	$scope.autenticar=function(){
		$location.path('responder/'+$scope.user.mail);
	}
});

