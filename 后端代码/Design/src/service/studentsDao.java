package service;

import java.util.List;

import entity.Students;

/**
 * 学生业务逻辑接口
 * @author Administrator
 *
 */
public interface studentsDao {

	//查询所有学生信息
	public List<Students> queryAllStudent();
	
	//根据学号查询学生信息
	public Students queryStudentById(String id);
	
	//增加学生信息
	public boolean addStudent(Students student);
	
	//根据学号删除学生信息
	public boolean deleteStudentById(String id);
	
	//修改学生信息
	public boolean updateStudent(Students student);
	
	//生成新的学生id
	public String getNewId();
}
