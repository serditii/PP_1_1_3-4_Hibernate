package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nick1", "lastName1", (byte) 13);
        userService.saveUser("Nick2", "lastName2", (byte) 14);
        userService.saveUser("Nick3", "lastName3", (byte) 15);
        userService.saveUser("Nick4", "lastName4", (byte) 16);
        List<User> userList = userService.getAllUsers();
        System.out.println(userList.toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
