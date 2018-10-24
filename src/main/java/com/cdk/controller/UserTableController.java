package com.cdk.controller;

import com.cdk.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class UserTableController {

    public static final String  Divider= "############################";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getUser")
    public Map<String,Object> getUser(@RequestBody Map map){
        String account = (map.get("account")!=null?map.get("account").toString():"");
        String name = (map.get("name")!=null?map.get("name").toString():"");
        String phone = (map.get("phone")!=null?map.get("phone").toString():"");
        String email = (map.get("email")!=null?map.get("email").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");

        System.out.println("account：" + account);
        System.out.println("name：" + name);
        System.out.println("phone：" + phone);
        System.out.println("email：" + email);
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

        String sql="select * ,(select GROUP_CONCAT(b.roleId,\"#cdk#\",c. role) as info from t_user_roles as b JOIN t_role as c on b.roleId = c.id\n" +
                "where a.id= b.userId and c.isDelete!=1 and b.isDelete!=1 )\n" +
                "as roles";
        sql += " from t_user as a where a.isDelete != 1  ";
        if(name!=""){
            sql+=" and a.name LIKE '%"+name+"%'";
        }
        if(account!=""){
            sql+=" and a.account LIKE '%"+account+"%'";
        }
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
        sql+=" limit "+(pageNo-1)*pageSize+", "+pageSize;
        System.out.println("sql：" + sql);
        list=jdbcTemplate.queryForList(sql);
        Map<String,Object> JsonMap =  new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);

        System.out.println("UserData：" + JsonMap);
        System.out.println(Divider);

        return JsonMap;
    }

    @RequestMapping(value="/editUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editUser(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String account = (map.get("account")!=null?map.get("account").toString():"");
        String name = (map.get("name")!=null?map.get("name").toString():"");
        String phone = (map.get("phone")!=null?map.get("phone").toString():"");
        String email = (map.get("email")!=null?map.get("email").toString():"");
        String type = (map.get("type")!=null?map.get("type").toString():"");
        System.out.println("id：" + id);
        System.out.println("account：" + account);
        System.out.println("name：" + name);
        System.out.println("phone：" + phone);
        System.out.println("email：" + email);
        System.out.println("type：" + type);
        Result  re;
        String sql = "UPDATE t_user SET account='"+account+"',name = '"+name+"',phone = '"+ phone +
                "',email = '"+email+"' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("用户信息修改成功");
            re = new Result(200,"用户信息更新成功",null );
        }else {
            System.out.println("用户信息修改失败");
            re = new Result(400,"用户信息更新失败",null );
        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/addUser")
    public Result addUser(@RequestBody Map map){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String id = (map.get("id") !=null?map.get("id").toString():"");
        String account = (map.get("account")!=null?map.get("account").toString():"");
        String name = (map.get("name")!=null?map.get("name").toString():"");

        String password = "123456";//默认初始密码

        String phone = (map.get("phone")!=null?map.get("phone").toString():"");
        String email = (map.get("email")!=null?map.get("email").toString():"");
        String type = (map.get("type")!=null?map.get("type").toString():"");

        System.out.println("id：" + id);
        System.out.println("account：" + account);
        System.out.println("password：" + password);
        System.out.println("name：" + name);
        System.out.println("phone：" + phone);
        System.out.println("email：" + email);
        System.out.println("type：" + type);
        System.out.println("addDatatime：" + addDatetime);

        Result  re;

        String sql="insert into t_user (name,account,password,nick,phone,email,state,addDatetime,lastDatetime,isDelete) "+
                " values ('"+name+"','"+account+"','"+password+"','"+name+"','"+phone+"','"+email+"','0','"+addDatetime+"','"+addDatetime+"','0')";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("用户添加成功");
            re = new Result(200,"用户添加成功",null );
        }else {
            System.out.println("用户添加失败");
            re = new Result(400,"用户添加失败",null );

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/editpassword", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result editpassword(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String password = (map.get("password")!=null?map.get("password").toString():"");
        System.out.println("id：" + id);
        System.out.println("password：" + password);
        Result  re;
        String sql = "UPDATE t_user SET password='"+password+"' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("密码修改成功");
            re = new Result(200,"用户密码修改成功",null );
        }else {
            System.out.println("密码修改失败");
            re = new Result(400,"用户密码修改失败",null );
        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/changeStateToFrozen_User", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToFrozen_User(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_user SET state='1' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("用户冻结成功");
            re = new Result(200,"用户冻结成功",null );
        }else {
            System.out.println("用户冻结失败");
            re = new Result(400,"用户冻结失败",null );

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/changeStateToNormal_User", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result changeStateToNormal_User(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_user SET state='0' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("用户解冻成功");
            re = new Result(200,"用户解冻成功",null );
        }else {
            System.out.println("用户解冻失败");
            re = new Result(400,"用户解冻失败",null );

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/deleteUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteUser(@RequestBody Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");

        System.out.println("id：" + id);

        Result  re;
        String sql = "UPDATE t_user SET isDelete='1' where id ='" + id +"'";
        System.out.println("sql：" + sql);
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql);
        if (temp > 0) {
            System.out.println("用户删除成功");
            re = new Result(200,"用户删除成功",null );
        }else {
            System.out.println("用户删除失败");
            re = new Result(400,"用户删除失败",null );

        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping(value="/deleteAllUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllUser(@RequestBody Map<String,String> map){
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
            sql[i]="UPDATE  t_user  set isDelete='1' where id = '"+ObjectArry[i]+"';";
            strSql += sql;
            //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
            //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
            //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        }
        System.out.println("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        Object args[] = new Object[]{id};
        if (temp.length !=0 ) {
            System.out.println("用户批量删除成功");
            re = new Result(200,"用户批量删除成功",null );
        }else if(ObjectArry.length==0){
            System.out.println("无任何删除操作");
            re = new Result(400,"无任何删除操作",null );
        }else{
            System.out.println("用户批量删除失败");
            re = new Result(400,"用户批量删除失败",null );
        }
        System.out.println(Divider);
        return re;
    }

    @RequestMapping("/insertUserRoles")
    public Result insertUserRoles(@RequestBody Map<String,String> map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        //String[] stringArray  = (map.get("InsertUserRoles") !=null?map.get("InsertUserRoles"):new String[]);
        String InsertUserRoles  = map.get("InsertUserRoles");

        if(Objects.equals(InsertUserRoles,"")){
            System.out.println("无任何添加操作");
            System.out.println(Divider);
            return new Result(200,"无任何添加操作",null );
        }

        System.out.println("id：" + id);
        System.out.println("InsertUserRoles：" + InsertUserRoles);
        System.out.println("isArray：" + InsertUserRoles.getClass().isArray());

        String[] ObjectArry =InsertUserRoles.split( ",");

//        //object 转 ObjectArry
//        if (InsertUserRoles.getClass().isArray()) {
//            int length = Array.getLength(InsertUserRoles);
//            Object[] ObjectArry = new Object[length];
//            for (int i = 0; i < ObjectArry.length; i++) {
//                ObjectArry[i] = Array.get(InsertUserRoles, i);
//            }
//        }

        System.out.println("ObjectArry：" + ObjectArry);
        System.out.println("ObjectArry.length：" + ObjectArry.length);

        Result  re;
        String sql = "";
        String strSql = "";
        int temp = 99;

        for(int i = 0 ;i < ObjectArry.length;i++){
            sql="insert into t_user_roles  (userId,roleId,isDelete) "+
                    " values ( "+id+" , " + ObjectArry[i] + " , '0')";
            strSql += sql;
            //只能运行一条语句，不可使用拼接
            temp = jdbcTemplate.update(sql);
        }

        System.out.println("sql：" + strSql);
        Object args[] = new Object[]{id};
        //jdbcTemplate.update(sql);
        if (temp > 0 && temp!=99) {
            System.out.println("用户角色添加成功");
            re = new Result(200,"用户角色添加成功",null );
        }else if(temp==99){
            System.out.println("无任何添加操作");
            re = new Result(200,"无任何添加操作",null );
        }else{
            System.out.println("用户角色添加失败");
            re = new Result(400,"用户角色添加失败",null );
        }
        //re = new Result(200,"角色添加成功",null );
        System.out.println(Divider);
        return re;
    }
}
