package hotelmanagementsystem.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {


    private static final SessionFactory sessionFactory;

    static
    {
        try {

            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable e){

            System.out.println("Check HibernateUtils static method");
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutDown (){

        getSessionFactory().close();

    }

    public static void closeSession (Session session){

        if (session != null && session.isOpen()){

            session.close();

        }

    }



}
