package net.spfwork.forum.dao;

import net.spfwork.forum.domain.User;
import net.spfwork.forum.util.DataSourceUtils;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import net.spfwork.forum.util.LocalDateTimeBeanProcessor;
import java.sql.SQLException;

public class UserDao {

    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

    private BeanProcessor beanProcessor=new LocalDateTimeBeanProcessor();
    private RowProcessor rowProcessor=new BasicRowProcessor(beanProcessor);

/*    private int id;
    private String phone;
    private String pwd;
    private int sex;
    private String img;
    private LocalDateTime createTime;
    private int role;
    private String username;
* */
    public int save(User user) throws SQLException {
        String sql="insert into user (phone,pwd,sex,img,create_time,role,username) values (?,?,?,?,?,?,?)";
        Object [] param={
                user.getPhone(),user.getPwd(),user.getSex(),user.getImg(),user.getCreateTime(),user.getRole(),user.getUsername()
        };
        int i;
        try{
            i=queryRunner.update(sql,param);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return i;
    }
    public User findByPhoneAndPwd(String phone,String pwdMd5)  throws SQLException{
        String sql="select * from user where phone=? and pwd=?";
        User user=null;
        try{
            user=queryRunner.query(sql,new BeanHandler<>(User.class,rowProcessor),phone,pwdMd5);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }


}
