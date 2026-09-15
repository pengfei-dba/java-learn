package net.spfwork.forum.controller;

import net.spfwork.forum.domain.User;
import net.spfwork.forum.service.UserService;
import net.spfwork.forum.service.impl.UserServletImpl;
import net.spfwork.forum.util.Md5sumUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends BaseServlet {

    private UserService userService=new UserServletImpl();
/**
 * 用户注册方法
 * @param request HttpServletRequest对象，用于接收客户端请求
 * @param response HttpServletResponse对象，用于向客户端响应结果
 */

    /**
     * 处理用户注册请求的方法
     * @param request HttpServletRequest对象，包含客户端请求信息
     * @param response HttpServletResponse对象，用于向客户端返回响应
     */
    public void register(HttpServletRequest request, HttpServletResponse response) {
        // 创建一个User对象，用于存储用户信息
        User user=new User();

        // 获取请求参数，并将其存储在一个Map中，键为参数名，值为参数值数组
        Map<String,String []> map=request.getParameterMap();
        try {
            // 使用BeanUtils工具类将请求参数填充到User对象中
            // 这样可以将前端表单数据直接映射到Java对象属性
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            // 处理IllegalAccessException异常，打印堆栈跟踪信息
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // 处理InvocationTargetException异常，打印堆栈跟踪信息
            e.printStackTrace();
        }
        // 调用userService的register方法处理用户注册逻辑，并获取结果
        int i = userService.register(user);

        if(i>0){
            //注册成功，跳转到登录界面 TODO
        }else {
            //注册失败，跳转到注册页面 TODO
        }
    }

//    public void login(HttpServletRequest request, HttpServletResponse response) {
//        String phone=request.getParameter("phone");
//        String pwd=request.getParameter("pwd");
//        User user=userService.login(phone,Md5sumUtils.msgToMD5(pwd));//双层加密永远匹配不上
//        if (user != null){
//            request.getSession().setAttribute("User",user);//没有响应体
//        }else{
//            request.setAttribute("msg","用户名或密码错误");
//        }
//
//    }
    /**
     * 处理用户登录请求的方法
     * @param request HttpServletRequest对象，用于获取请求参数
     * @param response HttpServletResponse对象，用于设置响应内容和重定向
     * @throws IOException 可能抛出的IO异常
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 从请求中获取手机号参数
        String phone=request.getParameter("phone");
        // 从请求中获取密码参数
        String pwd=request.getParameter("pwd");
        // 调用userService的login方法进行用户登录验证
        User user=userService.login(phone,pwd);
        // 设置响应内容类型为HTML，字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        // 如果用户不为空，表示登录成功
        if (user != null){
            // 将用户信息存入session中
            request.getSession().setAttribute("User",user);
            // 重定向到首页
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            // 设置请求属性，用于在前端显示错误信息
            request.setAttribute("msg","用户名或密码错误");
            // 向响应中写入错误信息
            response.getWriter().write("用户名或密码错误");
        }
    }
    public void  logout(HttpServletRequest request,HttpServletResponse response){

        request.getSession().invalidate();

        //页面跳转 TODO
    }
}
