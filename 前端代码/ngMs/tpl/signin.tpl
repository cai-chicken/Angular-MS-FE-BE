<div class="app-login" ng-controller="signinCtrl">
	<div class="login-wrapper">
		<h4>User Login</h4>
		<div>
			<div><strong>Sign in to get in touch</strong></div>
			<form name="signInForm" ng-submit="goSignIn()">
				<div class="form-group col-sm-12"
					ng-class="{
						'has-error': signInForm.username.$dirty && signInForm.username.$invalid || status.uStatus !== 0,
						'has-success': signInForm.username.$dirty && signInForm.username.$valid && status.uStatus === 0
					}"
					>
					<input type="text" class="form-control" ng-required="true" ng-model="data.username" name="username" id="user_name" placeholder="Username" ng-minLength="3" ng-maxLength="16" ng-click="status.uStatus = 0;" ng-blur="(signInForm.username.$dirty && signInForm.username.$valid) && usernameIsValid()">
					<span class="glyphicon  form-control-feedback" aria-hidden="true"
					ng-class="{
						'glyphicon-remove': signInForm.username.$dirty && signInForm.username.$invalid || status.uStatus !== 0,
						'glyphicon-ok': signInForm.username.$dirty && signInForm.username.$valid && status.uStatus === 0
					}"
					></span>
				</div>

				<div class="danger-info">
					<span class="text-danger" ng-bind="status.uStatus ? info['u'] : ''"></span>
				</div>

				<div class="form-group col-sm-12"
					ng-class="{
						'has-error': signInForm.password.$dirty && signInForm.password.$invalid || status.pStatus !== 0,
						'has-success': signInForm.password.$dirty && signInForm.password.$valid && status.pStatus === 0	
					}"
					>
					<input type="password" class=" form-control" ng-required="true" ng-model="data.password" name="password" id="pass_word" placeholder="Password" ng-minLength="6" ng-maxLength="30" ng-click="status.pStatus = 0;">
					<span class="glyphicon form-control-feedback" aria-hidden="true"
					ng-class="{
						'glyphicon-remove': signInForm.password.$dirty && signInForm.password.$invalid || status.pStatus !== 0,
						'glyphicon-ok': signInForm.password.$dirty && signInForm.password.$valid && status.pStatus === 0
					}"
					></span>
				</div>

				<div class="danger-info">
					<span class="text-danger" ng-bind="status.pStatus ? info['p'] : ''"></span>
				</div>

				<div class="form-group col-sm-12">
					<input ng-disabled="signInForm.$invalid" type="submit" class="form-control btn btn-primary" value="Log in"></input>
				</div>

				<div class="create-user">
					<span>Do not have an account?</span>
					<div class="form-group col-sm-12">
						<a href="#!/signup">
							<input type="button" class="btn btn-default form-control" value="Create an account">
						</a>
					</div>
				</div>
			</form>
			<div class="copyright text-qh">
				<p>Web app framework base on Bootstrap and AngularJS</p>
				<span>© 2017</span>
			</div>
		</div>
	</div>
</div>