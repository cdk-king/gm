package com.cdk.controller;

import com.cdk.entity.Demo;
import com.cdk.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;

/**
 *  * 〈一句话功能简述〉
 *  * 〈功能详细描述〉
 *  * @author    [作者]
 *  * @version   [版本号, YYYY-MM-DD]
 *  * @see       [相关类/方法]
 *  * @since     [产品/模块版本]
 *  * @deprecated
 *  
 */
@RestController
//@RestController注解相当于@ResponseBody ＋ @Controller
public class HomeController {
    private static Logger logger = Logger.getLogger(String.valueOf(HomeController.class));
    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
      * 〈一句话功能简述〉
      * 〈功能详细描述〉
      * @param  [参数1]   [参数1说明]
      * @param  [参数2]   [参数2说明]
      * @return [返回类型说明]
      * @exception/throws [违例类型] [违例说明]
      * @see [类、类#方法、类#成员]
      * @deprecated
      */

    /**
     * 测试保存数据方法.
     *
     * @return
     */
    @Resource
    private DemoService demoService;

    @RequestMapping("/save")
    public String save() {
        Demo d = new Demo();
        d.setName("Angel");
        demoService.save(d);//保存数据.
        return "ok.DemoController.save";
    }

    @RequestMapping("/getCount")
    public int getCount() {
        String sql = "select count(*) from t_user";
        int str = jdbcTemplate.queryForObject(sql, int.class);
        return str;
    }

    @RequestMapping("/getString")
    public String getString() {
        String sql = "select * from t_user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list.toString();
    }

    @RequestMapping("/testGet/{s}")
    public String testGet1(@PathVariable("s") String s) {
        return s;
    }

    @RequestMapping("/testGet")
    public String testGet2(@RequestParam("name") String s) {
        return s;
    }


    @RequestMapping("/delLastUser")
    public String delLastUser() {
        String sql = "delete from t_user where id in\n" + "(select n.id from (select id from t_user order by id desc limit 1) as n)";
        jdbcTemplate.execute(sql);
        return "delLastUser";
    }
}
