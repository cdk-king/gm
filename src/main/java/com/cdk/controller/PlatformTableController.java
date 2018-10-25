package com.cdk.controller;

import com.cdk.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

@RestController
public class PlatformTableController {

    public static final String Divider = "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getAllPlatform")
    public Map<String, Object> getAllPlatform(@RequestBody Map map) {

        //String id = (map.get("id") !=null?map.get("id").toString():"");

        String platformName = (map.get("platform") != null ? map.get("platform").toString() : "");
        String platformTag = (map.get("platformTag") != null ? map.get("platformTag").toString() : "");
        String platform_describe = (map.get("platform_describe") != null ? map.get("platform_describe").toString() : "");

        String gameName = (map.get("gameName") != null ? map.get("gameName").toString() : "");

        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String addDatetime = (map.get("addDatetime") != null ? map.get("addDatetime").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");

        System.out.println("platformName：" + platformName);
        System.out.println("platform_describe：" + platform_describe);
        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);


        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");

        System.out.println("pageNo：" + StrPageNo);
        System.out.println("pageSize：" + StrPageSize);
        int pageNo = 1;
        int pageSize = 5;

        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String sql = "select a.*,b.gameName,c.role from t_gameplatform as a left JOIN " +
                "  t_game  as b on a.gameId = b.id and b.isDelete!=1  left JOIN" +
                "  t_role as c on a.roleId = c.id and c.isDelete != 1  where a.isDelete != 1 ";

        if (platformName != "") {
            sql += " and a.platform LIKE '%" + platformName + "%'";
        }
        if (platform_describe != "") {
            sql += " and a.platform_describe LIKE '%" + platform_describe + "%'";
        }
        if (platformTag != "") {
            sql += " and a.platformTag LIKE '%" + platformTag + "%'";
        }
        if (gameName != "") {
            sql += " and b.gameName LIKE '%" + gameName + "%'";
        }
        //0：全部，1：冻结，2：未冻结
        if (!Objects.equals(state, "") && !Objects.equals(state, "0")) {
            System.out.println(Objects.equals(state, "1"));
            if (Objects.equals(state, "1")) {
                System.out.println("1");
                sql += " and state = 1 ";
            } else if (Objects.equals(state, "2")) {
                sql += " and state != 1 ";
            }
        }

        sql += " order by a.id ";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        System.out.println("sql：" + sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        System.out.println("platformData：" + JsonMap);
        System.out.println(Divider);

        return JsonMap;
    }

    @RequestMapping("/getAllGameList")
    public Result getAllGameList(@RequestBody Map map) {

        String sql = "select * from t_game where isDelete != 1 ";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println("sql：" + sql);

        Result re;

        if (list.size() > 0) {
            System.out.println("游戏列表获取成功");
            re = new Result(200, "游戏列表获取成功", list);
        } else {
            System.out.println("游戏列表获取失败");
            re = new Result(400, "游戏列表获取失败", list);

        }
        System.out.println(Divider);
        return re;
    }


    @RequestMapping("/getAllRoleList")
    public Result getAllRoleList(@RequestBody Map map) {

        String sql = "select * from t_role where isDelete != 1 ";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println("sql：" + sql);

        Result re;

        if (list.size() > 0) {
            System.out.println("角色列表获取成功");
            re = new Result(200, "角色列表获取成功", list);
        } else {
            System.out.println("角色列表获取失败");
            re = new Result(400, "角色列表获取失败", list);

        }
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping("/addPlatform")
    public Result addPlatform(@RequestBody Map map) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String id = (map.get("id") != null ? map.get("id").toString() : "");

        String gameId = (map.get("gameId") != null ? map.get("gameId").toString() : "");
        String roleId = (map.get("roleId") != null ? map.get("roleId").toString() : "");

        String platform = (map.get("platform") != null ? map.get("platform").toString() : "");
        String platformTag = (map.get("platformTag") != null ? map.get("platformTag").toString() : "");
        String ParentId = (map.get("ParentId") != null ? map.get("ParentId").toString() : "");
        String platform_describe = (map.get("platform_describe") != null ? map.get("platform_describe").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");

        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        System.out.println("id：" + id);
        System.out.println("platform：" + platform);
        System.out.println("platform_describe：" + platform_describe);
        System.out.println("platformTag：" + platformTag);

        System.out.println("gameId：" + gameId);

        System.out.println("sort：" + sort);

        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result re;
        //describe是MySQL的关键字
        String sql = "insert into t_gameplatform (platform,platformTag,platform_describe,gameId,roleId,sort,addUser,addDatetime,state,isDelete) " +
                " values ('" + platform + "','" + platformTag + "','" + platform_describe + "','" + gameId + "','" + roleId + "','0','" + addUser +
                "','" + addDatetime + "','0','0')";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("权限添加成功");
            re = new Result(200, "权限添加成功", null);
        } else {
            System.out.println("权限添加失败");
            re = new Result(400, "权限添加失败", null);
        }
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping(value = "/editPlatform", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editPlatform(@RequestBody Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        String platform = (map.get("platform") != null ? map.get("platform").toString() : "");
        String platform_describe = (map.get("platform_describe") != null ? map.get("platform_describe").toString() : "");
        String platformTag = (map.get("platformTag") != null ? map.get("platformTag").toString() : "");
        String ParentId = (map.get("ParentId") != null ? map.get("ParentId").toString() : "");
        String sort = (map.get("sort") != null ? map.get("sort").toString() : "");

        String gameId = (map.get("gameId") != null ? map.get("gameId").toString() : "");
        String roleId = (map.get("roleId") != null ? map.get("roleId").toString() : "");

        String addUser = (map.get("addUser") != null ? map.get("addUser").toString() : "");
        String addDatetime = (map.get("addDatetime") != null ? map.get("addDatetime").toString() : "");
        String state = (map.get("state") != null ? map.get("state").toString() : "");

        System.out.println("id：" + id);
        System.out.println("platform：" + platform);
        System.out.println("platform_describe：" + platform_describe);
        System.out.println("platformTag：" + platformTag);
        System.out.println("ParentId：" + ParentId);
        System.out.println("sort：" + sort);

        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result re;
        //数据库表名如果和列名重复，需要用别名来区分
        String sql =
                "UPDATE t_gameplatform as a SET a.platform='" + platform + "',a.platform_describe = '" + platform_describe + "'," + "a.gameId='" +
                        gameId + "',a.roleId='" + roleId + "'," + "a.platformTag='" + platformTag + "' ,a.addUser = '" + addUser + "' where a.id =" +
                        id + "";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("平台信息修改成功");
            re = new Result(200, "平台信息更新成功", null);
        } else {
            System.out.println("平台信息修改失败");
            re = new Result(400, "平台信息更新失败", null);

        }
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping(value = "/deletePlatform", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deletePlatform(@RequestBody Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");

        System.out.println("id：" + id);

        Result re;
        String sql = "UPDATE t_gameplatform SET isDelete='1' where id ='" + id + "'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("平台删除成功");
            re = new Result(200, "平台删除成功", null);
        } else {
            System.out.println("平台删除失败");
            re = new Result(400, "平台删除失败", null);

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/changeStateToNormal_Platform", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Platform(@RequestBody Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");

        System.out.println("id：" + id);

        Result re;
        String sql = "UPDATE t_gameplatform SET state='0' where id ='" + id + "'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("游戏解冻成功");
            re = new Result(200, "游戏解冻成功", null);
        } else {
            System.out.println("游戏解冻失败");
            re = new Result(400, "游戏解冻失败", null);

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value = "/changeStateToFrozen_Platform", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Platform(@RequestBody Map map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");

        System.out.println("id：" + id);

        Result re;
        String sql = "UPDATE t_gameplatform SET state='1' where id ='" + id + "'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("平台冻结成功");
            re = new Result(200, "平台冻结成功", null);
        } else {
            System.out.println("平台冻结失败");
            re = new Result(400, "平台冻结失败", null);

        }
        System.out.println(Divider);
        return re;
    }

    @Transactional
    @RequestMapping(value = "/deleteAllPlatform", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllPlatform(@RequestBody Map<String, String> map) {
        String id = (map.get("id") != null ? map.get("id").toString() : "");
        System.out.println("id：" + id);
        if (Objects.equals(id, "")) {
            System.out.println("无任何批量删除操作");
            System.out.println(Divider);
            return new Result(400, "无任何批量删除操作", null);
        }

        String[] ObjectArry = id.split(",");
        System.out.println("ObjectArry：" + ObjectArry);

        Result re;
        String sql[] = new String[ObjectArry.length];
        String strSql = "";
        int[] temp = new int[ObjectArry.length];

        //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
        //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
        //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        for (int i = 0; i < ObjectArry.length; i++) {
            sql[i] = "UPDATE  t_gameplatform  set isDelete='1' where id = '" + ObjectArry[i] + "'; ";
            strSql += sql;
        }
        System.out.println("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        Object args[] = new Object[]{id};
        if (temp.length != 0) {
            System.out.println("平台批量删除成功");
            re = new Result(200, "平台批量删除成功", null);
        } else if (ObjectArry.length == 0) {
            System.out.println("无任何删除操作");
            re = new Result(400, "无任何删除操作", null);
        } else {
            System.out.println("平台批量删除失败");
            re = new Result(400, "平台批量删除失败", null);
        }
        System.out.println(Divider);
        return re;
    }

}
