package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;
import service.usersDao;
import service.impl.usersDaoImpl;
import util.JSONUtil;
import util.RequestUtil;
import util.ResponseUtil;

public class userActions extends superAction implements ModelDriven<Users> {
	private static final long serialVersionUID = -2711234680984558835L;
	
	// 处理对象，返回json字符串工具类
	private JSONUtil jsonUtil = new JSONUtil();
	// 获取request、response工具类
	private RequestUtil reqGet = new RequestUtil();
	private ResponseUtil resSet = new ResponseUtil();
	
	private Users user = new Users();
	private usersDao udao = new usersDaoImpl();
	private PrintWriter out;
	private String jsonStr, username, password, cfmpassword;
	private HashMap<String, Object> map;;
	
	//用户登录动作
	public void signIn() throws IOException {
		// 设置响应信息
		resSet.initResponseInfo(response);
		// 实例化HashMap对象
		map = new HashMap<String, Object>();
		try {
			// 获取响应对象的输出流对象out，用于向客户端返回数据
			out = response.getWriter();
			// 获取登录反馈对象
			map = udao.userSignin(user);
			
			// 判断登录返回的状态结果，是否有"status"
			if(map.containsKey("status")) {
				// 获取模型驱动匹配到的，请求数据中的，用户对象的用户名
				/*username = user.getUsername();
				password = user.getPassword();*/
				username = reqGet.parameter(request, "username");
				password = reqGet.parameter(request, "password");
				System.out.println("---"+username+"/"+password+"--登录成功--sessionid--"+ session.getId());
				
				// 存储在session中，保持登录状态
				session.setAttribute("username", username);
				// 转换成json字符串
				jsonStr = jsonUtil.JSONStringify(map);
				// 响应json数据
				out.print(jsonStr);
				return;
			} else {
				System.out.println("---"+username+"/"+password+"--登录失败--sessionid--"+ session.getId());
				
				// 登录失败，第二个参数指定false => 表示登录异常，更改errno = 1;
				jsonStr = jsonUtil.JSONStringify(map, false);
				out.print(jsonStr);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr = jsonUtil.JSONStringify(e, false);
			out.print(jsonStr);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	//用户注册操作
	@SkipValidation
	public void signUp() throws IOException {
		// 设置响应信息
		resSet.initResponseInfo(response);
		// 实例化HashMap对象
		map = new HashMap<String, Object>();
		
		try {
			out = response.getWriter();
			// 获取用户注册，请求参数
			username = reqGet.parameter(request, "username") ;
			password = reqGet.parameter(request, "password") ;
			cfmpassword = reqGet.parameter(request, "cfmpassword") ;
			
			if (password.equals(cfmpassword)) {
				// 传递用户实例化对象，执行用户注册方法，返回注册结果HashMap对象
				map = udao.userSignup(user);
				// 判断返回结果中是否存在username的Key，如果存在表示注册成功，否则注册失败
				if (map.containsKey("username")) {
					System.out.println("---"+username+"/"+password+"--注册成功--sessionid--"+ session.getId());
					
					// 注册成功后将用户名保存在session会话中
					session.setAttribute("username", username);
					// 处理注册结果为json字符串
					jsonStr = jsonUtil.JSONStringify(map);
					// 响应注册结果
					out.print(jsonStr);
				} else {
					System.out.println("---"+username+"/"+password+"--注册失败--sessionid--"+ session.getId());
					
					// 处理注册结果为json字符串
					jsonStr = jsonUtil.JSONStringify(map, false);
					// 响应注册结果
					out.print(jsonStr);
				}
			} else {
				System.out.println("---"+username+"/"+password+"/"+cfmpassword+"--注册失败--sessionid--"+ session.getId());
				
				// 手动添加注册失败提示信息
				map.put("status", "确认密码有误！");
				// 处理提示信息对象为json字符串
				jsonStr = jsonUtil.JSONStringify(map, false);
				// 响应注册结果
				out.print(jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr = jsonUtil.JSONStringify(e, false);
			out.print(jsonStr);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	//用户注销动作
	@SkipValidation
	public void logOff() throws IOException {
		// 设置响应信息
		resSet.initResponseInfo(response);
		try {
			out = response.getWriter();
			username = (String) session.getAttribute("username");
			if (session.getAttribute("username") != null) {
				System.out.println("---"+username+"--注销成功--sessionid--"+ session.getId());
				session.removeAttribute("username");
			} else {
				System.out.println("---"+username+"--注销失败，已被注销--sessionid--"+ session.getId());
			}
			jsonStr = jsonUtil.JSONStringify(true);
			out.print(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();;
			jsonStr = jsonUtil.JSONStringify(e, false);
			out.print(jsonStr);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	// 用户是否登录
	public void whetherLogged() {
		// 实例化HashMap对象
		map = new HashMap<String, Object>();
		// 设置响应信息
		resSet.initResponseInfo(response);
		try {
			// 获取响应对象的输出流对象out，用于向客户端返回数据
			out = response.getWriter();
			username = (String) session.getAttribute("username");
			if(username != null) {
				System.out.println("---"+username+"--用户已登录--sessionid--"+ session.getId());
				map.put("username", username);
				jsonStr = jsonUtil.JSONStringify(map);
				out.print(jsonStr);
			} else {
				System.out.println("---"+username+"--用户未登录--sessionid--"+ session.getId());
				jsonStr = jsonUtil.JSONStringify(false, false);
				out.print(jsonStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("---"+username+"--whetherLogged()时发生异常--sessionid--"+ session.getId());
			jsonStr = jsonUtil.JSONStringify(e, false);
			out.print(jsonStr);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	// 用户名是否已存在
	public void usernameIsValid() {
		// 设置响应信息
		resSet.initResponseInfo(response);
		// 实例化HashMap对象
		map = new HashMap<String, Object>();
		username = reqGet.parameter(request, "username");
		try {
			System.out.println("--usernameIsValid--检测用户名是否被注册--");
			out = response.getWriter();
			map = udao.userSignup(user, true);
			if (map.containsKey("valid")) {
				jsonStr = jsonUtil.JSONStringify(map, true);
				out.print(jsonStr);
			} else {
				jsonStr = jsonUtil.JSONStringify(map, false);
				out.print(jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonStr = jsonUtil.JSONStringify(e, false);
			out.print(jsonStr);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	//模型驱动接受参数
	@Override
	public Users getModel() {
		return this.user;
	}
	
}
