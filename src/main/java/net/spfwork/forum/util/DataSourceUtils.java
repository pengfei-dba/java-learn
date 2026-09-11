package net.spfwork.forum.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * 数据源工具类，用于创建和管理数据源
 * 通过读取配置文件datasource.properties来初始化数据源
 */
public class DataSourceUtils {
    // 静态数据源对象，用于整个应用程序共享
    private static  DataSource dataSource;

    /**
     * 静态代码块，在类加载时执行，用于初始化数据源
     * 从类路径下读取datasource.properties配置文件
     * 并使用BasicDataSourceFactory创建数据源
     */
    static {
        // 通过类加载器获取配置文件的输入流
        try(InputStream in = DataSourceUtils.class.getClassLoader().getResourceAsStream("datasource.properties");){
            // 创建Properties对象，用于加载配置文件
            Properties properties=new Properties();
            // 加载配置文件
            properties.load(in);
            // 使用BasicDataSourceFactory创建数据源
            dataSource= BasicDataSourceFactory.createDataSource(properties);

        }catch (Exception e){
            // 异常处理，打印堆栈跟踪信息
            e.printStackTrace();
            throw new ExceptionInInitializerError("初始化数据源失败");
        }
    }

    /**
     * 获取数据源的方法
     * @return 返回初始化好的数据源对象
     */
    public static DataSource getDataSource() {
        return dataSource;
    }
}
