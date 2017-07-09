package web;

import domain.PageBean;
import domain.Product;
import factory.FactoryDemo;
import service.ProductService;
import serviceimpl.ProductServiceImpl;
import utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    // 分页商品的展示
    public String findPage(HttpServletRequest request, HttpServletResponse response) {

        try {
            String cid = request.getParameter("cid");
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = 12;
            ProductService psbean= (ProductService)FactoryDemo.getBean("ProductService");
            //ProductServiceImpl ps = new ProductServiceImpl();
            PageBean pb = psbean.findPage(cid, pageNumber, pageSize);
            request.setAttribute("pb",pb);
            request.setAttribute("cid",cid);



        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "商品查询失败,请等待稍后查询");
            return "/jsp/info.jsp";
        }
        return "/jsp/product_list.jsp";
    }


    // 单个商品的展示
    public String findBypid(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        try {

            // 1 获取点击商品的pid
            String pid = request.getParameter("pid");
            // 2 传递pid给service
            ProductService psbean= (ProductService)FactoryDemo.getBean("ProductService");
            //ProductService psbean=new ProductServiceImpl();
            Product p = psbean.findBypid(pid);
            request.setAttribute("p", p);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "商品查询失败,请等待稍后查询");
            return "/jsp/info.jsp";
        }


        return "/jsp/product_info.jsp";
    }

    // 查最新商品和热门商品
    public String findByNew(HttpServletRequest request, HttpServletResponse response) {
        try {

            ProductService psbean= (ProductService)FactoryDemo.getBean("ProductService");
            //ProductServiceImpl ps = new ProductServiceImpl();
            List<Product> list1 = psbean.findNew();
            request.setAttribute("p_new",list1);

            List<Product> list2 = psbean.findhot();
            request.setAttribute("p_hot",list2);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","查询最新商品出现错误");
            return "/jsp/info.jsp";
        }
        return "/jsp/index.jsp";
    }
}
