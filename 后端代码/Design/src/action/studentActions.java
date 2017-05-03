package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import entity.Students;
import service.studentsDao;
import service.impl.studentsDaoImpl;
import util.DateUtil;
import util.JSONUtil;
import util.Properties;
import util.ResponseUtil;

public class studentActions extends superAction implements ModelDriven<Students>{
	private static final long serialVersionUID = 3378379479390916603L;
	private Students student = new Students();
	
	// 处理对象，返回json字符串工具类
	private JSONUtil jsonUtil = new JSONUtil();
	private DateUtil dateUtil = new DateUtil();
	// 获取request、response工具类
	// private RequestUtil reqGet = new RequestUtil();
	private ResponseUtil resSet = new ResponseUtil();
	
	private HashMap<String, Object> map;
	private studentsDao sdao = new studentsDaoImpl();;
	private PrintWriter out;
	private String jsonStr;
	
	//查询所有学生操作
	public void queryAll() throws IOException {
		try {
			// 设置响应信息
			resSet.initResponseInfo(response);
			out = response.getWriter();
			// 实例化HashMap对象
			map = new HashMap<String, Object>();
			// 查询所有学生信息，返回List集合
			List<Students> list = sdao.queryAllStudent();
			// 判断是否有查询结果，并将查询结果响应给客户端
			if (list != null && list.size() > 0) {
				// 响应数据给客户端
				map.put("stuList", list);
				jsonStr = jsonUtil.JSONStringify(map);
				out.print(jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", e);
			jsonStr = jsonUtil.JSONStringify(map, false);
			out.print(jsonStr);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	//删除学生操作
	public void delete() throws IOException {
		try {
			// 设置响应信息
			resSet.initResponseInfo(response);
			out = response.getWriter();
			// 实例化HashMap对象
			map = new HashMap<String, Object>();
			// 获取学生id
			String id = student.getId();
			// 根据id删除对应学生信息
			Boolean delete_status = sdao.deleteStudentById(id);
			// 转化成适合响应的json字符串
			jsonStr = jsonUtil.JSONStringify(delete_status);
			// 响应删除结果
			out.print(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", e);
			jsonStr = jsonUtil.JSONStringify(map, false);
			out.print(jsonStr);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	//添加学生操作
	public void add() throws Exception {
		try {
			// 设置响应信息
			resSet.initResponseInfo(response);
			out = response.getWriter();
			// 实例化HashMap对象
			map = new HashMap<String, Object>();
			System.out.println(student.toString());
			// 获取日期时间戳
			String time = student.getBirthday();
			System.out.println("当前学生出生日期："+time);
			// 将时间戳格式化成日期字符串
			time = dateUtil.timeStampToDateStr(time, Properties.LONG_DATE);
			// 更新学生出生日期字段
			student.setBirthday(time);;
			System.out.println("formatted date: "+ time);
			// 执行添加学生操作，true表示添加成功，false表示失败
			Boolean add_status = sdao.addStudent(student);
			// 转化成适合响应的json字符串
			jsonStr = jsonUtil.JSONStringify(add_status);
			// 响应数据客户端
			out.print(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", e);
			jsonStr = jsonUtil.JSONStringify(map, false);
			out.print(jsonStr);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	//查询单个学生操作
	public void queryOne() {
		try {
			// 设置响应信息
			resSet.initResponseInfo(response);
			out = response.getWriter();
			// 实例化HashMap对象
			map = new HashMap<String, Object>();
			// 获取学生id
			String id = student.getId();
			// 根据id查询对应学生信息
			student = sdao.queryStudentById(id);
			// 判断是否查询到，将查询到的结果响应给客户端
			if (student != null) {
				map.put("stu", student);
				jsonStr = jsonUtil.JSONStringify(map);
				out.print(jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", e);
			jsonStr = jsonUtil.JSONStringify(map, false);
			out.print(jsonStr);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	//保存修改修改的学生操作
	public void update() throws Exception {
		try {
			// 设置响应信息
			resSet.initResponseInfo(response);
			out = response.getWriter();
			// 实例化HashMap对象
			map = new HashMap<String, Object>();
			
			// 获取id, name, profession
			String id = student.getId();
			String name = student.getName();
			String profession = student.getProfession();
			String birthday = student.getBirthday();
			birthday = dateUtil.timeStampToDateStr(birthday, Properties.LONG_DATE);
			student.setBirthday(birthday);
			System.out.println("formatted date："+ birthday);
			
			// id、name、profession都不为null才保存
			// 其中需要根据id，指定要修改的学生
			if (id != null && name != null && profession != null) {
				Boolean save_status = sdao.updateStudent(student);
				jsonStr = jsonUtil.JSONStringify(save_status);
				out.print(jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", e);
			jsonStr = jsonUtil.JSONStringify(map, false);
			out.print(jsonStr);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	// 模型驱动，自动请求参数
	@Override
	public Students getModel() {
		return this.student;
	}
}
