package net.spfwork.forum.dao;

import net.spfwork.forum.domain.Reply;
import net.spfwork.forum.domain.User;
import net.spfwork.forum.util.DataSourceUtils;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ReplyDao {
    private QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());

    private BeanProcessor beanProcessor=new GenerousBeanProcessor();
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

    /*CREATE TABLE `reply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) DEFAULT NULL,
  `floor` int(11) DEFAULT NULL COMMENT '楼层编号，回复是不能删除的',
  `content` varchar(524) DEFAULT NULL COMMENT '回复内容',
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL COMMENT '回复人名称',
  `user_img` varchar(128) DEFAULT NULL COMMENT '回复人头像',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete` int(11) DEFAULT NULL COMMENT '0是正常，1是禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4
    * */
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
