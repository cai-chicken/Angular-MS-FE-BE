package db;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class myHibernateSessionFactory {
	private static SessionFactory sessionFactory;
	
	//私有化构造方法，保证单例模式
	private myHibernateSessionFactory()
	{
		
	}
	
	//公有静态方法，获得会话工厂对象
	public static SessionFactory getSessionFactory()
	{
		if(sessionFactory==null)
		{
			Configuration conf = new Configuration().configure();
			ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			sessionFactory = conf.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		}
		else
		{
			return sessionFactory;
		}
	}
}
