angular.module('headerCtrl', [])
	.controller('headerCtrl', ['$scope', '$rootScope', '$location', '$http', headerCtrl]);

// 头部控制器
function headerCtrl($scope, $rootScope, $location, $http) {
	$scope.API = {
		LOGOFF: $rootScope.API.LOGOFF
	}

	$scope.user = {
		src: 'u_01.jpg'
	}

	$scope.events = {
		toggleMenu: function(e) {
			$('.app-sidebar').addClass('fadeInLeft').toggle();
		},
		logOff: function(e) {
			$http
				.get($scope.API.LOGOFF)
				.then(function(res) {
					var res = res.data;
					if(res.data && res.errno === 0) {
						$location.path('/signin');
					}
				});
		}
	}

	$(window).on('resize', function(){
		if (document.documentElement.clientWidth >= 767) {
			$('.app-sidebar').show();
		} else {
			$('.app-sidebar').hide();
		}
	});
}