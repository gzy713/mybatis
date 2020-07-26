package com.lamp;

import com.lamp.bean.Employee;
import com.lamp.dao.EmployeeDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class test {

    /*
    *
    * sqlSession与connecttion 是线程不安全的  所以不要写在 全局变量中 要写在局部变量中
    *
    * */

    @Test
    public  void test() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        Employee employee = new Employee();
        employee.setLastName("tom");
        employee.setEmail("126@qq.com");
        employee.setGender("男");
        int insert = sqlSession.insert("test.insertUser", employee);
        System.out.println(insert);
        sqlSession.commit();

    }


    @Test
    public  void test02() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        List<Object> objects = sqlSession.selectList("test.findEmp");
        System.out.println(objects);

    }

    @Test

    public  void test03() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        EmployeeDao mapper = sqlSession.getMapper(EmployeeDao.class);
        Employee byId = mapper.findById(3);
        System.out.println(byId);

    }




}
