package com.ludayo.dao;

import com.ludayo.domain.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    static InputStream  is;
    static SqlSession sqlSession;
    static UserDao userDao;
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        is= Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlSession工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory build = builder.build(is);
        //3.创建sqlSession
        sqlSession = build.openSession();
        //4.创建dao接口的代理对象
        userDao = sqlSession.getMapper(UserDao.class);

    }
    @After
    public void destory() throws IOException {
        //6.释放资源
        sqlSession.close();
        is.close();
    }

    @Test
    public void findAll() {
        //5.使用代理对象查询所有结果
        List<Users> all = userDao.findAll();
        for (Users user : all) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void register(){
        Users users=new Users();
        users.setUsername("lili");
        users.setAddress("hongshan");
        Date birthday=new Date();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(birthday);
        users.setBirthday(format);
        users.setSex("female");
        userDao.registerUser(users);
    }

    @Test
    public void findById() {
        int id=2;
        Users byId = userDao.findById(id);
        System.out.println("byId = " + byId);
    }
}