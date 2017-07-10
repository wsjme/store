package web;

import domain.User;
import factory.FactoryDemo;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import serviceimpl.UserServiceImpl;
import utils.BaseServlet;
import utils.MailUtils;
import utils.UUIDUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by wsj on 2017/7/6.
 */
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/jsp/login.jsp";
    }

    public String registUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/jsp/register.jsp";
    }

    public String regist(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user, map);
            user.setUid(UUIDUtils.getUUID());
            user.setState(0);
            user.setCode(UUIDUtils.getUUID() + UUIDUtils.getUUID());
            UserService us = new UserServiceImpl();
            us.regist(user);
            //String emailMsg ="这是一封用户"+user.getName()+"用户的激活邮件，请<a href=http://localhost:8080/store/user?method=active&code="+user.getCode()+">点击激活</a>";
            String eamilMsg = "注册成功，请<a href=http://localhost:8080/user?method=active&code=" + user.getCode() + ">点击激活</a>";
            MailUtils.sendMail(user.getEmail(), eamilMsg);
            request.setAttribute("msg", "恭喜你,注册成功,请尽快登录邮箱进行激活...");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "注册失败");
            return "/jsp/info.jsp";
        }
        return "/jsp/info.jsp";
    }

    public String active(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 接收激活码
            String code = request.getParameter("code");
            // 根据激活码查询数据库还有没有该用户


            UserService usbean= (UserService) FactoryDemo.getBean("UserService");
            //UserServiceImpl us = new UserServiceImpl();
            User user = usbean.findByCode(code);
            // 判断user是否为null
            if(user==null)
            {
                // 没有--告诉用户重新去注册
                request.setAttribute("msg", "亲,激活失效请重新注册");
                return "/jsp/info.jsp";
            }
            // 有--修改激活状态为1
            user.setState(1);
            usbean.update(user);
            // 告诉用户激活成功,去登录
            request.setAttribute("msg", "亲,激活成功,请去登录吧");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "激活失败");
            return "/jsp/info.jsp";
        }

        return "/jsp/info.jsp";
    }


    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        try {

            // 获取用户名和密码
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            // 1 获取页面验证码数据
            String ymyzm = request.getParameter("ymyzm");  //ymyzm=nqq2
            // 2 获取codeservlet的数据
            HttpSession session = request.getSession();
            String sessionyzm =(String)session.getAttribute("sessionyzm"); //sessionyzm=nqq2
            // 3 判断
            if(ymyzm==null || sessionyzm==null || !ymyzm.equalsIgnoreCase(sessionyzm))
            {
                session.setAttribute("yzmts","验证码错误！");
                response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
                /*response.getWriter().println("no");
                System.out.println("NO");*/
            }else
            {
                // 获取用户名和密码去数据库匹配
                session.removeAttribute("sessionyzm");
                response.getWriter().println("ok");
                // 根据用户名和密码去数据库匹配
                //UserService us= new UserServiceImpl();
                UserService usbean= (UserService) FactoryDemo.getBean("UserService");
                User user = usbean.findUser(username,password);
                if(user==null)
                {
                    request.setAttribute("msg", "用户名或者密码错误..");
                    return "/jsp/login.jsp";
                }
                // 不为null 判断激活状态是否为1
                if(user.getState()!=1)
                {
                    request.setAttribute("msg", "亲,你还没有激活,请先去邮箱激活");
                    return "/jsp/info.jsp";
                }

                // 登录成功 到首页展示
                session.setAttribute("user", user);

                // 重定向到首页
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "登录失败");
            return "/jsp/info.jsp";
        }
        return null;
    }

    public String quit(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // 获取session
        HttpSession session = request.getSession();
        // 销毁session
        session.invalidate();
        // 到首页
        response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");

        return null;
    }
}


