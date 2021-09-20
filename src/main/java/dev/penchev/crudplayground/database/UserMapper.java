package dev.penchev.crudplayground.database;

import dev.penchev.crudplayground.models.UserModel;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<UserModel> {
    public UserModel map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new UserModel(resultSet.getInt("id"), resultSet.getString("name"));
    }
}