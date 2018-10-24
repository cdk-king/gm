package com.cdk.controller;

import com.cdk.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class RightTableController {

    public static final String  Divider= "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getRight")
    public Map<String,Object> getRight(@RequestBody Map map){

        //String id = (map.get("id") !=null?map.get("id").toString():"");

        String rightName = (map.get("rightName")!=null?map.get("rightName").toString():"");
        String rightTag = (map.get("rightTag")!=null?map.get("rightTag").toString():"");
        String right_describe = (map.get("right_describe")!=null?map.get("right_describe").toString():"");

        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");
        String isPage = (map.get("isPage")!=null?map.get("isPage").toString():"");

        System.out.println("rightName：" + rightName);
        System.out.println("right_describe：" + right_describe);
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

        String sql="select * from t_right where isDelete != 1  ";
        if(rightName!=""){
            sql+=" and rightName LIKE '%"+ rightName +"%'";
        }
        if(right_describe!=""){
            sql+=" and right_describe LIKE '%"+ right_describe +"%'";
        }
        if(rightTag!=""){
            sql+=" and rightTag LIKE '%"+ rightTag +"%'";
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

        System.out.println("RightData：" + JsonMap);
        System.out.println(Divider);

        return JsonMap;
    }

    @RequestMapping(value="/editRight", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editRight(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String rightName = (map.get("rightName")!=null?map.get("rightName").toString():"");
        String right_describe = (map.get("right_describe")!=null?map.get("right_describe").toString():"");
        String rightTag = (map.get("rightTag")!=null?map.get("rightTag").toString():"");
        String rightParentId = (map.get("rightParentId")!=null?map.get("rightParentId").toString():"");
        String rightSort = (map.get("rightSort")!=null?map.get("rightSort").toString():"");


        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");

        System.out.println("id：" + id);
        System.out.println("rightName：" + rightName);
        System.out.println("right_describe：" + right_describe);
        System.out.println("rightTag：" + rightTag);
        System.out.println("rightParentId：" + rightParentId);
        System.out.println("rightSort：" + rightSort);

        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result  re;
        //数据库表名如果和列名重复，需要用别名来区分
        String sql = "UPDATE t_right as a SET a.rightName='"+rightName+"',a.right_describe = '"+ right_describe +"',"
                //+"a.rightParentId=0,a.rightSort=0,"
                +"a.rightTag='"+  rightTag+"' ,a.addUser = '"+ addUser +
                "' where a.id =" + id +"";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("权限信息修改成功");
            re = new Result(200,"权限信息更新成功",null );
        }else {
            System.out.println("权限信息修改失败");
            re = new Result(400,"权限信息更新失败",null );

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/addRight")
    public Result addRight(@RequestBody Map map){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String id = (map.get("id") !=null?map.get("id").toString():"");
        String rightName = (map.get("rightName")!=null?map.get("rightName").toString():"");
        String rightTag = (map.get("rightTag")!=null?map.get("rightTag").toString():"");
        String rightParentId = (map.get("rightParentId")!=null?map.get("rightParentId").toString():"");
        String right_describe = (map.get("right_describe")!=null?map.get("right_describe").toString():"");
        String rightSort = (map.get("rightSort")!=null?map.get("rightSort").toString():"");

        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        //String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");

        System.out.println("id：" + id);
        System.out.println("rightName：" + rightName);
        System.out.println("right_describe：" + right_describe);
        System.out.println("rightTag：" + rightTag);
        System.out.println("rightSort：" + rightSort);

        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result  re;

        //describe是MySQL的关键字
        String sql="insert into t_right (rightName,rightTag,right_describe,rightSort,rightParentId,addUser,addDatetime,state,isDelete) "+
                " values ('"+rightName+"','"+rightTag+"','"+right_describe+"','0','0','"+addUser+"','"+addDatetime+"','0','0')";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("权限添加成功");
            re = new Result(200,"权限添加成功",null );
        }else {
            System.out.println("权限添加失败");
            re = new Result(400,"权限添加失败",null );
        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/changeStateToFrozen_Right", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Right(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_right SET state='1' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("权限冻结成功");
            re = new Result(200,"权限冻结成功",null );
        }else {
            System.out.println("权限冻结失败");
            re = new Result(400,"权限冻结失败",null );

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/changeStateToNormal_Right", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Right(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_right SET state='0' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("权限解冻成功");
            re = new Result(200,"权限解冻成功",null );
        }else {
            System.out.println("权限解冻失败");
            re = new Result(400,"权限解冻失败",null );

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/deleteRight", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteRight(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_right SET isDelete='1' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("权限删除成功");
            re = new Result(200,"权限删除成功",null );
        }else {
            System.out.println("权限删除失败");
            re = new Result(400,"权限删除失败",null );

        }
        System.out.println(Divider);
        return re;
    }


    @RequestMapping(value="/deleteAllRight", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllRight(@RequestBody Map<String,String> map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        System.out.println("id：" + id);
        if(Objects.equals(id,"")){
            System.out.println("无任何批量删除操作");
            System.out.println(Divider);
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
            sql[i]="UPDATE  t_right  set isDelete='1' where id = '"+ObjectArry[i]+"';";
            strSql += sql;
            //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
            //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
            //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        }
        System.out.println("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        Object args[] = new Object[]{id};
        if (temp.length !=0 ) {
            System.out.println("权限批量删除成功");
            re = new Result(200,"权限批量删除成功",null );
        }else if(ObjectArry.length==0){
            System.out.println("无任何删除操作");
            re = new Result(400,"无任何删除操作",null );
        }else{
            System.out.println("权限批量删除失败");
            re = new Result(400,"权限批量删除失败",null );
        }
        System.out.println(Divider);
        return re;
    }


}
