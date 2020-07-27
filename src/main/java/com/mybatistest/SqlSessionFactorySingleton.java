package com.mybatistest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactorySingleton {

    private static SqlSessionFactorySingleton sqlSessionFactorySingleton = new SqlSessionFactorySingleton();

    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionFactorySingleton() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new IllegalArgumentException("配置错误");
        }
        // 构建sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSessionFactorySingleton getInstance() {
        return sqlSessionFactorySingleton;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
