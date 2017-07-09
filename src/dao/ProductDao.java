package dao;

import domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/8.
 */
public interface ProductDao {
    List<Product> findPage(String cid, int pageNumber, int pageSize) throws SQLException;
    int count(String cid) throws SQLException;
    Product findBypid(String cid) throws  SQLException;
    List<Product> findNew() throws SQLException;
    List<Product> findhot() throws SQLException;
}
