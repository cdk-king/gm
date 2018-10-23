package com.cdk.controller;

import com.cdk.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ServerTableController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getAllServer")
    public Map<String,Object> getAllServer(@RequestBody Map map){

        //String id = (map.get("id") !=null?map.get("id").toString():"");

        String server = (map.get("server")!=null?map.get("server").toString():"");
        String serverIp = (map.get("serverIp")!=null?map.get("serverIp").toString():"");
        String platformName = (map.get("platform")!=null?map.get("platform").toString():"");
        String server_describe = (map.get("server_describe")!=null?map.get("server_describe").toString():"");

        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");
        String isPage = (map.get("isPage")!=null?map.get("isPage").toString():"");

        System.out.println("server：" + server);
        System.out.println("serverIp：" + serverIp);
        System.out.println("platformName：" + platformName);
        System.out.println("server_describe：" + server_describe);
        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);


        String StrPageNo = (map.get("pageNo") !=null?map.get("pageNo").toString():"1");
        String StrPageSize = (map.get("pageSize") !=null?map.get("pageSize").toString():"5");

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

        String sql="select a.*,b.platform,c.gameName from t_gameserver as a left JOIN \n" +
                " t_gameplatform  as b on a.platformId = b.id and b.isDelete!=1  left JOIN \n" +
                " t_game as c on b.gameId = c.id and c.isDelete != 1  where a.isDelete != 1 ";

        if(server!=""){
            sql+=" and a.server LIKE '%"+ server +"%'";
        }
        if(server_describe!=""){
            sql+=" and a.server_describe LIKE '%"+ server_describe +"%'";
        }
        if(serverIp!=""){
            sql+=" and a.serverIp LIKE '%"+ serverIp +"%'";
        }
        if(platformName!=""){
            sql+=" and b.platform LIKE '%"+ platformName +"%'";
        }
        //0：全部，1：冻结，2：未冻结
        if(!Objects.equals(state,"") && !Objects.equals(state,"0")){
            System.out.println(Objects.equals(state,"1"));
            if(Objects.equals(state,"1")){
                System.out.println("1");
                sql+=" and a.state = 1 ";
            }else if(Objects.equals(state,"2")){
                sql+=" and a.state != 1 ";
            }
        }
        sql +=" order by a.id ";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        int total = list.size();
        if(!Objects.equals(isPage,"")){
            sql+=" limit "+(pageNo-1)*pageSize+", "+pageSize;
        }

        System.out.println("sql：" + sql);
        list=jdbcTemplate.queryForList(sql);
        Map<String,Object> JsonMap =  new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        System.out.println("serverData：" + JsonMap);
        System.out.println("############################");

        return JsonMap;
    }
    @Transactional
    @RequestMapping("/addServer")
    public Result addPlatform(@RequestBody Map map){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String id = (map.get("id") !=null?map.get("id").toString():"");

        String platformId = (map.get("platformId") !=null?map.get("platformId").toString():"");
        String server = (map.get("server")!=null?map.get("server").toString():"");
        String serverIp = (map.get("serverIp")!=null?map.get("serverIp").toString():"");

        String server_describe = (map.get("server_describe")!=null?map.get("server_describe").toString():"");
        String sort = (map.get("sort")!=null?map.get("sort").toString():"");

        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");

        System.out.println("id：" + id);
        System.out.println("server：" + server);
        System.out.println("server_describe：" + server_describe);
        System.out.println("serverIP：" + serverIp);

        System.out.println("sort：" + sort);

        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result  re;
        //describe是MySQL的关键字
        String sql="insert into t_gameserver (server,serverIp,server_describe,platformId,sort,addUser,addDatetime,state,isDelete) "+
                " values ('"+server+"','"+serverIp+"','"+server_describe+"','"+platformId+"','0','"+addUser+"','"+addDatetime+"','0','0')";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("服务器添加成功");
            re = new Result(200,"服务器添加成功",null );
        }else {
            System.out.println("服务器添加失败");
            re = new Result(400,"服务器添加失败",null );
        }
        System.out.println("############################");
        return re;
    }
    @Transactional
    @RequestMapping(value="/editServer", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editServer(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String server = (map.get("server")!=null?map.get("server").toString():"");
        String server_describe = (map.get("server_describe")!=null?map.get("server_describe").toString():"");
        String serverIp = (map.get("serverIp")!=null?map.get("serverIp").toString():"");
        String sort = (map.get("sort")!=null?map.get("sort").toString():"");

        String gameId = (map.get("gameId") !=null?map.get("gameId").toString():"");
        String platformId = (map.get("platformId") !=null?map.get("platformId").toString():"");

        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");

        System.out.println("id：" + id);
        System.out.println("server：" + server);
        System.out.println("server_describe：" + server_describe);
        System.out.println("serverIp：" + serverIp);
        System.out.println("sort：" + sort);

        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result  re;
        //数据库表名如果和列名重复，需要用别名来区分
        String sql = "UPDATE t_gameserver as a SET a.server='"+server+"',a.server_describe = '"+ server_describe +"',"
                +"a.platformId='"+platformId+"',"
                +"a.serverIp='"+  serverIp+"' ,a.addUser = '"+ addUser +
                "' where a.id =" + id +"";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("服务器信息修改成功");
            re = new Result(200,"服务器信息更新成功",null );
        }else {
            System.out.println("服务器信息修改失败");
            re = new Result(400,"服务器信息更新失败",null );

        }
        System.out.println("############################");
        return re;
    }

    @RequestMapping("/getAllPlatformList")
    public Result getAllRoleList(@RequestBody Map map){

        String sql="select * from t_gameplatform where isDelete != 1 ";

        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        System.out.println("sql：" + sql);

        Result  re;

        if (list.size()>0) {
            System.out.println("渠道列表获取成功");
            re = new Result(200,"渠道列表获取成功",list );
        }else {
            System.out.println("渠道列表获取失败");
            re = new Result(400,"渠道列表获取失败",list );

        }
        System.out.println("############################");
        return re;
    }


    public static final Result success = new Result(200,"服务器解冻成功",null );
    @RequestMapping(value="/changeStateToNormal_Server", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Platform(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_gameserver SET state='0' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("服务器解冻成功");
            re = success;
        }else {
            System.out.println("服务器解冻失败");
            re = new Result(400,"服务器解冻失败",null );

        }
        System.out.println("############################");
        return re;
    }

    @RequestMapping(value="/changeStateToFrozen_Server", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Platform(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_gameserver SET state='1' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("服务器冻结成功");
            re = new Result(200,"服务器冻结成功",null );
        }else {
            System.out.println("服务器冻结失败");
            re = new Result(400,"服务器冻结失败",null );

        }
        System.out.println("############################");
        return re;
    }
    @Transactional
    @RequestMapping(value="/deleteServer", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteServer(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_gameserver SET isDelete='1' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("服务器删除成功");
            re = new Result(200,"服务器删除成功",null );
        }else {
            System.out.println("服务器删除失败");
            re = new Result(400,"服务器删除失败",null );

        }
        System.out.println("############################");
        return re;
    }

    @Transactional
    @RequestMapping(value="/deleteAllServer", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllServer(@RequestBody Map<String,String> map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        System.out.println("id：" + id);
        if(Objects.equals(id,"")){
            System.out.println("无任何批量删除操作");
            System.out.println("############################");
            return new Result(400,"无任何批量删除操作",null );
        }

        String[] ObjectArry =id.split( ",");
        System.out.println("ObjectArry：" + ObjectArry);

        Result  re;
        String sql[] = new String[ObjectArry.length] ;
        String strSql = "";
        int[] temp = new int[ObjectArry.length];

        //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
        //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
        //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        for(int i = 0 ;i < ObjectArry.length;i++){
            sql[i]="UPDATE  t_gameserver  set isDelete='1' where id = '"+ObjectArry[i]+"'; ";
            strSql += sql;
        }
        System.out.println("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        Object args[] = new Object[]{id};
        if (temp.length !=0 ) {
            System.out.println("服务器批量删除成功");
            re = new Result(200,"服务器批量删除成功",null );
        }else if(ObjectArry.length==0){
            System.out.println("无任何删除操作");
            re = new Result(400,"无任何删除操作",null );
        }else{
            System.out.println("服务器批量删除失败");
            re = new Result(400,"服务器批量删除失败",null );
        }
        System.out.println("############################");
        return re;
    }

    /**
      * 〈获取用户所在的渠道平台列表〉
      * 〈通过用户的UserId查找到用户的角色列表，在通过用户角色查找到该角色对应的唯一渠道平台〉
      * @param  [map]   [获取一个hashMap对象，从中取得键值为id的值(userId)]
      * @return [Result对象，包含响应码code，信息message和数据data，data是包含list的结果集]
      * @exception/throws [违例类型] [违例说明]
      * @see [类、类#方法、类#成员]
      * @deprecated
      */
    @RequestMapping("/getPlatformListForUser")
    public Result getPlatformListForUser(@RequestBody Map map){

        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        String sql="SELECT d.id as platformId, d.platform as platformName  from t_user as a \n" +
                "join t_user_roles as b on a.id = b.userId \n" +
                "join t_role as c on b.roleId = c.id \n" +
                "join t_gameplatform as d on c.id = d.roleId  \n" +
                "where a.isDelete != 1 and c.isDelete!=1 and d.isDelete!=1 and  a.id =" + id;

        System.out.println("sql：" + sql);
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        System.out.println(list);
        Result re;
        re = new Result(200,"用户渠道列表获取成功",list );
        System.out.println("############################");

        return re;
    }

    /**
      * 〈获取某渠道平台上的所有服务器列表〉
      * 〈通过平台的PlatformId查找到该渠道的所以服务器列表〉
      * @param  [map]   [获取一个hashMap对象，从中取得键值为id的值(platformIm)]
      * @return [Result对象，包含响应码code，信息message和数据data，data是包含list的结果集]
      * @exception/throws [违例类型] [违例说明]
      * @see [类、类#方法、类#成员]
      * @deprecated
      */
    @RequestMapping("/getServerListForUser")
    public Result getServerListForUser(@RequestBody Map map){

        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        String sql="SELECT a.id as serverId,a.server as serverName,a.serverIp from t_gameserver as a \n" +
                "join t_gameplatform as b on a.platformId = b.id \n" +
                "where a.isDelete != 1 and b.isDelete!=1  and  b.id =" + id;

        System.out.println("sql：" + sql);
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        System.out.println(list);
        Result re;
        re = new Result(200,"渠道服务器列表获取成功",list );
        System.out.println("############################");

        return re;
    }

    /**
      * 〈获取所有服务器树状结构〉
      * 〈获取所有服务器树状结构〉
      * @param  [map]   [获取一个hashMap对象，从中取得键值为id的值(platformIm)]
      * @return [Result对象，包含响应码code，信息message和数据data，data是包含list的结果集]
      * @exception/throws [违例类型] [违例说明]
      * @see [类、类#方法、类#成员]
      * @deprecated
      */
    @RequestMapping("/getServerTree")
    public Result getServerTree(@RequestBody Map map){

        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        String sql="SELECT a.id as gameId ,b.id as platformId,c.id as serverId,a.gameName,b.platform,c.server FROM t_game as a join t_gameplatform as b on a.id = b.gameId \n" +
                "join t_gameserver as c on b.id = c.platformId where a.isDelete!=1 and b.isDelete!=1 and c.isDelete !=1 ORDER BY a.id ,b.id,c.id";

        System.out.println("sql：" + sql);
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        System.out.println(list);
        Result re;
        re = new Result(200,"服务器树状结构获取成功",list );
        System.out.println("############################");

        return re;
    }


}
