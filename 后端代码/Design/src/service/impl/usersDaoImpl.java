package service.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.myHibernateSessionFactory;
import entity.Users;
import service.usersDao;
import util.CloseUtil;

public class usersDaoImpl implements usersDao{
	
	private CloseUtil util = new CloseUtil();
	// 数据库查询对象
	private Query query;
	// Hibernate会话对象
	private Session session;
	// 事务对象
	private Transaction ts;
	// HashMap对象
	private HashMap<String, Object> map;
	// 状态、查询语句、要登录的用户名和密码
	private String hql, username, password;
	// 要登录用户的用户名和密码状态，0表示正常，1表示有异常
	private int uStatus = 0, pStatus = 0;
	
	//@SuppressWarnings("null")//Transaction警告保持静默
	@Override
	public HashMap<String, Object> userSignin(Users u) {
		//事务对象初始化
		ts = null;
		try {
			map = new HashMap<String, Object>();
			// 获取用户名和密码
			username = u.getUsername();
			password = u.getPassword();
			
			// 从会话工厂获取session对象
			session = myHibernateSessionFactory.getSessionFactory().getCurrentSession();
			// 开启事务
			ts = session.beginTransaction();
			// hql语句
			hql = "from Users where username= ? ";
			// 创建query对象
			query = session.createQuery(hql);
			// 替换参数 0 => hql语句中的第1个?
			query.setParameter(0, username);
			
			@SuppressWarnings("unchecked")//query.list()警告保持静默
			// 获取查询结果列表
			List<Users> list = query.list();
			// 提交事务
			ts.commit();
			
			
			// 根据username查询数据库，判断是否存在指定用户
			if(list.size() > 0) {
				// 存在则继续判断密码是否匹配
				// 匹配返回"true"
				System.out.println(list.get(0).getPassword()+"---"+ password);
				if(password.equals(list.get(0).getPassword())) {
					map.put("status", true);
					map.put("username", username);
					return map;
				} else {
					// 不匹配提示密码错误
					pStatus = 1;
					// status = "密码有误";
				}
			} else {
				// 不存在更改状态，提示用户名不存在
				uStatus = 1;
				// status = "用户名不存在";
			}
			
			// map.put("status", status);
			map.put("uStatus", uStatus);
			map.put("pStatus", pStatus);
			return map;
		} catch(Exception e) {
			e.printStackTrace();
			map.put("status", "登录出现异常");
			map.put("error", e);
			return map;
		} finally {
			util.close(ts);
		}
	}

	@Override
	public HashMap<String, Object> userSignup(Users user, Boolean ...isCheck) {
		ts = null;
		try {
			map = new HashMap<String, Object>();
			username = user.getUsername();
			
			// 获取session会话对象
			session = myHibernateSessionFactory.getSessionFactory().getCurrentSession();
			// 开启事务
			ts = session.beginTransaction();
			// hql语句
			hql = "from Users where username= ? ";
			// 根据hql语句和session会话对象，查询数据库
			query = session.createQuery(hql);
			// 传值覆盖hql语句中的 ? 
			query.setParameter(0, user.getUsername());
			
			// 警告保持静默
			@SuppressWarnings("unchecked")
			// 获取查询结果集列表
			List<Users> list = query.list();
			// 结果集长度为空，表示数据库中不存在要注册的用户信息
			if (list.size()==0 && isCheck.length == 0) {
				// 将用户对象保存在session会话对象中
				session.save(user);
				// 提交事务
				ts.commit();
				// 关闭数据库会话
				// session.close();
				// 将用户名保存在要返回的HashMap对象中
				map.put("username", username);
				// 注册成功后，将存储用户名对象的Map返回
				return map;
			} else if (list.size() == 0 && isCheck[0] == true){
				ts.commit();
				map.put("valid", true);
				return map;
			} else if (list.size() > 0) {
				ts.commit();
				// 错误信息提示
				map.put("status", "用户名已存在！");
				return map;
			} else {
				ts.commit();
				System.out.println(isCheck[0] == true);
				return map;
			}
		} catch (Exception e) {
			// 控制台输出异常信息
			e.printStackTrace();
			map.put("status", "注册时异常");
			map.put("error", e);
			return map;
		} finally {
			util.close(ts);
		}
	}
	
}
