package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

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

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS users;";
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sqlCommand = "INSERT users(name, lastName, age) " +
                "VALUES ('" + name + "', '" + lastName + "', " + age + ");";
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommand);
            System.out.println("User с именем Ц " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        int idInt = (int) id;
        String sqlCommand = "DELETE FROM users WHERE Id = " + idInt;
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        Util util = new Util();
        String query = "select * from users";
        List<User> list = null;
        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            list = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String sqlCommand = "TRUNCATE TABLE users;";
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
