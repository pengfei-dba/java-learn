package net.spfwork.forum.dao;

import net.spfwork.forum.domain.Topic;
import net.spfwork.forum.util.DataSourceUtils;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class TopicDao {
    private QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());

    private BeanProcessor beanProcessor=new GenerousBeanProcessor();
    private RowProcessor rowProcessor=new BasicRowProcessor(beanProcessor);

    public int countTotalTopicBycId(int cId){
        String sql="select count(*) from topic where c_id=? and `delete`=0";
        long count=0;
        try {
            count=(Long)queryRunner.query(sql,new ScalarHandler(),cId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (int)count ;


    }

    public List<Topic> findListBycId(int cId,int from ,int pageSize){
        String sql="select * from topic where c_id=? and `delete`=0 order by update_time desc limit ?,?";
        List<Topic> topicList=null;

        try {
            topicList= queryRunner.query(sql,new BeanListHandler<>(Topic.class),cId,from,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return topicList;
    }

    public Topic findTopicById(int topicId){
        String sql="select * from topic where topic_id=? ";
        Topic topic=null;
        try {
            topic=queryRunner.query(sql,new BeanHandler<>(Topic.class,rowProcessor),topicId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return topic;
    }



}
