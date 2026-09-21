package net.spfwork.forum.controller;

import net.spfwork.forum.domain.Reply;
import net.spfwork.forum.domain.Topic;
import net.spfwork.forum.domain.User;
import net.spfwork.forum.dto.PageDTO;
import net.spfwork.forum.service.CategoryService;
import net.spfwork.forum.service.TopicService;
import net.spfwork.forum.service.impl.CategoryServiceImpl;
import net.spfwork.forum.service.impl.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * TopicServlet类 - 处理与主题相关的HTTP请求
 * 继承自BaseServlet，提供主题列表显示和主题详情查看功能
 */
@WebServlet(name="TopicServlet",urlPatterns = {"/topic"})
public class TopicServlet extends BaseServlet{

    // 主题服务接口实现类，用于处理主题相关的业务逻辑
    TopicService topicService = new TopicServiceImpl();

    // 分类服务，用于页面导航栏渲染
    CategoryService categoryService = new CategoryServiceImpl();
    // 每页显示的主题/回复数量，设为2条
    private static final int pageSize=2;

    /**
     * list方法 - 获取指定分类的主题列表
     * @param request HttpServletRequest对象，包含客户端请求信息
     * @param httpServletResponse HttpServletResponse对象，用于向客户端发送响应
     */
    public void list(HttpServletRequest request,HttpServletResponse httpServletResponse) throws ServletException, IOException {

        // 从请求参数中获取分类ID
        int cId=Integer.parseInt(request.getParameter("c_id"));
        // 初始化页码为1
        int pageNumber=1;
        // 获取当前页码参数
        String currentPage=request.getParameter("page");

        // 如果当前页码参数不为空，则转换为整数
        if (currentPage!=null && !currentPage.isEmpty()){
            pageNumber=Integer.parseInt(currentPage);
        }

        // 调用服务层方法获取分页数据
        PageDTO pageDTO=topicService.findListBycId(cId,pageNumber,pageSize);
        // 将分页数据存入请求属性中，供JSP页面使用（index.jsp 读取 topicPage）
        request.setAttribute("topicPage",pageDTO);
        // 导航栏所需的分类列表和登录用户
        request.setAttribute("categoryList",categoryService.list());
        request.setAttribute("loginUser",request.getSession().getAttribute("User"));
        // 转发到首页渲染
        request.getRequestDispatcher("/index.jsp").forward(request,httpServletResponse);
    }


    /**
     * findDetailById方法 - 根据主题ID获取主题详情及其回复列表
     * @param request HttpServletRequest对象，包含客户端请求信息
     * @param httpServletResponse HttpServletResponse对象，用于向客户端发送响应
     */
    public void findDetailById(HttpServletRequest request,HttpServletResponse httpServletResponse) throws ServletException, IOException {

        // 从请求参数中获取主题ID
        int topicId = Integer.parseInt(request.getParameter("topic_id"));
        // 初始化页码为1
        int pageNumber=1;
        // 获取当前页码参数
        String currentPage=request.getParameter("page");
        //处理浏览量，如果同个session内只算一次
        String sessionReadKey = "is_read_"+topicId;

        Boolean isRead = (Boolean) request.getSession().getAttribute(sessionReadKey);

        if(isRead == null){
            request.getSession().setAttribute(sessionReadKey,true);
            //新增一个pv
            topicService.addOnePV(topicId);
        }




        // 如果当前页码参数不为空，则转换为整数
        if (currentPage!=null && currentPage!=""){
            pageNumber=Integer.parseInt(currentPage);
        }
        // 调用服务层方法获取主题详情
        Topic topic=topicService.findTopicById(topicId);

        // 调用服务层方法获取主题的分页回复列表
        PageDTO<Reply> pageDTO=topicService.findReplyPageByTopicId(topicId,pageNumber,pageSize);
        // 将主题详情存入请求属性中
        request.setAttribute("topic" ,topic);
        // 将回复分页数据存入请求属性中，供JSP页面使用
        request.setAttribute("ReplyPage",pageDTO);
        // 导航栏所需的分类列表和登录用户
        request.setAttribute("categoryList",categoryService.list());
        request.setAttribute("loginUser",request.getSession().getAttribute("User"));
        // 转发到详情页渲染
        request.getRequestDispatcher("/topic.jsp").forward(request,httpServletResponse);
    }
    public void addTopic(HttpServletRequest request, HttpServletResponse httpServletResponse){
        User loginUser=(User)request.getSession().getAttribute("User");
        if (loginUser!=null){
            String title=request.getParameter("title");
            String content=request.getParameter("content");
            int cId =Integer.parseInt(request.getParameter("c_id"));
            int rows=topicService.addTopic(loginUser,title,content,cId);
            if (rows>0){
                request.setAttribute("mes","发布成功");
            }else {
                request.setAttribute("mes","发布失败");
            }
        }else {
            request.setAttribute("mes","请登录");
        }


    }

    public void ReplyByTopicId(HttpServletRequest request, HttpServletResponse httpServletResponse) throws SQLException {
        User loginUser=(User)request.getSession().getAttribute("User");
        if (loginUser!=null){
            int topicId=Integer.parseInt(request.getParameter("topic_id"));
            String content = request.getParameter("content");
            int rows=topicService.ReplyByTopicId(loginUser,topicId,content);
            if (rows>0){
                request.setAttribute("mes","回复成功");
            }else {
                request.setAttribute("mes","回复失败");
            }
        }
    }

}
