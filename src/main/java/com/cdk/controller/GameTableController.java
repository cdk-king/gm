package com.cdk.controller;

import com.cdk.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class GameTableController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getAllGame")
    public Map<String,Object> getAllGame(@RequestBody Map map){

        //String id = (map.get("id") !=null?map.get("id").toString():"");

        String gameName = (map.get("gameName")!=null?map.get("gameName").toString():"");
        String gameTag = (map.get("gameTag")!=null?map.get("gameTag").toString():"");
        String game_describe = (map.get("game_describe")!=null?map.get("game_describe").toString():"");

        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");
        String isPage = (map.get("isPage")!=null?map.get("isPage").toString():"");

        System.out.println("gameName：" + gameName);
        System.out.println("game_describe：" + game_describe);
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

        String sql="select * from t_game where isDelete != 1  ";
        if(gameName!=""){
            sql+=" and gameName LIKE '%"+ gameName +"%'";
        }
        if(game_describe!=""){
            sql+=" and game_describe LIKE '%"+ game_describe +"%'";
        }
        if(gameTag!=""){
            sql+=" and gameTag LIKE '%"+ gameTag +"%'";
        }
        //0：全部，1：冻结，2：未冻结
        if(!Objects.equals(state,"") && !Objects.equals(state,"0")){
            System.out.println(Objects.equals(state,"1"));
            if(Objects.equals(state,"1")){
                System.out.println("1");
                sql+=" and state = 1 ";
            }else if(Objects.equals(state,"2")){
                sql+=" and state != 1 ";
            }
        }
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
        System.out.println("gameData：" + JsonMap);
        System.out.println("############################");

        return JsonMap;
    }
    @Transactional
    @RequestMapping(value="/editGame", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editGame(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String gameName = (map.get("gameName")!=null?map.get("gameName").toString():"");
        String game_describe = (map.get("game_describe")!=null?map.get("game_describe").toString():"");
        String gameTag = (map.get("gameTag")!=null?map.get("gameTag").toString():"");
        String sort = (map.get("sort")!=null?map.get("sort").toString():"");


        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");

        System.out.println("id：" + id);
        System.out.println("gameName：" + gameName);
        System.out.println("game_describe：" + game_describe);
        System.out.println("gameTag：" + gameTag);
        System.out.println("sort：" + sort);

        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result  re;
        //数据库表名如果和列名重复，需要用别名来区分
        String sql = "UPDATE t_game as a SET a.gameName='"+gameName+"',a.game_describe = '"+ game_describe +"',"
                +"a.gameTag='"+  gameTag+"' ,a.addUser = '"+ addUser +
                "' where a.id =" + id +"";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("游戏信息修改成功");
            re = new Result(200,"游戏信息更新成功",null );
        }else {
            System.out.println("游戏信息修改失败");
            re = new Result(400,"游戏信息更新失败",null );

        }
        System.out.println("############################");
        return re;
    }
    @Transactional
    @RequestMapping("/addGame")
    public Result addGame(@RequestBody Map map){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String id = (map.get("id") !=null?map.get("id").toString():"");
        String gameName = (map.get("gameName")!=null?map.get("gameName").toString():"");
        String gameTag = (map.get("gameTag")!=null?map.get("gameTag").toString():"");
        String game_describe = (map.get("game_describe")!=null?map.get("game_describe").toString():"");
        String sort = (map.get("sort")!=null?map.get("sort").toString():"");

        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        //String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");

        System.out.println("id：" + id);
        System.out.println("gameName：" + gameName);
        System.out.println("game_describe：" + game_describe);
        System.out.println("gameTag：" + gameTag);
        System.out.println("sort：" + sort);

        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result  re;

        //describe是MySQL的关键字
        String sql="insert into t_game (gameName,gameTag,game_describe,sort,addUser,addDatetime,state,isDelete) "+
                " values ('"+gameName+"','"+gameTag+"','"+game_describe+"','0','"+addUser+"','"+addDatetime+"','0','0')";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("游戏添加成功");
            re = new Result(200,"游戏添加成功",null );
        }else {
            System.out.println("游戏添加失败");
            re = new Result(400,"游戏添加失败",null );
        }
        System.out.println("############################");
        return re;
    }
    @Transactional
    @RequestMapping(value="/deleteGame", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteGame(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_game SET isDelete='1' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("游戏删除成功");
            re = new Result(200,"游戏删除成功",null );
        }else {
            System.out.println("游戏删除失败");
            re = new Result(400,"游戏删除失败",null );

        }
        System.out.println("############################");
        return re;
    }


    @RequestMapping(value="/changeStateToNormal_Game", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Game(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_game SET state='0' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("游戏解冻成功");
            re = new Result(200,"游戏解冻成功",null );
        }else {
            System.out.println("游戏解冻失败");
            re = new Result(400,"游戏解冻失败",null );

        }
        System.out.println("############################");
        return re;
    }

    @RequestMapping(value="/changeStateToFrozen_Game", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Game(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_game SET state='1' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("游戏冻结成功");
            re = new Result(200,"游戏冻结成功",null );
        }else {
            System.out.println("游戏冻结失败");
            re = new Result(400,"游戏冻结失败",null );

        }
        System.out.println("############################");
        return re;
    }
    @Transactional
    @RequestMapping(value="/deleteAllGame", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllGame(@RequestBody Map<String,String> map){
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

        for(int i = 0 ;i < ObjectArry.length;i++){
            //UPDATE user_roles  set  isDelete='1' where
            sql[i]="UPDATE  t_Game  set isDelete='1' where id = '"+ObjectArry[i]+"';";
            strSql += sql;
            //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
            //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
            //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        }
        System.out.println("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        Object args[] = new Object[]{id};
        if (temp.length !=0 ) {
            System.out.println("游戏批量删除成功");
            re = new Result(200,"游戏批量删除成功",null );
        }else if(ObjectArry.length==0){
            System.out.println("无任何删除操作");
            re = new Result(400,"无任何删除操作",null );
        }else{
            System.out.println("游戏批量删除失败");
            re = new Result(400,"游戏批量删除失败",null );
        }
        System.out.println("############################");
        return re;
    }
}
