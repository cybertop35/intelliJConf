angular.module('marcosApp')
		.factory('responseInterceptor', ['$q', '$location', function ($q, $location) {
			return {
				response: function (response) {
                    // return the response or wrap it into a promise if it is blank
					return response || $q.when(response);
				},
				responseError: function (rejection) {
					if (rejection.status === 401) {
						var returnTo = $location.search().returnTo;
						if (returnTo === undefined && $location.path().lastIndexOf('/auth', 0) !== 0) {
							returnTo = $location.path();
						}
						$location.path('/auth/login').search('returnTo', returnTo);
					} 
				return $q.reject(rejection);
				}
			};
		}])
		.config(['$httpProvider', function ($httpProvider) {
			$httpProvider.interceptors.push('responseInterceptor');
		}]);
