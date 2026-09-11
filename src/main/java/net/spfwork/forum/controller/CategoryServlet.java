package net.spfwork.forum.controller;

import net.spfwork.forum.domain.Category;
import net.spfwork.forum.service.CategoryService;
import net.spfwork.forum.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * categoryServlet - 处理分类相关的HTTP请求
 * 该类继承自BaseServlet，用于处理与分类相关的业务逻辑
 */
@WebServlet(name = "categoryServlet",urlPatterns = {"/category"})
public class CategoryServlet extends BaseServlet {

    // 声明并初始化分类服务对象，使用categoryServiceImpl实现类
    private CategoryService categoryService=new CategoryServiceImpl();

    /**
     * list方法 - 获取所有分类信息并存储到请求中
     * @param req HttpServletRequest对象，包含客户端请求信息
     * @param resp HttpServletResponse对象，用于向客户端发送响应
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用服务层方法获取所有分类列表
        List<Category> list=categoryService.list();

        System.out.println(list.toString());
        // 将分类列表存入请求作用域，供前端页面使用
        req.setAttribute("list",list);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
