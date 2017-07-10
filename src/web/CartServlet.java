package web;

import domain.Cart;
import domain.CartItem;
import domain.Product;
import factory.FactoryDemo;
import service.ProductService;
import utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by wsj on 2017/7/9.
 */
@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends BaseServlet {

    public Cart getCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        return cart;
    }


    public String addCart(HttpServletRequest request, HttpServletResponse response){
        try {
            String pid = request.getParameter("pid");
            int count = Integer.parseInt(request.getParameter("count"));
            ProductService ps = (ProductService)FactoryDemo.getBean("ProductService");
            Product product = ps.findBypid(pid);
            CartItem item = new CartItem();
            item.setProduct(product);
            item.setCount(count);
            Cart cart = getCart(request);

            cart.add(item);
            response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public String removeAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cart cart = getCart(request);
        cart.removeAll();
        // 重定向到cart.jsp
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        return null;
    }

    public String removeByid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String key = request.getParameter("id");
        Cart cart = getCart(request);
        cart.remove(key);
        // 重定向到cart.jsp
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        return null;
    }
}
