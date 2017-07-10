package dao;

import domain.Order;
import domain.Orderitem;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by wsj on 2017/7/9.
 */
public interface OrderDao {
    void save(Connection con, Order order) throws SQLException;
    void saveItem (Connection con, Orderitem item) throws SQLException;
}
