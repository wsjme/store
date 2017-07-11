package service;

import domain.Order;
import domain.PageBean;
import domain.User;

/**
 * Created by wsj on 2017/7/9.
 */
public interface OrderService {
    void save(Order order);
    PageBean findOrder(User user, int pageNumber, int pageSize) throws Exception;
    Order findByoid(String oid) throws Exception;
    void update(Order order) throws Exception;
}
