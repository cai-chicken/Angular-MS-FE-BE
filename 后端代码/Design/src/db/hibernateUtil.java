package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class hibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;
	
	
	static
	{
		//创建Configuration对象，读取hibernate.cfg.xml配置文件，完成初始化
		Configuration conf = new Configuration().configure();
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
		StandardServiceRegistry ssr = ssrb.build();
		sessionFactory = conf.buildSessionFactory(ssr);
	}
	
	//获取sessionFacotry
	public static SessionFactory getSessionFactory()
	{
		System.out.println(sessionFactory.hashCode());
		return sessionFactory;
	}
	
	//获取session
	public static Session getSession()
	{
		session = sessionFactory.openSession();
		return session;
	}
	
	public static void closeSession(Session session)
	{
		if(session!=null)
		{
			session.close();
		}
	}
}
