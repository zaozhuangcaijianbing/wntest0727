package com.mybatistest;

import org.apache.ibatis.session.SqlSession;

public class MybatisTest {
    public static void main(String[] args) throws Exception {

        SqlSession sqlSession = SqlSessionFactorySingleton.getInstance().getSqlSession();
        try {
            //方式1，不使用mapper接口
            User user = sqlSession.selectOne("UserMapper.selectUser");
            User user3 = sqlSession.selectOne("UserMapper.selectUser");
            System.out.println(user);
        } finally {
            sqlSession.close();
        }


        SqlSession sqlSession2 = SqlSessionFactorySingleton.getInstance().getSqlSession();
        try {
            //方式2，使用mapper接口，推荐写法
            TokenMapper tokenMapper = sqlSession2.getMapper(TokenMapper.class);
            Token token = tokenMapper.queryOneToken();
            System.out.println(token);
        } finally {
            sqlSession2.close();
        }


    }
}
