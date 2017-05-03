angular.module('signupCtrl', [])
	.controller('signupCtrl', ['$rootScope', '$scope', '$http', '$location', 'util', signupCtrl])

function signupCtrl($rootScope, $scope, $http, $location, util) {
	// 用户注册api
	var SIGNUP_API = $rootScope.API.SIGNUP;
	// 用户名是否则被注册api
	var USERNAME_ISVALID_API = $rootScope.API.USERNAME_ISVALID;
	var CONTENT_TYPE_POST = $rootScope.CONTENT_TYPE_POST;
	var ERR_USER_EXISTED = "用户名太火了，请换一个";

	$scope.hasErr = false;

	// 用户注册
	$scope.goSignUp = function() {
		// 注册请求
		$http
			.post(SIGNUP_API, $scope.data, {
				headers: CONTENT_TYPE_POST,
				transformRequest: util.transformRequest
			})
			.then(function(res) {
				var res = res.data;
				if (res.data && res.errno === 0) {
					// 向父组件传递消息，注册成功，自动登录
					$scope.$emit('username', res.data.username);
					// 跳转首页
					$location.path('/app/dashboard');
				} else if (res.errno === 1){
					// res.errno => 1(用户名存在)
					// 更新错误提示
					$scope.errInfo = ERR_USER_EXISTED;
					// 更改注册状态
					$scope.hasErr = true;
				} else if (res.errno === 2) {
					return ;
				} else {
					return console.log('signup failed: ', res);
				}
			})
	}

	// 当注册请求用户名已存在时，再次点击用户名对应的文本框时，重置错误提示
	// $scope.reset = function() {
	// 	$scope.hasErr ? ($scope.hasErr = false) : '';
	// 	console.log($scope.hasErr);
	// }
	// ng-click="reset()"
	// --以上代码等同于---
	// ng-click="hasErr = false;"

	// 发送请求检测用户名是否合法
	$scope.usernameIsValid = function() {
		$http
			.post(USERNAME_ISVALID_API, $scope.data, {
				headers: CONTENT_TYPE_POST,
				transformRequest: util.transformRequest
			})
			.then(function(res) {
				var res = res.data;
				if (res.data && res.errno === 1) {
					// 更新错误提示
					$scope.errInfo = ERR_USER_EXISTED;
					// 更改注册状态
					$scope.hasErr = true;
				} else if (res.data && res.errno === 2) {
					$scope.hasErr = false;
					$scope.errInfo = '';
				}
			})
	}

	$scope.isCheck = function($event) {}
	$scope.goToCreate = function() {}
}