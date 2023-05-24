package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSession;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users (" +
                "Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45)," +
                " lastName VARCHAR(45), age INT)";
        Util util = new Util();

        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS users;";
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        Session session = getSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            System.out.println("User с именем Ц " + name + " добавлен в базу данных");
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Util util = new Util();
        Session session = getSession();
        try {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Util util = new Util();
        List<User> list;
        Session session = getSession();
        try {
            session.beginTransaction();
            list = session.createQuery("from User ").getResultList();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Util util = new Util();
        Session session = getSession();
        try {
            session.beginTransaction();
            session.createQuery("delete User ").executeUpdate();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
}
