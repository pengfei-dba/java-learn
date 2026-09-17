package net.spfwork.forum.dao;

import net.spfwork.forum.domain.Topic;
import net.spfwork.forum.domain.User;
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
        String sql="select * from topic where id=? ";
        Topic topic=null;
        try {
            topic=queryRunner.query(sql,new BeanHandler<>(Topic.class,rowProcessor),topicId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return topic;
    }


    public int save(Topic topic) {
        String sql="insert into topic (id,c_id,title,content,user_id,username,user_img,create_time,update_time,hot,`delete`) values (?,?,?,?,?,?,?,?,?,?,?)";
        Object [] param={
                topic.getId(), topic.getcId(),topic.getTitle(),
                topic.getContent(),topic.getUserId(),topic.getUsername(),
                topic.getUserImg(),topic.getCreateTime(),topic.getUpdateTime(),topic.getHot(),
                topic.getDelete()
        };
        int rows=0;
        try {
            rows=queryRunner.update(sql,param);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows;
    }

    public int findLatestFloorByTopicId(int topicId) {
        String sql="select max(floor) from reply where topic_id=?";
        int latestFloor=0;
        try {
            latestFloor=(Integer)queryRunner.query(sql,new ScalarHandler(),topicId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return latestFloor;
    }



    public void updatePv(int topicId, int newPv, int pv) {
        String sql="update topic set pv=? where id=? and pv=?";
        try {
            queryRunner.update(sql,newPv,topicId,pv);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
