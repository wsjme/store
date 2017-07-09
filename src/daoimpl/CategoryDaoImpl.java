package daoimpl;

import dao.CategoryDao;
import domain.CateGory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/8.
 */
public class CategoryDaoImpl implements CategoryDao{
    @Override
    public List<CateGory> findCategory() throws SQLException {
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from category ";
        return qr.query(sql,new BeanListHandler<CateGory>(CateGory.class));
    }
}
