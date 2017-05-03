angular.module('appCtrl', ['signinCtrl', 'signupCtrl', 'headerCtrl', 'sidebarCtrl'])
	.controller('appCtrl', ['$scope', '$http', appCtrl])
	.run(['$rootScope', '$http', '$location', run])

// app控制器 => 页面最先加载的控制器
function appCtrl($scope, $http) {
	// 发送异步请求 => 获取侧边栏数据
	$http
		.get("data/sidebar.json")
		.then(function(res) {
			// 数据请求成功后判断状态是否正确
			if (res.data && res.data.errno === 0) {
				// 更新作用域数据
				$scope.menu = res.data.data;
			} else {
				// 抛出错误提示信息
				return console.log('get data/sidebar.json failed.', res);
			}
		})
}

function run ($rootScope, $http, $location) {
	// *****************指定后端API所在端口*****************
	var port = 8080;
	// *****************指定后端数据地址*****************
	// 定义后端API接口base地址常量
	var BASE_URI = "http://localhost:"+port+"/Design/";
	

	// 请求头信息
	$rootScope.CONTENT_TYPE_POST = {'Content-Type':'application/x-www-form-urlencoded'};
	// API
	$rootScope.API = {
		// 用户、登录注册相关API
		SIGNUP: BASE_URI + 'user_signUp.action',
		SIGNIN: BASE_URI + 'user_signIn.action',
		WHETHERLOGGED: BASE_URI + 'user_whetherLogged.action',
		LOGOFF: BASE_URI + 'user_logOff.action',
		USERNAME_ISVALID: BASE_URI + 'user_usernameIsValid.action',
		// 学生增删改查API
		STUDENT: {
			ADD: BASE_URI + 'student_add.action',
			DELETE: BASE_URI + 'student_delete.action',
			UPDATE: BASE_URI + 'student_update.action',
			QUERYALL: BASE_URI + 'student_queryAll.action',
			QUERYONE: BASE_URI + 'student_queryOne.action'
		}
	}


	// 监听signin/signup控制器的username消息
	// 将用户登录成功后传递的用户名存储在作用域下
	$rootScope.$on('username', function(event, data) {
		// 存储登录成功用户名
		$rootScope.username = data;
	})

	// 发送请求 => 检测用户是否登录
	$http
		.get($rootScope.API.WHETHERLOGGED)
		.then(function(res) {
			var res = res.data;
			if (res.data && res.errno === 0) {
				// 如果登录过，将用户名存储到作用域下
				$rootScope.username = res.data.username;

				// 更新路由，跳转主页
				// $location.path('/');
			} else {
				// 否则未登录跳转，登录页面
				$location.path('/signin');
			}
		})

	// 监听状态路由改变事件
	$rootScope.$on('stateChangeSuccess', function() {
		// 状态路由改变时，检测是否登录
		if ($rootScope.username === undefined) {
			// 未登录跳转登录页面
			$location.path('/signin');
		}
	})
}