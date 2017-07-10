package web;

import domain.*;
import factory.FactoryDemo;
import service.OrderService;
import utils.BaseServlet;
import utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wsj on 2017/7/9.
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends BaseServlet {
    public String addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // 先获取购物车的数据用来封装订单和订单下的商品
            HttpSession session = request.getSession();
            Cart cart = (Cart)session.getAttribute("cart");
            // 封装订单
            Order order = new Order();
            // 封装订单编号
            order.setOid(UUIDUtils.getUUID());
            // 封装下单时间
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(new Date());
            order.setOrdertime(date);
            // 封装订单的总金额
            order.setTotal(cart.getTotal());
            // 封装订单状态 //未付款
            order.setState(0);
            // 订单属于哪个用户
            User user = (User)session.getAttribute("user");
            if(user==null){
                // 请登录再操作下单
                request.setAttribute("msg","请登录再操作下单");
                return "/jsp/info.jsp";
            }
            order.setUser(user);
            // 获取该订单的订单项集合
            List<Orderitem> orderList = order.getList();
            // 封装订单下的订单项商品
            Map<String, CartItem> map = cart.getMap();
            for (String key : map.keySet()) {
                // 有一个购物项==一个订单项
                Orderitem item = new Orderitem();
                item.setItemid(UUIDUtils.getUUID());
                item.setCount(map.get(key).getCount());
                item.setProduct(map.get(key).getProduct());
                item.setSubtotal(map.get(key).getSubtotal());
                // 当前订单项属于哪个订单
                item.setOrder(order);
                // 把每一个封装好的orderitem对象放在订单的订单项集合下
                orderList.add(item);
            }
            // 存订单--order
            OrderService osbean = (OrderService)FactoryDemo.getBean("OrderService");
            osbean.save(order);
            // 清空购物车
            cart.removeAll();

            // 带着order的所有数据到页面展示 order_info.jsp
            request.setAttribute("order", order);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/jsp/order_info.jsp";
    }
}
