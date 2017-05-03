package service;

import java.util.HashMap;

import entity.Users;

/**
 * 用户业务逻辑接口
 * @author Administrator
 *
 */
public interface usersDao {
	
	// 用户登录方法
	public  HashMap<String, Object> userSignin(Users user);
	// 用户注册方法
	public HashMap<String, Object> userSignup(Users user , Boolean ...isCheck);
}
