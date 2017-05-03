<table class="table table-hover">
	<thead class="list-title">
		<th ng-repeat="column in table.columns" ng-bind="column"></th>
	</thead>

	<tbody>
		<tr ng-repeat="item in list">
			<td  ng-bind="item.id"></td>
			<td  ng-bind="item.name"></td>
			<td  ng-bind="item.gender"></td>
			<td  ng-bind="item.politicalStatus"></td>
			<td  ng-bind="item.birthday"></td>
			<td  ng-bind="item.profession"></td>
			
			<td>
				<a class="btn btn-default" ng-href="{{url}}detail/{{item.id}}">详情</a>
				<a class="btn btn-default" ng-href="{{url}}update/{{item.id}}">修改</a>
				<a class="btn btn-danger" ng-click="events.setId(item.id)" data-toggle="modal" data-target=".{{directive.modal.targetClass}}">删除</a>
			</td>
		</tr>
	</tbody>
</table>