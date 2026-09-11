package net.spfwork.forum.dao;

import net.spfwork.forum.domain.Category;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import net.spfwork.forum.util.DataSourceUtils;

import java.util.List;

/**
 * categoryDao类，用于处理分类数据相关的数据库操作
 * 使用QueryRunner和BeanProcessor来简化数据库操作和结果集映射
 */
public class CategoryDao {
    // 初始化QueryRunner对象，使用DataSourceUtils获取数据源
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

    //开启驼峰映射，使数据库字段名与Java对象属性名自动匹配
    private BeanProcessor beanProcessor = new GenerousBeanProcessor();
    // 配置RowProcessor，使用自定义的BeanProcessor处理结果集
    private RowProcessor processor = new BasicRowProcessor(beanProcessor);


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
            return queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
        } catch (Exception e) {
            // 捕获异常并转换为运行时异常抛出
            throw new RuntimeException(e);
        }
    }
}
