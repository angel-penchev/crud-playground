package dev.penchev.crudplayground.service;

import dev.penchev.crudplayground.database.UserDAO;
import dev.penchev.crudplayground.models.UserModel;

public class UserService {
    final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserModel getUserById(int id) {
        return userDAO.get(id);
    }

    public UserModel createUser(UserModel user) {
        userDAO.insert(user);
        return user;
    }

    public UserModel editUser(int id, UserModel user) {
        UserModel userModel = new UserModel(id, user.getName());
        userDAO.update(userModel);
        return userModel;
    }

    public void deleteUser(int id) {
        userDAO.delete(id);
    }
}
