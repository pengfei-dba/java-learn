package net.spfwork.forum.service;

import net.spfwork.forum.domain.Reply;
import net.spfwork.forum.domain.Topic;
import net.spfwork.forum.dto.PageDTO;

import java.util.List;

public interface TopicService {

    PageDTO<Topic> findListBycId(int cId,int page ,int pageSize);
    Topic findTopicById(int topicId);

    PageDTO<Reply> findReplyPageByTopicId(int topicId, int page, int pageSize);



}
