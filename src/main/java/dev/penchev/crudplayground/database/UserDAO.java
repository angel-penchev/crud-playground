package dev.penchev.crudplayground.database;

import dev.penchev.crudplayground.models.UserModel;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.UUID;

@RegisterMapper(UserMapper.class)
public interface UserDAO {
    @SqlQuery("SELECT * FROM users WHERE id = :id")
    UserModel getById(@Bind("id") UUID id);

    @SqlQuery("SELECT * FROM users WHERE username = :username")
    UserModel getByUsername(@Bind("username") String username);

    @SqlUpdate("INSERT INTO users (id, username, password_hash, role) VALUES (:id, :username, :password, 'regular')")
    void insert(@BindBean UserModel user);

    @SqlUpdate("UPDATE users SET username = :username, password_hash = :password WHERE id = :userId")
    void updateWithoutRole(@Bind("userId") UUID id, @BindBean UserModel user);

    @SqlUpdate("UPDATE users SET username = :username, password_hash = :password, role = :role WHERE id = :userId")
    void updateWithRole(@Bind("userId") UUID id, @BindBean UserModel user);

    @SqlUpdate("DELETE FROM users WHERE id = :id")
    void delete(@Bind("id") UUID id);
}
