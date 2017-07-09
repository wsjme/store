package utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by wsj on 2017/7/6.
 */
@WebServlet(name = "BaseServlet", urlPatterns = "/base")
public class BaseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 为所有模块执行反射代码
        try {
            Class clazz = this.getClass();
            String me = request.getParameter("method");
            Method method = clazz.getMethod(me, HttpServletRequest.class,HttpServletResponse.class);
            //让方法自己执行
            //System.out.println("方法名称："+me);
            String value = (String) method.invoke(clazz.newInstance(), request, response);
            //System.out.println("跳转地址："+value);
            if (value!=null) {
                request.getRequestDispatcher(value).forward(request,response);
            }
            // 判断
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "反射发生了错误");
            request.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
