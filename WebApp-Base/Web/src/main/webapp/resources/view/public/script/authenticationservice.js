angular.module('marcosApp')
		.service('AuthenticationService', ['$http', function ($http) {

			this._defaultRedirection = '/resume/list';
			this._apiUrl = '/api/authentication';

			this.getDefaultRedirection = function () {
				return this._defaultRedirection;
			};

			this.login = function (username, password, rememberMe) {
				return $http({
					method: 'POST',
					url: this._apiUrl + '/login',
					data: $.param({'remember-me': rememberMe }),
					headers: {
						'Authorization': 'Basic ' + btoa(username + ':' + password),
						'Content-Type': 'application/x-www-form-urlencoded'
					}
				});
			};

			this.logout = function () {
				return $http({
					method: 'POST',
					url: this._apiUrl + '/logout',
					headers: {
						'Content-Type': 'application/x-www-form-urlencoded'
					}
				});
			};

		}]);
