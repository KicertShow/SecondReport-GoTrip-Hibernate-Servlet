package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;

@WebListener
public class HibernateInitialization implements ServletContextListener {

	// 讓程式一打開就建立sessionFactory
	// 一定要加WebListener
	SessionFactory factory;
	
	public HibernateInitialization() {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Starting the program...");
		factory = HibernateUtils.getSessionFactory();
		System.out.println("The program is started...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		factory.close();
		System.out.println("Closing the program...");
	}

}
