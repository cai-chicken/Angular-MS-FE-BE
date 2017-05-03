<section class="container cnt-inner m-n">
	<content-title></content-title>
	<div class="main-cnt">
		<div class="panel panel-default">
			<div class="panel-heading">Student information details</div>
			<table class="table">
				<tr ng-repeat="val in columns">
					<th class="col-sm-2">{{val}}</th>
					<td class="col-sm-8">{{stu[keys[$index]]}}</td>
				</tr>
				<tr>
					<td class="col-sm-10" colspan="2">
						<a class="btn btn-default" ng-href="{{url}}list/1">返回列表</a>
						<a class="btn btn-default" ng-href="{{url}}update/{{currentId}}">修改</a>
						<a class="btn btn-danger" data-toggle="modal" data-target=".{{directive.modal.targetClass}}">删除</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</section>

<content-modal-box></content-modal-box>