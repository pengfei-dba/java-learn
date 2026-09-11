package net.spfwork.forum.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet("/baseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method=req.getParameter("method");
        if (method!=null){
            try{
                Method m =this.getClass().getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
                m.invoke(this,req,resp);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
