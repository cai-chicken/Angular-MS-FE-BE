package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import service.studentsDao;
import db.myHibernateSessionFactory;
import entity.Students;

public class studentsDaoImpl implements studentsDao{
	
	private Session session = null;
	private Transaction ts = null;
	private List<Students> list = null;
	private String hql = "";
	private Students stu;
	private Query query;
	
	// 查询所有学生信息
	@SuppressWarnings("unchecked")
	@Override
	public List<Students> queryAllStudent() {
		try {
			session = myHibernateSessionFactory.getSessionFactory().getCurrentSession();
			ts = session.beginTransaction();
			hql = "from Students";//从持久化学生类中映射查询数据
			Query query = session.createQuery(hql);
			list = query.list();
			ts.commit();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.commit();
			return null;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

	// 查询单个学生信息根据id
	@Override
	public Students queryStudentById(String id) {
		try {
			session = myHibernateSessionFactory.getSessionFactory().getCurrentSession();
			ts = session.beginTransaction();
			stu = (Students)session.get(Students.class, id);
			ts.commit();
			return stu;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.commit();
			return null;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

	// 添加学生信息
	@Override
	public boolean addStudent(Students stu) {
		try {
			stu.setId(getNewId());
			session = myHibernateSessionFactory.getSessionFactory().getCurrentSession();
			ts = session.beginTransaction();
			System.out.println(stu.toString());
			session.save(stu);
			ts.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.rollback();
			return false;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

	// 删除学生信息根据id
	@Override
	public boolean deleteStudentById(String id) {
		try {
			session = myHibernateSessionFactory.getSessionFactory().getCurrentSession();
			ts = session.beginTransaction();
			stu = (Students)session.get(Students.class, id);
			session.delete(stu);
			ts.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.commit();
			return false;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

	// 更新学生信息根据id
	@Override
	public boolean updateStudent(Students stu) {
		try {
			session = myHibernateSessionFactory.getSessionFactory().getCurrentSession();
			ts = session.beginTransaction();
			hql = "select name from Students where id = ?";
			query = session.createQuery(hql);
			query.setParameter(0, stu.getId());
			String name = (String) query.uniqueResult();
			
			System.out.println("当前修改列的name: "+ name);
			if (name != "") {
				session.update(stu);
				ts.commit();
				return true;
			}
 			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.rollback();
			return false;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

	// 生成新的id
	@Override
	public String getNewId() {
		try {
			session = myHibernateSessionFactory.getSessionFactory().getCurrentSession();
			ts = session.beginTransaction();
			hql = "select max(id) from Students";
			Query query = session.createQuery(hql);
			String id = (String)query.uniqueResult();
			System.out.println("查询到的最大id为："+ id);
			if (id == null || "".equals(id)) {
				id = "2014000001";// 默认值
			} else {
				// 注意parseInt支持的最大正整数 => 2147483647，2^31-1
				Integer i = Integer.parseInt(id);//转化整型
				i++;
				id = String.valueOf(i);
			}
			ts.commit();
			return id;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.rollback();
			return null;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}
	
}
