package web;

import factory.FactoryDemo;
import service.CategoryService;
import utils.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wsj on 2017/7/8.
 */
@WebServlet(name = "CategoryServlet", urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
    public String findCategory(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/html;charset=utf-8");
            /*CategoryService cs = new CategoryServiceImpl();*/
            CategoryService csbean= (CategoryService) FactoryDemo.getBean("CategoryService");
            String json = csbean.findCategory();
            response.getWriter().println(json);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","导航条加载失败！");
            return "/jsp/info/jsp";
        }
        return null;
    }
}
