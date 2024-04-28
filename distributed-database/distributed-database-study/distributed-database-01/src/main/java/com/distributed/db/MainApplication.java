package com.distributed.db;

import com.distributed.db.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MainApplication {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            // MyBatis会自动创建一个代理对象，由代理对象去执行增删改查方法
            UserMapper mapper = session.getMapper(UserMapper.class);

            // 执行业务
            mapper.list();

            // 手动提交事务
            session.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
