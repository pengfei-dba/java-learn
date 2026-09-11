package net.spfwork.forum.controller;

import net.spfwork.forum.domain.Reply;
import net.spfwork.forum.domain.Topic;
import net.spfwork.forum.dto.PageDTO;
import net.spfwork.forum.service.TopicService;
import net.spfwork.forum.service.impl.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TopicServlet类 - 处理与主题相关的HTTP请求
 * 继承自BaseServlet，提供主题列表显示和主题详情查看功能
 */
@WebServlet(name="TopicServlet",urlPatterns = {"/topic"})
public class TopicServlet extends BaseServlet{

    // 主题服务接口实现类，用于处理主题相关的业务逻辑
    TopicService topicService = new TopicServiceImpl();

    // 每页显示的主题/回复数量，设为2条
    private static final int pageSize=2;

    /**
     * list方法 - 获取指定分类的主题列表
     * @param request HttpServletRequest对象，包含客户端请求信息
     * @param httpServletResponse HttpServletResponse对象，用于向客户端发送响应
     */
    public void list(HttpServletRequest request,HttpServletResponse httpServletResponse){

        // 从请求参数中获取分类ID
        int cId=Integer.parseInt(request.getParameter("c_id"));
        // 初始化页码为1
        int pageNumber=1;
        // 获取当前页码参数
        String currentPage=request.getParameter("page");

        // 如果当前页码参数不为空，则转换为整数
        if (currentPage!=null && currentPage!=""){
            pageNumber=Integer.parseInt(currentPage);
        }

        // 调用服务层方法获取分页数据
        PageDTO pageDTO=topicService.findListBycId(cId,pageNumber,pageSize);
        // 将分页数据存入请求属性中，供JSP页面使用
        request.setAttribute("pageDao",pageDTO);
    }

    /**
     * findDetailById方法 - 根据主题ID获取主题详情及其回复列表
     * @param request HttpServletRequest对象，包含客户端请求信息
     * @param httpServletResponse HttpServletResponse对象，用于向客户端发送响应
     */
    public void findDetailById(HttpServletRequest request,HttpServletResponse httpServletResponse){

        // 从请求参数中获取主题ID
        int topicId = Integer.parseInt(request.getParameter("topic_id"));
        // 初始化页码为1
        int pageNumber=1;
        // 获取当前页码参数
        String currentPage=request.getParameter("page");

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
    }

}
