package serviceimpl;

import dao.UserDao;
import domain.User;
import factory.FactoryDemo;

/**
 * Created by wsj on 2017/7/6.
 */
public class UserServiceImpl implements service.UserService {

    @Override
    public void regist(User user) throws Exception {
        UserDao udbean= (UserDao) FactoryDemo.getBean("UserDao");
        //UserDaoImpl ud = new UserDaoImpl();
        udbean.regist(user);
    }
    @Override
    public User findByCode(String code) throws Exception {
        UserDao udbean= (UserDao) FactoryDemo.getBean("UserDao");
        //UserDaoImpl ud = new UserDaoImpl();
        return udbean.findByCode(code);
    }

    @Override
    public void update(User user) throws Exception {
        UserDao udbean= (UserDao) FactoryDemo.getBean("UserDao");
        //UserDaoImpl ud = new UserDaoImpl();
        udbean.update(user);
    }

    @Override
    public User findUser(String username, String password) throws Exception {
        UserDao udbean= (UserDao) FactoryDemo.getBean("UserDao");
        //UserDaoImpl ud = new UserDaoImpl();
        return udbean.findUser(username,password);
    }
}
