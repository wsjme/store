package serviceimpl;

import dao.CategoryDao;
import daoimpl.ProductDaoImpl;
import domain.PageBean;
import domain.Product;
import factory.FactoryDemo;
import service.ProductService;
import dao.ProductDao ;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wsj on 2017/7/8.
 */
public class ProductServiceImpl implements ProductService {

    @Override
    public PageBean findPage(String cid, int pageNumber, int pageSize) throws Exception {
        ProductDao pdbean= (ProductDao) FactoryDemo.getBean("ProductDao");
        //ProductDaoImpl pd = new ProductDaoImpl();
        List<Product> list = pdbean.findPage(cid, pageNumber, pageSize);
        int totalCount = pdbean.count(cid);
        int totalPage = 0;
        if (totalCount/pageSize==0) {
            totalPage=totalCount/pageSize;
        } else {
            totalPage=totalCount/pageSize+1;
        }
        PageBean pb = new PageBean();
        pb.setList(list);
        pb.setPageSize(pageSize);
        pb.setPageNumber(pageNumber);
        pb.setTotalPage(totalPage);
        pb.setTotalCount(totalCount);
        return pb;
    }

    @Override
    public Product findBypid(String pid) throws Exception {
        //ProductDaoImpl pd = new ProductDaoImpl();
        ProductDao pdbean= (ProductDao) FactoryDemo.getBean("ProductDao");
        return pdbean.findBypid(pid);
    }

    @Override
    public List<Product> findNew() throws Exception {
        //ProductDaoImpl pd = new ProductDaoImpl();
        ProductDao pdbean= (ProductDao) FactoryDemo.getBean("ProductDao");
        return pdbean.findNew();
    }

    @Override
    public List<Product> findhot() throws Exception {
        //ProductDaoImpl pd = new ProductDaoImpl();
        ProductDao pdbean= (ProductDao) FactoryDemo.getBean("ProductDao");
        return pdbean.findhot();
    }
}
