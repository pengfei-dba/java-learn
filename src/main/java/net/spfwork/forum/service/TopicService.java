package net.spfwork.forum.service;

import net.spfwork.forum.domain.Reply;
import net.spfwork.forum.domain.Topic;
import net.spfwork.forum.domain.User;
import net.spfwork.forum.dto.PageDTO;

import java.sql.SQLException;
import java.util.List;

public interface TopicService {

    PageDTO<Topic> findListBycId(int cId,int page ,int pageSize);
    Topic findTopicById(int topicId);

    PageDTO<Reply> findReplyPageByTopicId(int topicId, int page, int pageSize);


    int addTopic(User loginUser, String title, String content, int cId);

    int ReplyByTopicId(User loginUser, int topicId, String content) throws SQLException;

    void addOnePV(int topicId);
}
