angular.module('signinCtrl', [])
	.controller('signinCtrl', ['$rootScope', '$scope', '$http', '$location', 'util', signinCtrl])

// 用户登录控制器
function signinCtrl($rootScope, $scope, $http, $location, util) {
	// 用户登录
	var SIGNIN_API = $rootScope.API.SIGNIN;
	// 用户名是否存在api
	var USERNAME_ISVALID_API = $rootScope.API.USERNAME_ISVALID;
	var CONTENT_TYPE_POST = $rootScope.CONTENT_TYPE_POST;
	var INITIAL_STATUS = {
		"uStatus": 0,
		"pStatus": 0
	};

	// 提示信息对象
	$scope.info = {
		// 用户名相关
		u: "用户名不存在",
		// 密码相关
		p: '密码有误'
	}

	// 用户名密码状态对象
	$scope.status = INITIAL_STATUS;

	// 用户登录事件
	$scope.goSignIn = function(e) {
		$http
			// 发送登录异步请求，传递登录信息
			.post(SIGNIN_API, $scope.data, {
				// 指定请求头
				headers: CONTENT_TYPE_POST,
				// 请求数据格式化
				transformRequest: util.transformRequest
			})
			.then(function(res) {
				var res = res.data;
				if (res.data && res.errno === 0) {
					// 向父组件传递消息
					$scope.$emit('username', res.data.username);
					// 跳转路由
					$location.path('/');
				} else if (res.data && res.errno === 1){
					$scope.status = res.data;
				}
			})
	}

	$scope.usernameIsValid = function() {
		$http
			.post(USERNAME_ISVALID_API, $scope.data, {
				headers: CONTENT_TYPE_POST,
				transformRequest: util.transformRequest
			})
			.then(function(res) {
				var res = res.data;
				if (res.data && res.errno === 2) {
					$scope.status.uStatus = 1;
				}
			})
	}

	// 键盘事件 => 重置用户&密码状态对象
	$scope.reset = function() {
		$scope.status = INITIAL_STATUS;
	}
}