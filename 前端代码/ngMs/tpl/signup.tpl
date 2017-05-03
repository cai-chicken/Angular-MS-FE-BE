<div class="app-register" ng-controller="signupCtrl">
	<div class="register-wrapper">
		<h4>User Register</h4>
		<div>
			<div><strong>Sign up to find interesting thing</strong></div>
			<form name="signUpForm" ng-submit="goSignUp()">
				<div class="form-group col-sm-12"
					ng-class="{
						'has-error': signUpForm.username.$dirty && signUpForm.username.$invalid || hasErr,
						'has-success': signUpForm.username.$dirty && signUpForm.username.$valid
					}"
					>
					<input type="text" class="form-control" ng-required="true" ng-model="data.username" name="username" id="user_name" placeholder="Username (3 ~ 16)" ng-maxLength="16" ng-minLength="3" ng-click="hasErr = false;" ng-blur="(signUpForm.username.$dirty && signUpForm.username.$valid) && usernameIsValid()">
					<span class="glyphicon  form-control-feedback" aria-hidden="true"
					ng-class="{
						'glyphicon-remove': signUpForm.username.$dirty && signUpForm.username.$invalid || hasErr,
						'glyphicon-ok': signUpForm.username.$dirty && signUpForm.username.$valid
					}"
					></span>
				</div>

				<div class="danger-info">
					<span class="text-danger" ng-bind="errInfo"></span>
				</div>

				<div class="form-group col-sm-12"
					ng-class="{
						'has-error': signUpForm.password.$dirty && signUpForm.password.$invalid,
						'has-success': signUpForm.password.$dirty && signUpForm.password.$valid
					}"
					>
					<input type="password" class=" form-control" ng-required="true" ng-model="data.password" name="password" id="pass_word" placeholder="Password (6 ~ 30)" ng-maxLength="30" ng-minLength="6">
					<span class="glyphicon form-control-feedback" aria-hidden="true"
					ng-class="{
						'glyphicon-remove': signUpForm.password.$dirty && signUpForm.password.$invalid,
						'glyphicon-ok': signUpForm.password.$dirty && signUpForm.password.$valid
					}"
					></span>
				</div>

				<div class="form-group col-sm-12"
					ng-class="{
						'has-error': signUpForm.cfmpassword.$dirty && data.cfmpassword !== data.password,
						'has-success': signUpForm.cfmpassword.$dirty && signUpForm.cfmpassword.$valid && 
						data.cfmpassword === data.password
					}"
					>
					<input type="password" class=" form-control" ng-required="true" ng-model="data.cfmpassword" name="cfmpassword" id="pass_word" placeholder="Confirm Password" ng-maxLength="30" ng-minLength="6">
					<span class="glyphicon form-control-feedback" aria-hidden="true"
					ng-class="{
						'glyphicon-remove': signUpForm.cfmpassword.$dirty &&
						data.cfmpassword !== data.password,
						'glyphicon-ok': signUpForm.cfmpassword.$dirty && signUpForm.cfmpassword.$valid && 
						data.cfmpassword === data.password
					}"
					></span>
				</div>

				<div class="col-sm-12 check_box">
					<label for="check_box" class="label_box" ><input type="checkbox" ng-required="true" ng-model="ck.checkbox" name="checkbox" id="check_box" placeholder="checkbox" ng-click="isCheck($event)">Agree the terms and policy<i ng-class="{
					'checked': ck.checkbox,
					'b-d-sh': signUpForm.checkbox.$dirty && signUpForm.checkbox.$invalid,
					'b-d-sl': signUpForm.checkbox.$dirty && signUpForm.checkbox.$valid
					}"></i></label>
				</div>

				<div class="form-group col-sm-12">
					<input ng-disabled="signUpForm.$invalid || data.cfmpassword !== data.password || hasErr" type="submit" class="form-control btn btn-primary" value="Sign up"></input>
				</div>

				<div class="create-user">
					<span>Already have an account?</span>
					<div class="form-group col-sm-12">
						<a href="#!/signin" ng-click="goToCreate()">
							<input type="button" class="btn btn-default form-control" value="Sign in">
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