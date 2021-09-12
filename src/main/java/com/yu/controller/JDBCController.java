package com.yu.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 13:56 2021/9/8
 * @Modified By:
 */
@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库的所有信息
    @RequestMapping("/user")
    public List<Map<String, Object>> userlist()
    {
        String sql = "select * from mybatis.student";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
        return list_maps;
    }

    @GetMapping("/adduser")
    public String addUser()
    {
        String sql = "insert into mybatis.first(id,name,pwd) values(4,'小明','123456')";
        jdbcTemplate.update(sql);
        return "add-ok";
    }

    @GetMapping("/updateuser/{id}")
    public String updateUser(@PathVariable("id")int id)
    {
        String sql = "update mybatis.first set name=?, pwd=? where id="+id;
        Object[] objects = new Object[2];
        objects[0] = "小明";
        objects[1] = "zzzzzz";
        jdbcTemplate.update(sql,objects);
        return "updateUser-ok";
    }

    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") int id)
    {
        String sql = "delete from mybatis.first where id=?";
        jdbcTemplate.update(sql,id);
        return "delete-ok";
    }
}
