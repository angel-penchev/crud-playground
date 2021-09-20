package dev.penchev.crudplayground.database;

import dev.penchev.crudplayground.models.UserModel;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserMapper.class)
public interface UserDAO {
    @SqlQuery("SELECT * FROM users WHERE id = :id")
    UserModel get(@Bind("id") int id);

    @SqlUpdate("INSERT INTO users (id, name) VALUES (:id, :name)")
    int insert(@BindBean UserModel user);

    @SqlUpdate("UPDATE users SET name = :name WHERE id =:id")
    int update(@BindBean UserModel user);

    @SqlUpdate("DELETE FROM users WHERE id = :id")
    int delete(@Bind("id") int id);
}
