package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/mydb1";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private Connection connection;
    private static SessionFactory SessionFactory = null;

    public static Session getSession() throws HibernateException {
        return SessionFactory.openSession();
    }

    public Connection getConnection() {
        return connection;
    }

    public Util() {
        try {
            Configuration configuration = new Configuration();
            SessionFactory = configuration.addAnnotatedClass(User.class).buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
