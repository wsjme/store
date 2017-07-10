package daoimpl;

import dao.OrderDao;
import domain.Order;
import domain.Orderitem;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by wsj on 2017/7/9.
 */
public class OrderDaoImpl implements OrderDao{
    @Override
    public void save(Connection con, Order order) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        Object[] params={order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()};
        qr.update(con,sql,params);
    }

    @Override
    public void saveItem(Connection con, Orderitem item) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = "insert into orderitem values(?,?,?,?,?)";
        Object[] params={item.getItemid(),item.getCount(),item.getSubtotal(),item.getProduct().getPid(),item.getOrder().getOid()};
        qr.update(con,sql,params);
    }
}
