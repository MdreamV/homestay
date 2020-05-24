package com.jtw.homestay.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class DaoUtils {
    private static SqlSessionFactory factory;
    private static InputStream in;

    static{
        //1.读取配置文件
        in = DaoUtils.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
    }
    /**
     * 获取sqlSession
     * @return
     */
    public static SqlSession getSession(){
        //3.创建Sqlsession对象,自动提交事务
        return factory.openSession(true);
    }

}
