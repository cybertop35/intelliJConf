angular.module('marcosApp')

.config([ '$routeProvider', function($routeProvider) {

	var templatePrefix = 'resources/view/public';
	$routeProvider.when('/auth/login', {
		templateUrl : templatePrefix + '/login-view.html',
		controller : 'LoginController'
	}).when('/auth/passwordReset', {
		templateUrl : templatePrefix + '/password-reset-view.html',
		controller : ''
	}).when('/auth/logout', {
		template : '',
		controller : 'LogoutController'
	}).when('/', {
		templateUrl : templatePrefix + '/home-view.html',
		controller : ''
	});
	
} ])

.controller(
		'LoginController',
		['$rootScope', '$scope', '$http', '$location','AuthenticationService',function($rootScope, $scope, $http, $location,AuthenticationService) {

			$scope.credentials = {
		            username: '',
		            password: '',
		            rememberMe: false
		        };
			$scope.loginHasBeenCalled = false;
			
			$scope.login = function() {
				$scope.loginHasBeenCalled = true;
	            if (!$scope.credentials.username || !$scope.credentials.password) {
	                return;
	            }
	            
	            
	            AuthenticationService.login($scope.credentials.username, $scope.credentials.password, $scope.credentials.rememberMe)
                .then(function () {
                    var redirectTo = $location.search().returnTo;
                    if (redirectTo === undefined) {
                        redirectTo = AuthenticationService.getDefaultRedirection();
                    }
                    $location.path(redirectTo).search({});
                }, function (rejection) {
                    if (rejection.status === 401) {
                        toaster.pop('error', 'Login failed', 'Incorrect User/Password');
                    } else {
                        toaster.pop('error', 'Error processing request', rejection.statusText + ' [' + rejection.status + ']');
                    }
                });
	            
	            
			};

		} ])

.controller('LogoutController',
		[ '$scope', '$location', function($scope, $location) {

		} ]);