<section class="container cnt-inner m-n">
	<content-title></content-title>
	<div class="main-cnt">
		<div class="panel panel-default">
			<div class="panel-heading">Please fill out student information</div>
			<div class="panel-body">
				<form name="updateForm" class="updateForm-container" ng-submit="events.updateStudent()">

					<div class="row">
						<div class="col-sm-2">
							<label for="name">姓名</label>
						</div>
						<div class="col-sm-6">
							<input class="form-control" type="text" id="name" placeholder="请输入学生姓名" ng-required="true" ng-minLength="2" ng-model="data.name" name="name">
						</div>
						<div class="col-sm-4" ng-show="updateForm.name.$dirty && updateForm.name.$invalid"><span class="alert alert-warning">姓名不得少于2位</span></div>
					</div>

					<div class="row">
						<div class="col-sm-2">
							<label class="" for="">性别</label>
						</div>
						
						<div class="col-sm-6">
							<label for="male" class="radio-label">
							<input  type="radio" id="male" name="gender" ng-model="data.gender" value="男"><i
							ng-class="{
							'checked': data.gender === '男',
							'b-d-sl': data.gender === '男'
							}"></i>男</label>

							<label for="female" class="radio-label"><input  type="radio" id="female" name="gender" ng-model="data.gender" value="女"><i
							ng-class="{
							'checked': data.gender === '女',
							'b-d-sl': data.gender === '女'
							}"></i>女</label>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-2">
							<label class=" " for="politicalStatus">政治面貌</label>
						</div>
						
						<div class="col-sm-6">
							<select class="form-control" id="politicalStatus" ng-model="data.politicalStatus" name="politicalStatus">
								<option value="群众">群众</option>
								<option value="团员">团员</option>
								<option value="党员">党员</option>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-2">
							<label class="" for="birthday">出生日期</label>
						</div>
						
						<div class="col-sm-6">
							<input class="form-control" type="date" id="birthday" ng-model="data.birthday" name="birthday"></input>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-2">
							<label for="profession">专业</label>
						</div>
						
						<div class="col-sm-6">
							<select class="form-control" id="profession" ng-model="data.profession" name="profession">
								<option value="计算机网络">计算机网络</option>
								<option value="软件工程">软件工程</option>
								<option value="统计学">统计学</option>
								<option value="会计">会计</option>
								<option value="信息安全">信息安全</option>
								<option value="工商企业管理">工商企业管理</option>
								<option value="政法学">政法学</option>
								<option value="中医学">中医学</option>
							</select>
						</div>
					</div>


					<div class="row">
						<div class="col-sm-2 col-sm-offset-2">
							<input  type="submit" value="修改学生" class="btn btn-success">
						</div>

						<div class="col-sm-2">
							<a class="btn btn-default" ng-href="{{url}}list/1">返回列表</a>
						</div>

						<div class="col-sm-2">
							<input  type="button" value="重置修改" class="btn btn-warning" ng-click="events.queryOne()">
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
</section>