package net.spfwork.forum.service.impl;

import net.spfwork.forum.dao.ReplyDao;
import net.spfwork.forum.dao.TopicDao;
import net.spfwork.forum.domain.Reply;
import net.spfwork.forum.domain.Topic;
import net.spfwork.forum.domain.User;
import net.spfwork.forum.dto.PageDTO;
import net.spfwork.forum.service.TopicService;

import java.util.List;

/**
 * 主题服务实现类
 * 实现了TopicService接口，提供主题相关的业务逻辑处理
 */
public class TopicServiceImpl implements TopicService {

    // 主题数据访问对象
    TopicDao topicDao=new TopicDao();
    // 回复数据访问对象
    ReplyDao replyDao=new ReplyDao();
    /**
     * 根据主题ID查找主题
     * @param topicId 主题ID
     * @return 返回找到的主题对象
     */
    @Override
    public Topic findTopicById(int topicId) {
        return topicDao.findTopicById(topicId);
    }

    /**
     * 根据分类ID查找主题列表并分页
     * @param cId 分类ID
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return 返回分页数据对象，包含主题列表和分页信息
     */
    @Override
    public PageDTO<Topic> findListBycId(int cId, int page, int pageSize) {
        // 计算总记录数
        int totalRecords=topicDao.countTotalTopicBycId(cId);
        // 计算起始记录位置
        int from =(page-1)*pageSize;
        // 获取当前页的主题列表
        List<Topic> list=topicDao.findListBycId(cId,from,pageSize);
        // 创建分页对象
        PageDTO<Topic> pageDTO=new PageDTO<>(page,pageSize,totalRecords);

        // 设置主题列表到分页对象
        pageDTO.setList(list);
        return pageDTO;
    }

    /**
     * 根据主题ID查找回复列表并分页
     * @param topicId 主题ID
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return 返回分页数据对象，包含回复列表和分页信息
     */
    @Override
    public PageDTO<Reply> findReplyPageByTopicId(int topicId, int page, int pageSize) {
        // 计算总记录数
        int totalRecords=replyDao.countTotalReplyByTopicId(topicId);
        // 计算起始记录位置
        int from =(page-1)*pageSize;
        // 获取当前页的回复列表
        List<Reply> list =replyDao.findListByTopicId(topicId,from,pageSize);
        // 创建分页对象
        PageDTO<Reply> pageDTO= new PageDTO<>(page,pageSize,totalRecords);
        // 设置回复列表到分页对象
        pageDTO.setList(list);
        return pageDTO;
    }

    @Override
    public int addTopic(User loginUser, String title, String content, int cId) {
        topicDao.addTopic(loginUser, title, content, cId);
    }
}
