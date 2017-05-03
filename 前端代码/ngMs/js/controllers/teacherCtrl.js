angular.module('teacherCtrl', [])
	.controller('createTeacherCtrl', ['$scope', '$http', '$location', createTeacherCtrl])
	.controller('teacherListCtrl', ['$scope', '$http', '$stateParams', 'util', teacherListCtrl])
	.controller('teacherDetailCtrl', ['$scope', '$http', '$stateParams', 'util', teacherDetailCtrl])

// 创建教师控制器
function createTeacherCtrl($scope, $http, $location) {
	// 标题
	$scope.header = {
		title: 'Create Teacher',
		subTitle: ''
	}

	// 键值对象
	$scope.data = {
		specialty: 'tj',
		branch: 'tj',
		education: 'bk',
		post: 'jx',
		gender: 'male'
	}

	// hash baseurl
	$scope.url = '#!/app/teacher/';

	// 创建用户事件
	$scope.createTeacher = function() {
		$http
			.post('action/create.teacher.php', $scope.data)
			.then(function(res) {
				var res = res.data;
				if (res.data && res.errno === 0) {
					// 创建成功跳转列表页
					$location.path('/app/teacherlist/1');
				}
			})
			.catch(function(err) {
				return console.log('create teacher failed: ', err);
			})
	}
}

// 教师列表控制器
function teacherListCtrl($scope, $http, $stateParams, util) {
	// 标题
	$scope.header = {
		title: 'Teacher List',
		subTitle: ''
	}

	// 列名和主键
	$scope.table = {
		columns:[
			'TeacherId',
			'Name',
			'Password',
			'Gender',
			'DateCreated',
			'Post',
			'Content',
			'Action'
		],
		keyName: 'id'
	}

	// hash baseurl
	$scope.url = '#!/app/teacher/';

	// 获取页码
	// + => 字符串转换数组
	$scope.pageNum = +$stateParams.pageNum;

	// 根据页码请求数据
	$http
		.get('action/list.teacher.php', {
			params: {
				pageNum: $scope.pageNum
			}
		})
		.then(function(res) {
			var res = res.data;
			if (res.data && res.errno == 0) {
				// 更新作用域数据
				$scope.list = res.data;
			}
		})
		.catch(function(err) {
			return console.log('get teacherlist failed: ', err);
		})

	// 赋值工具服务的方法 => 分页切换事件
	$scope.togglePage = util.togglePage;
}

// 教师详情控制器
function teacherDetailCtrl($scope, $http, $stateParams, util) {
	// 标题
	$scope.header = {
		title: 'Teacher Detail',
		subTitle: ''
	}

	// hash baseurl
	$scope.url = '#!/app/teacher/';

	// 获取用户id
	$scope.teacherId = $stateParams.teacherId;

	// 根据id请求数据
	$http
		.get('action/detail.teacher.php', {
			params: {
				teacherId: $scope.teacherId
			}
		})
		.then(function(res) {
			var res = res.data;
			if (res.data && res.errno == 0) {
				// 将请求的数据存储到作用域中
				$scope.teacher = res.data;

				$scope.keys = util.createKeysArr(res.data);
			}
		})
		.catch(function(err) {
			return console.log('get teacherdetail failed: ', err);
		})
}