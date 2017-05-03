angular.module('configRouter', [])
	.config(['$httpProvider', '$stateProvider', '$urlRouterProvider', config])

function config($httpProvider, $stateProvider, $urlRouterProvider) {
	// 请求时向服务器端发送cookie
	$httpProvider.defaults.withCredentials = true;

	$urlRouterProvider
		// 重定向路由
		// .when('/app', '/signin')
		// 指定默认路由
		.otherwise('/app/dashboard')
	
	$stateProvider
		// 配置状态路由 
		// state(stateName, stateObj)
		// this => window
		.state('app', {
			// 此状态不会被显性的激活，只能被子状态隐性激活
			abstract: true,
			// 状态路由规则
			url: '/app',
			// 模板地址
			templateUrl: 'tpl/app.tpl'
			// 控制器
			,controller: 'appCtrl'
		})
		// 用户登录
		.state('signin', {
			url: '/signin',
			templateUrl: 'tpl/signin.tpl',
			controller: 'signinCtrl'
		})
		// 用户注册
		.state('signup', {
			url: '/signup',
			templateUrl: 'tpl/signup.tpl',
			controller: 'signupCtrl'
		})
		
		// 仪表盘
		.state('app.dashboard', {
			url: '/dashboard',
			templateUrl: 'tpl/dashboard.tpl',
			controller: 'dashboardCtrl'
		})
		// ---student模块---
		.state('app.createstudent', {
			url: '/student/create',
			templateUrl: 'tpl/create/create.student.tpl',
			controller: 'createStudentCtrl'
		})
		.state('app.studentlist', {
			url: '/student/list/:pageNum',
			templateUrl: 'tpl/list/list.student.tpl',
			controller: 'studentListCtrl'
		})
		.state('app.studentdetail', {
			url: '/student/detail/:studentId',
			templateUrl: 'tpl/detail/detail.student.tpl',
			controller: 'studentDetailCtrl'
		})
		.state('app.updatestudent', {
			url: '/student/update/:studentId',
			templateUrl: 'tpl/update/update.student.tpl',
			controller: 'updateStudentCtrl'
		})
		// ---teacher模块---
		.state('app.createteacher', {
			url: '/teacher/create',
			templateUrl: 'tpl/create/create.teacher.tpl',
			controller: 'createTeacherCtrl'
		})
		.state('app.teacherlist', {
			url: '/teacher/list/:pageNum',
			templateUrl: 'tpl/list/list.teacher.tpl',
			controller: 'teacherListCtrl'
		})
		.state('app.teacherdetail', {
			url: '/teacher/detail/:teacherId',
			templateUrl: 'tpl/detail/detail.teacher.tpl',
			controller: 'teacherDetailCtrl'
		})

	
}