angular.module('studentCtrl', [])
	.controller('createStudentCtrl', ['$rootScope', '$scope', '$http', '$location', 'util', createStudentCtrl])
	.controller('studentListCtrl', ['$rootScope', '$scope', '$http', '$stateParams', '$location', '$state','util', studentListCtrl])
	.controller('studentDetailCtrl', ['$rootScope', '$scope', '$http', '$stateParams', '$location', 'util', studentDetailCtrl])
	.controller('updateStudentCtrl', ['$rootScope', '$scope', '$http', '$stateParams', '$location', 'util', updateStudentCtrl])

// 创建学生控制器
function createStudentCtrl($rootScope, $scope, $http, $location, util) {
	// 新增学生API
	var ADD_STUDENT_API = $rootScope.API.STUDENT.ADD;
	var CONTENT_TYPE_POST = $rootScope.CONTENT_TYPE_POST;

	// 标题
	$scope.header = {
		title: 'Create Student',
		subTitle: ''
	};

	$scope.columns = {
		id: "学号",
		name: "姓名",
		gender: "性别",
		politicalStatus: "政治面貌",
		birthday: "出生日期",
		profession: "专业"
	};

	// 表单键值
	$scope.data = {
		gender: "男",
		politicalStatus: '群众',
		profession: '计算机网络'
	};

	// baseurl
	$scope.url = '#!/app/student/';

	// 创建学生事件
	$scope.createStudent = function () {
		var student = Object.assign({}, $scope.data);
		student.birthday = +new Date(student.birthday);
		$http
			.post(ADD_STUDENT_API, student, {
				headers: CONTENT_TYPE_POST,
				transformRequest: util.transformRequest
			})
			// 成功回调函数
			.then(function (res) {
				var res = res.data;
				if (res.data === true && res.errno === 0) {
					// 成功跳转学生列表
					$location.path('/app/student/list/1');
				} else {
					return console.log('create failed: ', res);
				}
			})
	}
}

// 学生列表控制器
function studentListCtrl($rootScope, $scope, $http, $stateParams, $location, $state, util) {
	// 查询学生列表API
	var QUERYALL_STUDENT_API = $rootScope.API.STUDENT.QUERYALL;
	// 根据id, 删除学生API
	var DELETE_STUDENT_API = $rootScope.API.STUDENT.DELETE;

	// 标题
	$scope.header = {
		title: 'Student List',
		subTitle: ''
	};

	// 列名和主键对象
	$scope.table = {
		columns: [
			"学号",
			"姓名",
			"性别",
			"政治面貌",
			"出生日期",
			"专业"
		],
		keyName: 'id'
	};

	// baseurl
	$scope.url = '#!/app/student/';

	// 通过状态参数服务 => 获取页码
	$scope.pageNum = +$stateParams.pageNum;

	// 根据页码请求数据
	$http
		.get(QUERYALL_STUDENT_API, {
			// 传递query
			params: {
				pageNum: $scope.pageNum
			}
		})
		// 成功回调函数
		.then(function (res) {
			var res = res.data;
			if (res.data && res.errno === 0) {
				// 成功将数据存储在当前作用域下
				$scope.list = res.data.stuList;
			} else {
				return console.log('get studentlist failed: ', res);
			}
		})

	// 复制分页方法
	$scope.togglePage = util.togglePage;

	$scope.events = {
		delete: function (id) {
			$http
				.get(DELETE_STUDENT_API, {
					params: {
						id: id
					}
				})
				.then(function (res) {
					var res = res.data;
					if (res.data === true && res.errno === 0) {
						$state.reload();// 刷新当前路由
					}
				})
		},
		setId: function(id) {
			$scope.currentId = id;
			// 模态框数据
			$scope.directive = {
				modal: {// 模态框
					targetClass: "directive-modal-sm", // 与触发模态框按钮data-target对应
					sizeClass: "modal-sm", // 模态框尺寸调整类, modal-lg->大模态框(默认)，modal-sm->小模态框
					title: "删除学生信息",
					body: "学号 : <b>"+$scope.currentId+"</b>",
					leftBtn: {// 底部有两个按钮，这个是左按钮
						content: "取消", //具有关闭模态框功能，一般为Close、Cancle、取消等
						styleClass: "btn-success" // BootStrap内置按钮样式类即可，btn-default、btn-info等
					},
					rightBtn: {// 右按钮
						content: "确定删除",
						styleClass: "btn-danger",
						id: $scope.currentId,
						clickEvent: $scope.events.delete
					}
				}
			}
		}

	};


}

