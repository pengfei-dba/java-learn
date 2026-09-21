package net.spfwork.forum.dao;

import net.spfwork.forum.domain.Reply;
import net.spfwork.forum.domain.User;
import net.spfwork.forum.util.DataSourceUtils;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import net.spfwork.forum.util.LocalDateTimeBeanProcessor;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ReplyDao {
    private QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());

    private BeanProcessor beanProcessor=new LocalDateTimeBeanProcessor();
    private RowProcessor rowProcessor=new BasicRowProcessor(beanProcessor);


    public int countTotalReplyByTopicId(int topicId){
        String sql="select count(*) from reply where topic_id=?";
        long count=0;
        try {
            count=queryRunner.query(sql,new ScalarHandler<>(),topicId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (int)count;
    }

    public List<Reply>  findListByTopicId(int topicId,int from,int pageSize){
        String sql="select * from reply where topic_id=? order by create_time asc limit ?,?";
        List<Reply> replyList = null;
        try {
            replyList=queryRunner.query(sql,new BeanListHandler<>(Reply.class,rowProcessor),topicId,from,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return replyList;
    }


    public int save(Reply reply) throws SQLException {
        String sql="insert into reply (topic_id,floor,content,user_id,username,user_img,create_time,update_time,`delete`) values (?,?,?,?,?,?,?,?,?)";
        Object [] params={
                reply.getTopicId(),reply.getFloor(),reply.getContent(),
                reply.getUserId(),reply.getUsername(),reply.getUserImg(),
                LocalDateTime.now(),LocalDateTime.now(),reply.getDelete()
        };
        int rows=0;
        try {
            rows=queryRunner.update(sql,params);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rows;
    }
}
