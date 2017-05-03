// 启动应用程序，引入第三方模块和依赖模块
angular.module('myApp', 
	['ui.router', 
	'ngSanitize',
	'configRouter',
	'appCtrl',
	'utilService',
	'directive'
])