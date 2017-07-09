package service;

import domain.PageBean;
import domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/8.
 */
public interface ProductService {
    PageBean findPage(String cid, int pageNumber, int pageSize) throws Exception ;
    Product findBypid(String pid) throws  Exception;
    List<Product> findNew() throws  Exception;
    List<Product> findhot() throws  Exception;
}
