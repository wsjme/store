package dao;

import domain.Order;
import domain.Orderitem;
import domain.PageBean;
import domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/9.
 */
public interface OrderDao {
    void save(Connection con, Order order) throws SQLException;
    void saveItem (Connection con, Orderitem item) throws SQLException;
    List<Order> findOrder(User user, int pageNumber, int pageSize) throws Exception;
    int findCount(User user) throws SQLException;
    Order findByoid(String oid) throws Exception;
    void update(Order order) throws SQLException;
}
