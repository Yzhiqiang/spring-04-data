package com.yu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Spring04DataApplicationTests {
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        //获取数据库链接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //xxx Template:Springboot 已将配置好模板，拿来即用

        connection.close();
    }

}
