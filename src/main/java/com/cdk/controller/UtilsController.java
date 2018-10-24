package com.cdk.controller;

import com.cdk.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cdk.util.MD5Util.convertMD5;
import static com.cdk.util.MD5Util.md5Decode;
import static com.cdk.util.MD5Util.string2MD5;

@RestController
public class UtilsController {

    public static final String  Divider= "############################";

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
    @RequestMapping("/md5")
    public String md5(HttpServletRequest request){
        String s = request.getParameter("psw");
        System.out.println(md5Decode("a6aeb3ffa55fc7d664406af9c3bd0f1b"));
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + string2MD5(s));
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));
        String re = "原始：" + s + "<br/>" + "MD5后：" + string2MD5(s) + "<br/>" + "加密的：" + convertMD5(s) + "<br/>" + "解密的：" + convertMD5(convertMD5(s));
        return re;
    }

    @RequestMapping("/getUserAllRight")
    public Result getUserAllRight(@RequestBody Map map){

        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        String sql="SELECT e.id,e.name,c.role,a.rightName,a.rightTag from t_right as a join\n" +
                "t_role_rights as b on a.id = b.rightId join t_role as c on c.id = b.roleId " +
                "join t_user_roles as d on c.id = d.roleId join t_user as e on d.userId = e.id " +
                "where e.isDelete != 1 and c.isDelete!=1 and a.isDelete!=1  and  e.id = " + id;

        System.out.println("sql：" + sql);
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        System.out.println("list.size()：" + list.size());
        Object[] str =  new String[list.size() ];

        for(int i = 0;i<list.size();i++){
            str[i] = list.get(i).get("rightTag");
        }

        Result re;

        Map<String,Object> JsonMap =  new HashMap();
        JsonMap.put("list", list);
        re = new Result(200,"用户权限组获取成功",str );

        System.out.println("RightData：" + str);
        System.out.println(Divider);

        return re;
    }

    @RequestMapping("/getUserAllRole")
    public Result getUserAllRole(@RequestBody Map map){

        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        String sql="SELECT a.id,c.name,a.role from t_role as a " +
                "join t_user_roles as b on a.id = b.roleId join t_user as c on b.userId = c.id " +
                "where a.isDelete != 1 and c.isDelete!=1 and  c.id = " + id;

        System.out.println("sql：" + sql);
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        System.out.println("list.size()：" + list.size());
        System.out.println(list);
        Object[] str =  new String[list.size() ];

        for(int i = 0;i<list.size();i++){
            str[i] = list.get(i).get("id").toString();
        }

        Result re;

        Map<String,Object> JsonMap =  new HashMap();
        JsonMap.put("list", list);
        re = new Result(200,"用户角色组获取成功",str );

        System.out.println(str);
        System.out.println(Divider);

        return re;
    }
}
