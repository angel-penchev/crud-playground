package dev.penchev.crudplayground.database;

import dev.penchev.crudplayground.models.UserModel;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements ResultSetMapper<UserModel> {
    public UserModel map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new UserModel(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("username"),
                resultSet.getString("password_hash")
        );
    }
}