// 学生详情控制器
function studentDetailCtrl($rootScope, $scope, $http, $stateParams, $location, util) {
	// 查询单个学生信息
	var QUERYONE_STUDENT_API = $rootScope.API.STUDENT.QUERYONE;
	// 根据id, 删除学生API
	var DELETE_STUDENT_API = $rootScope.API.STUDENT.DELETE;

	// 标题
	$scope.header = {
		title: 'Student Detail',
		subTitle: ''
	};
	$scope.columns = {
		id: "学号",
		name: "姓名",
		gender: "性别",
		politicalStatus: "政治面貌",
		birthday: "出生日期",
		profession: "专业",
		birthplace: "籍贯"
	};
	// 列名数组
	$scope.keys = Object.keys($scope.columns);
	$scope.currentId = $stateParams.studentId;
	$scope.url = '#!/app/student/';
	$scope.events = {
		delete: function (id) {
			$http
				.get(DELETE_STUDENT_API, {
					params: {
						id: id
					}
				})
				.then(function (res) {
					var res = res.data;
					if (res.data === true && res.errno === 0) {
						console.log("删除成功");
						$location.path('/app/student/list/1');
					}
				})
		}
	};
	
	
	// 模态框数据
	$scope.directive = {
	 	modal: {// 模态框
	 		targetClass: "directive-modal-sm", // 与触发模态框按钮data-target对应
	 		sizeClass: "modal-sm", // 模态框尺寸调整类, modal-lg->大模态框(默认)，modal-sm->小模态框
	 		title: "删除学生信息",
	 		body: "学号 : <b>"+$scope.currentId+"</b>",
	 		leftBtn: {// 底部有两个按钮，这个是左按钮
	 			content: "取消", //具有关闭模态框功能，一般为Close、Cancle、取消等
	 			styleClass: "btn-success" // BootStrap内置按钮样式类即可，btn-default、btn-info等
	 		},
	 		rightBtn: {// 右按钮
	 			content: "确定删除",
	 			styleClass: "btn-danger",
				id: $scope.currentId,
				clickEvent: $scope.events.delete
	 		}
	 	}
	 }



	// 根据学生id请求数据
	$http
		.get(QUERYONE_STUDENT_API, {
			params: {
				// 传递id
				id: $scope.currentId
			}
		})
		// 成功回调函数
		.then(function (res) {
			var res = res.data;
			if (res.data && res.errno === 0) {
				// 成功将学生信息存储
				$scope.stu = res.data.stu;
			} else {
				return console.log('get studentdetail failed: ', res);
			}
		})
}

// 修改学生控制器
function updateStudentCtrl($rootScope, $scope, $http, $stateParams, $location, util) {
	var CONTENT_TYPE_POST = $rootScope.CONTENT_TYPE_POST;
	// 修改学生API
	var UPDATE_STUDENT_API = $rootScope.API.STUDENT.UPDATE;
	// 查询单个学生API
	var QUERYONE_STUDENT_API = $rootScope.API.STUDENT.QUERYONE;

	$scope.url = '#!/app/student/';


	$scope.events = {
		updateStudent: function() {
			var student = Object.assign({}, $scope.data);
			student.birthday = +new Date(student.birthday);
			$http
				.post(UPDATE_STUDENT_API, student, {
					headers: CONTENT_TYPE_POST,
					transformRequest: util.transformRequest
				})
				.then(function(res) {
					var res = res.data;
					if (res.data === true && res.errno === 0) {
						$location.path('/app/student/list/1');
					} else {
						return console.log("update student failed: ", res);
					}
				})	
		},
		queryOne: function() {
			$http
				.get(QUERYONE_STUDENT_API, {
					params: {
						id: $stateParams.studentId
					}
				})
				.then(function(res) {
					var res = res.data;
					if (res.data && res.errno === 0) {
						res.data.stu.birthday = new Date(res.data.stu.birthday);
						$scope.data = res.data.stu;
					}
				})
		}
	}

	$scope.events.queryOne();
}