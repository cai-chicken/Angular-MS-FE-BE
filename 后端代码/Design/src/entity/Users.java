package entity;

import java.io.Serializable;

/**
 * 用户实体类
 * @author Administrator
 *
 */
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uid;//用户编号
	private String username;//用户名
	private String password;//用户密码
	
	public Users() {
	}

	public Users(int uid, String username, String password) {
		//super();
		this.uid = uid;
		this.username = username;
		this.password = password;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
