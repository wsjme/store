package daoimpl;

import dao.ProductDao;
import domain.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/8.
 */
public class ProductDaoImpl implements ProductDao{


    public List<Product> findPage(String cid, int pageNumber, int pageSize) throws SQLException {
        int start = (pageNumber-1)*pageSize;
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where cid = ? limit ?,?";
        return qr.query(sql,new BeanListHandler<Product>(Product.class),cid,start,pageSize);
    }

    public int count(String cid) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select count(*) from product where cid = ?";
        Long l = (Long)qr.query(sql, new ScalarHandler(),cid);
        return l.intValue();
    }

    @Override
    public Product findBypid(String pid) throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where pid = ?";
        return qr.query(sql,new BeanHandler<Product>(Product.class),pid);
    }

    @Override
    public List<Product> findNew() throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where pflag = 0 ORDER BY pdate DESC LIMIT 0,9";
        return qr.query(sql,new BeanListHandler<Product>(Product.class));
    }

    @Override
    public List<Product> findhot() throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where is_hot = 0 ORDER BY pdate DESC LIMIT 0,9";
        return qr.query(sql,new BeanListHandler<Product>(Product.class));
    }
}
