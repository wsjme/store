package daoimpl;

import dao.OrderDao;
import domain.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Order> findOrder(User user, int pageNumber, int pageSize) throws Exception {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        int start = (pageNumber-1)*pageSize;
        String sql = "select * from orders where uid = ? limit ?,?";
        Object[] params={user.getUid(),start,pageSize};
        List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class), params);

        if(list!=null){
            for (Order order : list) {
                List<Orderitem> itemlist = order.getList();
                String sql2 = "select * from orderitem o,product p where oid=? and o.pid=p.pid";
                List<Map<String, Object>> maps = qr.query(sql2, new MapListHandler(), order.getOid());
                for (Map<String, Object> map : maps) {
                    Orderitem item = new Orderitem();
                    BeanUtils.populate(item,map);
                    Product product = new Product();
                    BeanUtils.populate(product,map);
                    item.setProduct(product);
                    itemlist.add(item);
                }
            }
        }

        return list;
    }

    public int findCount(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select count(*) from orders where uid = ? ";
        Long l= (Long)qr.query(sql,new ScalarHandler(),user.getUid());
        return l.intValue();
    }

    @Override
    public Order findByoid(String oid) throws Exception {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from orders where oid = ?";
        Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);

        if(order!=null){
            String sql2 = "select * from orderitem o,product p where oid = ? and o.pid = p.pid";
            List<Map<String, Object>> maps = qr.query(sql2, new MapListHandler(), oid);
            for (Map<String, Object> map : maps) {
                List<Orderitem> itemlist = order.getList();
                Orderitem item = new Orderitem();
                BeanUtils.populate(item,map);
                Product product = new Product();
                BeanUtils.populate(product,map);
                item.setProduct(product);
                itemlist.add(item);
            }

        }
        return order;
    }

    @Override
    public void update(Order order) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql="update orders set state=?,address=?,NAME=?,telephone=? where oid=?";
        qr.update(sql,order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
    }
}
