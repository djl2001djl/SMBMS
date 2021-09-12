package com.DJL.servlet.user;

import com.DJL.pojo.User;
import com.DJL.service.user.UserService;
import com.DJL.service.user.UserServiceImpl;
import com.DJL.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DJL
 * @create 2021-08-31 22:46
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login ============ " );
        //获取用户名和密码
        String userCode = request.getParameter("userCode");
        String userPassword = request.getParameter("userPassword");
        //调用service方法，进行用户匹配
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode,userPassword);
        if(null != user){//登录成功
            //放入session
            request.getSession().setAttribute(Constants.USER_SESSION, user);
            //页面跳转（frame.jsp）
            response.sendRedirect("/smbms/jsp/frame.jsp");//前面必须加上 "/smbms"这个路径
            //request.getRequestDispatcher("/jsp/frame.jsp").forward(request,response);
        }else{
            //页面跳转（login.jsp）带出提示信息--转发
            request.setAttribute("error", "用户名或密码不正确");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
