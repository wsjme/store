package dao;

import domain.User;

import java.sql.SQLException;

/**
 * Created by wsj on 2017/7/6.
 */
public interface UserDao {
    void regist(User user) throws SQLException;

    User findByCode(String code) throws SQLException;

    void update(User user) throws SQLException;

    User findUser(String username, String password) throws SQLException;
}
