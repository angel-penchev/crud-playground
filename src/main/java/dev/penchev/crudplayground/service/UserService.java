package dev.penchev.crudplayground.service;

import dev.penchev.crudplayground.database.UserDAO;
import dev.penchev.crudplayground.models.UserModel;

import java.util.UUID;

public class UserService {
    final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserModel getUserById(UUID id) {
        return userDAO.getById(id);
    }

    public UserModel editUser(UUID id, UserModel user, boolean allowRoleChange) {
        if (allowRoleChange) {
            userDAO.updateWithoutRole(id, user);
        } else {
            userDAO.updateWithRole(id, user);
        }
        return userDAO.getById(id);
    }

    public void deleteUser(UUID id) {
        userDAO.delete(id);
    }
}
