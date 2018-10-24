package com.cdk.controller;

import com.cdk.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class RoleTableController {

    public static final String  Divider= "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getRole")
    public Map<String,Object> getRole(@RequestBody Map map){

        //String id = (map.get("id") !=null?map.get("id").toString():"");

        String role = (map.get("role")!=null?map.get("role").toString():"");
        String role_describe = (map.get("role_describe")!=null?map.get("role_describe").toString():"");
        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");
        String isPage = (map.get("isPage")!=null?map.get("isPage").toString():"");

        System.out.println("role：" + role);
        System.out.println("role_describe：" + role_describe);
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

        String sql="select * ,(select GROUP_CONCAT(b.rightId,\"#cdk#\",c. rightName) as info from t_role_rights as b JOIN t_right as c on b.rightId = c.id \n" +
                "where a.id= b.roleId and c.isDelete!=1 and b.isDelete!=1 )\n" +
                "as rights";
        sql +=  " from t_role as a where a.isDelete != 1  ";
        if(role!=""){
            sql+=" and a.role LIKE '%"+role+"%'";
        }
        if(role_describe!=""){
            sql+=" and a.role_describe LIKE '%"+role_describe+"%'";
        }
        if(!Objects.equals(state,"") && !Objects.equals(state,"0")){
            System.out.println(Objects.equals(state,"1"));
            if(Objects.equals(state,"1")){
                System.out.println("1");
                sql+=" and a.state = 1 ";
            }else if(Objects.equals(state,"2")){
                sql+=" and a.state != 1 ";
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

        System.out.println("RoleData：" + JsonMap);
        System.out.println(Divider);

        return JsonMap;
    }

    @RequestMapping(value="/editRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editRole(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String role = (map.get("role")!=null?map.get("role").toString():"");
        String role_describe = (map.get("role_describe")!=null?map.get("role_describe").toString():"");
        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");

        System.out.println("id：" + id);
        System.out.println("role：" + role);
        System.out.println("role_describe：" + role_describe);
        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result  re;
        //数据库表名如果和列名重复，需要用别名来区分
        String sql = "UPDATE t_role as a SET a.role='"+role+"',a.role_describe = '"+role_describe+"',a.addUser = '"+ addUser +
                "' where a.id =" + id +"";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("角色信息修改成功");
            re = new Result(200,"角色信息更新成功",null );
        }else {
            System.out.println("角色信息修改失败");
            re = new Result(400,"角色信息更新失败",null );

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/addRole")
    public Result addRole(@RequestBody Map map){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String id = (map.get("id") !=null?map.get("id").toString():"");
        String role = (map.get("role")!=null?map.get("role").toString():"");
        String role_describe = (map.get("role_describe")!=null?map.get("role_describe").toString():"");
        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        //String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");

        System.out.println("id：" + id);
        System.out.println("role：" + role);
        System.out.println("role_describe：" + role_describe);
        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result  re;

        //describe是MySQL的关键字
        String sql="insert into t_role  (role,role_describe,addUser,addDatetime,state,isDelete) "+
                " values ('"+role+"','"+role_describe+"','"+addUser+"','"+addDatetime+"','0','0')";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("角色添加成功");
            re = new Result(200,"角色添加成功",null );
        }else {
            System.out.println("角色添加失败");
            re = new Result(400,"角色添加失败",null );
        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/changeStateToFrozen_Role", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_Role(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_role SET state='1' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("角色冻结成功");
            re = new Result(200,"角色冻结成功",null );
        }else {
            System.out.println("角色冻结失败");
            re = new Result(400,"角色冻结失败",null );

        }
        System.out.println(Divider);
        return re;
    }


    @RequestMapping(value="/changeStateToNormal_Role", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_Role(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_role SET state='0' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("角色解冻成功");
            re = new Result(200,"角色解冻成功",null );
        }else {
            System.out.println("角色解冻失败");
            re = new Result(400,"角色解冻失败",null );

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/deleteRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteRole(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_role SET isDelete='1' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("角色删除成功");
            re = new Result(200,"角色删除成功",null );
        }else {
            System.out.println("角色删除失败");
            re = new Result(400,"角色删除失败",null );

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/deleteRoleRights", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteRoleRights(@RequestBody Map<String,String> map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String deleteRoleRights  = map.get("deleteRoleRights");

        if(Objects.equals(deleteRoleRights,"")){
            System.out.println("无任何删除操作");
            System.out.println(Divider);
            return new Result(400,"无任何删除操作",null );
        }
        System.out.println("id：" + id);
        System.out.println("deleteRoleRights：" + deleteRoleRights);

        String[] ObjectArry =deleteRoleRights.split( ",");
        System.out.println("ObjectArry：" + ObjectArry);

        Result  re;
        String sql = "";
        String strSql = "";
        int temp = 99;

        for(int i = 0 ;i < ObjectArry.length;i++){
            //UPDATE user_roles  set  isDelete='1' where
            sql="delete from  t_role_rights  where roleId ='" + id +"' and rightId = '"+ObjectArry[i]+"'";
            strSql += sql;
            //只能运行一条语句，不可使用拼接
            temp = jdbcTemplate.update(sql);
        }
        System.out.println("sql：" + strSql);
        Object args[] = new Object[]{id};
        if (temp > 0 && temp!=99) {
            System.out.println("角色权限删除成功");
            re = new Result(200,"角色权限删除成功",null );
        }else if(temp==99){
            System.out.println("无任何删除操作");
            re = new Result(400,"无任何删除操作",null );
        }else{
            System.out.println("角色权限删除失败");
            re = new Result(400,"角色权限删除失败",null );
        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/deleteAllRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllRole(@RequestBody Map<String,String> map){
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
            sql[i]="UPDATE  t_role  set isDelete='1' where id = '"+ObjectArry[i]+"';";
            strSql += sql;
            //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
            //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
            //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        }
        System.out.println("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        Object args[] = new Object[]{id};
        if (temp.length !=0 ) {
            System.out.println("角色批量删除成功");
            re = new Result(200,"角色批量删除成功",null );
        }else if(ObjectArry.length==0){
            System.out.println("无任何删除操作");
            re = new Result(400,"无任何删除操作",null );
        }else{
            System.out.println("角色批量删除失败");
            re = new Result(400,"角色批量删除失败",null );
        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/InsertRoleRights")
    public Result InsertRoleRights(@RequestBody Map<String,String> map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        //String[] stringArray  = (map.get("InsertUserRoles") !=null?map.get("InsertUserRoles"):new String[]);
        String InsertRoleRights  = map.get("InsertRoleRights");

        if(Objects.equals(InsertRoleRights,"")){
            System.out.println("无任何添加操作");
            System.out.println(Divider);
            return new Result(200,"无任何添加操作",null );
        }

        System.out.println("id：" + id);
        System.out.println("InsertRoleRights：" + InsertRoleRights);
        System.out.println("isArray：" + InsertRoleRights.getClass().isArray());

        String[] ObjectArry =InsertRoleRights.split( ",");

        System.out.println("ObjectArry：" + ObjectArry);
        System.out.println("ObjectArry.length：" + ObjectArry.length);

        Result  re;
        String sql = "";
        String strSql = "";
        int temp = 99;

        for(int i = 0 ;i < ObjectArry.length;i++){
            sql="insert into t_role_rights  (roleId,rightId,isDelete) "+
                    " values ( "+id+" , " + ObjectArry[i] + " , '0')";
            strSql += sql;
            //只能运行一条语句，不可使用拼接
            temp = jdbcTemplate.update(sql);
        }

        System.out.println("sql：" + strSql);
        Object args[] = new Object[]{id};
        //jdbcTemplate.update(sql);
        if (temp > 0 && temp!=99) {
            System.out.println("角色权限添加成功");
            re = new Result(200,"角色权限添加成功",null );
        }else if(temp==99){
            System.out.println("无任何添加操作");
            re = new Result(200,"无任何添加操作",null );
        }else{
            System.out.println("角色权限添加失败");
            re = new Result(400,"角色权限添加失败",null );
        }
        //re = new Result(200,"角色添加成功",null );
        System.out.println(Divider);
        return re;
    }

}
