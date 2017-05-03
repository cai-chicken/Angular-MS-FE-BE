angular.module('directive', [])
	// 主体内容标题栏 => conetent-title
	.directive('contentTitle', function() {
		return {
			restrict: 'E',
			templateUrl: 'js/directive/content-title.tpl',
			link: function($scope, $element, $attrs) { }
			// 替换指令容器元素
			,replace: true
		}
	})
	
	// 表格 => content-table
	.directive('contentTable', function() {
		return {
			restrict: 'E',
			templateUrl: 'js/directive/content-table.tpl',
			link: function($scope, $element, $attrs) { }
			// 替换指令容器元素
			,replace: true
		}
	})

	// 分页 => content-paging
	.directive('contentPaging', function() {
		return {
			'restrict': 'E',
			templateUrl: 'js/directive/content-paging.tpl',
			link: function($scope, $element, $attrs) {}
			// 替换指令容器元素
			,replace: true
		}
	})
	
	/**
	 * 模态框内容由数据控制
	 * <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".directive-modal-sm">模态框</button>
	 * diretive: {
	 * 	modal: {// 模态框
	 * 		targetClass: "directive-modal-sm", // 与触发模态框按钮data-target对应
	 * 		sizeClass: "modal-sm", // 模态框尺寸调整类, modal-lg->大模态框(默认)，modal-sm->小模态框
	 * 		title: "模态框标题",
	 * 		body: "模态框主体内容",
	 * 		leftBtn: {// 底部有两个按钮，这个是左按钮
	 * 			content: "左按钮内容", //具有关闭模态框功能，一般为Close、Cancle、取消等
	 * 			styleClass: "btn-success" // BootStrap内置按钮样式类即可，btn-default、btn-info等
	 * 		},
	 * 		rightBtn: {// 右按钮
	 * 			content: "确定删除",
	 * 			styleClass: "btn-danger"
	 * 		}
	 * 	}
	 * }
	 */
	// 模态框 => content-modal-box
	.directive('contentModalBox', function() {
		return {
			'restrict': 'E',
			templateUrl: 'js/directive/content-modal-box.tpl',
			link: function($scope, $element, $attrs) {}
			// 替换指令容器元素
			,replace: true
		}
	})