package com.duolingo.app.util;

import com.duolingo.app.model.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
	
    private static SessionFactory sessionFactory;
       
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/duolingodb?serverTimezone=UTC");
                settings.put(Environment.USER, "admin");
                settings.put(Environment.PASS, "Xavi1234");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "none");

                configuration.setProperties(settings);
                
                configuration.addAnnotatedClass(Category.class);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Exercice.class);
                configuration.addAnnotatedClass(Item.class);
                configuration.addAnnotatedClass(Language.class);
                configuration.addAnnotatedClass(Level.class);
                configuration.addAnnotatedClass(Rank.class);
                configuration.addAnnotatedClass(TypeExercice.class);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}