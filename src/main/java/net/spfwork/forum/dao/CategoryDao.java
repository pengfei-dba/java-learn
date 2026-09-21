package net.spfwork.forum.dao;

import net.spfwork.forum.domain.Category;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import net.spfwork.forum.util.DataSourceUtils;
import net.spfwork.forum.util.LocalDateTimeBeanProcessor;

import java.util.List;


/**
 * categoryDao类，用于处理分类数据相关的数据库操作
 * 使用QueryRunner和BeanProcessor来简化数据库操作和结果集映射
 */
public class CategoryDao {
    // 初始化QueryRunner对象，使用DataSourceUtils获取数据源
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

    private BeanProcessor beanProcessor=new LocalDateTimeBeanProcessor();
    private RowProcessor rowProcessor=new BasicRowProcessor(beanProcessor);


    /**
     * 获取所有分类信息的方法
     * @return 返回包含所有分类信息的List集合
     * @throws RuntimeException 当查询发生异常时抛出运行时异常
     */
    public List<Category> list(){
        // 定义查询所有分类的SQL语句
        String sql="select * from category;";
        try {
            // 执行查询，将结果映射为category对象列表并返回
            return queryRunner.query(sql,new BeanListHandler<Category>(Category.class,rowProcessor));
        } catch (Exception e) {
            // 捕获异常并转换为运行时异常抛出
            throw new RuntimeException(e);
        }
    }

    public Category findById(int id){
        String sql="select * from category where id=?";
        Category category=null;
        try{
            category=queryRunner.query(sql,new BeanHandler<>(Category.class,rowProcessor),id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }
}
