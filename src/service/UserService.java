package service;

import domain.User;

import java.sql.SQLException;

/**
 * Created by wsj on 2017/7/6.
 */
public interface UserService {
    void regist(User user) throws Exception;
    User findByCode(String code) throws Exception;
    void update( User user) throws Exception;
    User findUser(String username,String password) throws Exception;
}
