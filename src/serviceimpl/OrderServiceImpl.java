package serviceimpl;

import dao.OrderDao;
import domain.Order;
import domain.Orderitem;
import factory.FactoryDemo;
import org.apache.commons.dbutils.DbUtils;
import service.OrderService;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/9.
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public void save(Order order) {
        Connection con = null;
        try {
            OrderDao odbean = (OrderDao)FactoryDemo.getBean("OrderDao");
            // 开启事务
            con = JDBCUtils.getConnection();
            con.setAutoCommit(false);
            // 保存订单
            odbean.save(con,order);
            // 保存订单下的订单项
            List<Orderitem> itemlist = order.getList();

            if (itemlist.size()>0) {
                for (Orderitem item : itemlist) {
                    odbean.saveItem(con,item);
                }
            }
            // 提交事务
            DbUtils.commitAndClose(con);

        } catch (Exception e) {
            try {
                DbUtils.rollbackAndClose(con);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }
}
