package dev.penchev.crudplayground.database;

import dev.penchev.crudplayground.user.UserMapper;
import dev.penchev.crudplayground.user.UserModel;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(UserMapper.class)
public interface UserDAO {
    @SqlQuery("SELECT * FROM users")
    List<UserModel> getAll();

//    @SqlQuery("")
}
