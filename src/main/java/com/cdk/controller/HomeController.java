package com.cdk.controller;

import com.cdk.entity.VueLoginInfoVo;
import com.cdk.result.Result;
import com.cdk.result.ResultCode;
import com.cdk.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cdk.entity.Demo;
import com.cdk.service.DemoService;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.cdk.util.MD5Util.*;

@RestController
public class HomeController {
    @RequestMapping("/cdk")
    public String cdk(){
        String s = new String("cdk");
        System.out.println(md5Decode("a6aeb3ffa55fc7d664406af9c3bd0f1b"));
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + string2MD5(s));
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));
        String re = "原始：" + s + "<br/>" + "MD5后：" + string2MD5(s) + "<br/>" + "加密的：" + convertMD5(s) + "<br/>" + "解密的：" + convertMD5(convertMD5(s));
        return re;
    }

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

    //org.springframework.ui.Model
    //事实上model数据，最终spring也是写到HttpServletRequest属性中，只是用model更符合mvc设计,减少各层间耦合。
    @RequestMapping("/index")
    public ModelAndView index(Model model){
        model.addAttribute("name", "CDK");
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping("/")
    public ModelAndView request(HttpServletRequest request){
        request.setAttribute("name", "hello world");
        ModelAndView mv = new ModelAndView("index");
        return mv;
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
        System.out.println("############################");
        return re;
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
        System.out.println("############################");
        return re;
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
        System.out.println("############################");
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
        System.out.println("############################");
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
        System.out.println("############################");
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
        System.out.println("############################");
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
        System.out.println("############################");
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
        System.out.println("############################");
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
        System.out.println("############################");
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
        System.out.println("############################");
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
        System.out.println("############################");
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
        System.out.println("############################");
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
        System.out.println("############################");
        return re;
    }

    @RequestMapping(value="/deleteUserRoles", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteUserRoles(@RequestBody Map<String,String> map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String deleteUserRoles  = map.get("deleteUserRoles");

        if(Objects.equals(deleteUserRoles,"")){
            System.out.println("无任何删除操作");
            System.out.println("############################");
            return new Result(400,"无任何删除操作",null );
        }
        System.out.println("id：" + id);
        System.out.println("deleteUserRoles：" + deleteUserRoles);

        String[] ObjectArry =deleteUserRoles.split( ",");
        System.out.println("ObjectArry：" + ObjectArry);

        Result  re;
        String sql = "";
        String strSql = "";
        int temp = 99;

        for(int i = 0 ;i < ObjectArry.length;i++){
            //UPDATE user_roles  set  isDelete='1' where
            sql="delete from  t_user_roles  where userId ='" + id +"' and roleId = '"+ObjectArry[i]+"'";
            strSql += sql;
            //只能运行一条语句，不可使用拼接
            temp = jdbcTemplate.update(sql);
        }
        System.out.println("sql：" + strSql);
        Object args[] = new Object[]{id};
        if (temp > 0 && temp!=99) {
            System.out.println("用户角色删除成功");
            re = new Result(200,"用户角色删除成功",null );
        }else if(temp==99){
            System.out.println("无任何删除操作");
            re = new Result(400,"无任何删除操作",null );
        }else{
            System.out.println("用户角色删除失败");
            re = new Result(400,"用户角色删除失败",null );
        }
        System.out.println("############################");
        return re;
    }

    @RequestMapping(value="/deleteRoleRights", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteRoleRights(@RequestBody Map<String,String> map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String deleteRoleRights  = map.get("deleteRoleRights");

        if(Objects.equals(deleteRoleRights,"")){
            System.out.println("无任何删除操作");
            System.out.println("############################");
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
        System.out.println("############################");
        return re;
    }

    @RequestMapping(value="/deleteAllUser", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllUser(@RequestBody Map<String,String> map){
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
        System.out.println("############################");
        return re;
    }

    @RequestMapping(value="/deleteAllRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllRole(@RequestBody Map<String,String> map){
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
        System.out.println("############################");
        return re;
    }

    @RequestMapping(value="/deleteAllRight", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result deleteAllRight(@RequestBody Map<String,String> map){
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
        System.out.println("############################");
        return re;
    }

    @RequestMapping("/addUser")
    public Result addUser(@RequestBody Map map){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String id = (map.get("id") !=null?map.get("id").toString():"");
        String account = (map.get("account")!=null?map.get("account").toString():"");
        String name = (map.get("name")!=null?map.get("name").toString():"");

        String password = "123456";

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
        System.out.println("############################");
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
        System.out.println("############################");
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
        System.out.println("############################");
        return re;
    }


    @RequestMapping("/insertUserRoles")
    public Result insertUserRoles(@RequestBody Map<String,String> map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        //String[] stringArray  = (map.get("InsertUserRoles") !=null?map.get("InsertUserRoles"):new String[]);
        String InsertUserRoles  = map.get("InsertUserRoles");

        if(Objects.equals(InsertUserRoles,"")){
            System.out.println("无任何添加操作");
            System.out.println("############################");
            return new Result(200,"无任何添加操作",null );
        }

        System.out.println("id：" + id);
        System.out.println("InsertUserRoles：" + InsertUserRoles);
        System.out.println("isArray：" + InsertUserRoles.getClass().isArray());

        String[] ObjectArry =InsertUserRoles.split( ",");

//object 转 ObjectArry
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
        System.out.println("############################");
        return re;
    }

    @RequestMapping("/InsertRoleRights")
    public Result InsertRoleRights(@RequestBody Map<String,String> map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        //String[] stringArray  = (map.get("InsertUserRoles") !=null?map.get("InsertUserRoles"):new String[]);
        String InsertRoleRights  = map.get("InsertRoleRights");

        if(Objects.equals(InsertRoleRights,"")){
            System.out.println("无任何添加操作");
            System.out.println("############################");
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
        System.out.println("############################");
        return re;
    }

    //HttpServletRequest request
    @RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@RequestBody VueLoginInfoVo loginInfoVo){

        //String username = request.getParameter("username");
        String username = loginInfoVo.getUsername();
        String password = loginInfoVo.getPassword();

        //String sql="select psw from user where name = '" +  username+"'";
        //String str=jdbcTemplate.queryForObject(sql,String.class);

        ///如果queryFoMap的的执行没有结果，则直接抛异常进入catch模块;queryForList则没这问题，
        //queryFoMap的设计初衷应该就是为“执行结果有且只有一条数据”的查询情况所设计的，所以，查询不到数据的时候就抛出异常，
        //如果没有进行很合理的异常处理，则结果会明显不符合预期，为了避免这情况，干脆永远放弃queryFoMap，需要查询结果的时候就只用queryForList。
        //String sql="select * from user where name = '" +  username + "' order by id desc limit 1";
        //Map<String,Object> map=jdbcTemplate.queryForMap(sql);

        String sql="select * from t_user where name = '" +  username + "' order by id desc limit 1";
        System.out.println("sql：" + sql);
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);

        if(list != null && list.size() >= 1){
            //System.out.println("list中第二个元素 "+list.get(1));

            if(!Objects.equals(list.get(0).get("password"),password)){
                Result  re = new Result(400,"登录失败，密码错误",list.get(0).get("password")+"|" + password );
                System.out.println("登录失败，密码错误");
                return re;
            }
        }else{
            Result  re = new Result(400,"登录失败，用户名无效", password );
            System.out.println("登录失败，用户名无效");
            return re;
        }

        //if(Objects.equals(str,null)){
        //    Result  re = new Result(400,"登录失败，用户名无效", password );
        //    return re;
        //}

        //因为string属于符合数据类型，所以应该是使用equals，假如我们使用==比较，肯定是比较它们的内存地址了
        //if(!Objects.equals(map.get("psw"),password)){
        //    Result  re = new Result(400,"登录失败，密码错误",str.get("psw")+"|" + password );
        //    return re;
        //}

        Result  re = new Result(200,"登录成功",list.get(0) );
        System.out.println("用户登录成功");
        //return ResultFactory.buildSuccessResult("登录成功");
        //return re;
        return re;
    }

    @RequestMapping("/one")
    public ModelAndView one(Model model){
        model.addAttribute("username", "CDK");
        ModelAndView mv = new ModelAndView("one");
        return mv;
    }



    @Autowired
    private JdbcTemplate jdbcTemplate;

    //http://localhost:8011/getUser
    @RequestMapping("/getUser")
    public Map<String,Object> getUser(@RequestBody Map map){

        //String id = (map.get("id") !=null?map.get("id").toString():"");

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
        //String sql="select * ";
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
        System.out.println("############################");

        return JsonMap;
    }

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
        System.out.println("############################");

        return JsonMap;
    }


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
        System.out.println("############################");

        return JsonMap;
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
        System.out.println("############################");

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
        System.out.println("############################");

        return re;
    }

    /*
    *   待完成
    */
    @RequestMapping("/getGameListForUser")
    public Result getGameListForUser(@RequestBody Map map){

        String id = (map.get("id") !=null?map.get("id").toString():"");

        //获取

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
        System.out.println("############################");

        return re;
    }

    @Resource
    private DemoService demoService;

    /**

     * 测试保存数据方法.

     * @return

     */

    @RequestMapping("/save")
    public String save(){
        Demo d = new Demo();
        d.setName("Angel");
        demoService.save(d);//保存数据.
        return "ok.DemoController.save";
    }

    @RequestMapping("/getCount")
    public int getCount(){
        String sql="select count(*) from t_user";
        int str=jdbcTemplate.queryForObject(sql,int.class);
        return str;
    }

    @RequestMapping("/getString")
    public String getString(){
        String sql="select * from t_user";
        String str=jdbcTemplate.queryForObject(sql,String.class);
        return str;
    }


    @RequestMapping("/delLastUser")
    public String delLastUser(){
        String sql="delete from t_user where id in\n" +
                "(select n.id from (select id from t_user order by id desc limit 1) as n)";
        jdbcTemplate.execute(sql);
        return "delLastUser";
    }
}
