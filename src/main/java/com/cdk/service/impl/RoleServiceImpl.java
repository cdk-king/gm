package com.cdk.service.impl;

import com.cdk.dao.RoleDao;
import com.cdk.dao.impl.RoleDaoImpl;
import com.cdk.entity.Role;
import com.cdk.result.Result;
import com.cdk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {
    public static final String  Divider= "############################";
    public static final String  Split= "----------------";
    @Autowired
    public RoleDaoImpl roleDaoImpl;

    public Result addRole(Map map){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String id = (map.get("id") !=null?map.get("id").toString():"");
        String roleName = (map.get("role")!=null?map.get("role").toString():"");
        String role_describe = (map.get("role_describe")!=null?map.get("role_describe").toString():"");
        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");

        System.out.println("id：" + id);
        System.out.println("role：" + roleName);
        System.out.println("role_describe：" + role_describe);
        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);

        Role role = new Role();
        role.setRole(roleName);
        role.setRole_describe(role_describe);
        role.setAddUser(addUser);

        Result  re;
        int temp = roleDaoImpl.addRole(role);
        if (temp > 0) {
            System.out.println("角色添加成功");
            re = new Result(200,"角色添加成功",null );
        }else {
            System.out.println("角色添加失败");
            re = new Result(400,"角色添加失败",null );
        }
        return re;
    }

    public Result getRole(Map map){
        String roleName = (map.get("role")!=null?map.get("role").toString():"");
        String role_describe = (map.get("role_describe")!=null?map.get("role_describe").toString():"");
        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");
        String isPage = (map.get("isPage")!=null?map.get("isPage").toString():"");
        if(state==""){
            state = "0";
        }
        System.out.println("role：" + roleName);
        System.out.println("role_describe：" + role_describe);
        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        String StrPageNo = (map.get("pageNo") !=null?map.get("pageNo").toString():"1");
        String StrPageSize = (map.get("pageSize") !=null?map.get("pageSize").toString():"5");

        System.out.println("isPage：" + isPage);
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

        Role role = new Role();
        role.setRole(roleName);
        role.setRole_describe(role_describe);
        role.setAddUser(addUser);

        Result  re;
        Map<String,Object> JsonMap=roleDaoImpl.getRole(role,isPage,pageNo,pageSize);
        if(Objects.equals(JsonMap.get("list"),0)){
            re= new Result(400,"角色列表获取失败","");
        }else{
            re = new Result(200,"角色列表获取成功",JsonMap);
        }
        return re;
    }

    public Result editRole(Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String roleName = (map.get("role")!=null?map.get("role").toString():"");
        String role_describe = (map.get("role_describe")!=null?map.get("role_describe").toString():"");
        String addUser = (map.get("addUser")!=null?map.get("addUser").toString():"");
        String addDatetime = (map.get("addDatetime")!=null?map.get("addDatetime").toString():"");
        String state = (map.get("state")!=null?map.get("state").toString():"");

        System.out.println("id：" + id);
        System.out.println("role：" + roleName);
        System.out.println("role_describe：" + role_describe);
        System.out.println("addUser：" + addUser);
        System.out.println("addDatetime：" + addDatetime);
        System.out.println("state：" + state);

        Result  re;

        Role role = new Role();
        role.setId(Integer.parseInt(id));
        role.setRole(roleName);
        role.setRole_describe(role_describe);
        role.setAddUser(addUser);

        int temp = roleDaoImpl.editRole(role);
        if (temp > 0) {
            System.out.println("角色信息修改成功");
            re = new Result(200,"角色信息更新成功",null );
        }else {
            System.out.println("角色信息修改失败");
            re = new Result(400,"角色信息更新失败",null );
        }

        return re;
    }

    public Result changeStateToFrozen_Role(Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        System.out.println("id：" + id);
        Role user = new Role();
        user.setId(Integer.parseInt(id));

        Result re;
        int temp=roleDaoImpl.changeStateToFrozen_Role(user);
        if (temp > 0) {
            System.out.println("角色冻结成功");
            re = new Result(200,"角色冻结成功",null );
        }else {
            System.out.println("角色冻结失败");
            re = new Result(400,"角色冻结失败",null );
        }
        return re;
    }

    public Result changeStateToNormal_Role(Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        System.out.println("id：" + id);
        Role role = new Role();
        role.setId(Integer.parseInt(id));

        Result re;
        int temp=roleDaoImpl.changeStateToNormal_Role(role);
        if (temp > 0) {
            System.out.println("角色解冻成功");
            re = new Result(200,"角色解冻成功",null );
        }else {
            System.out.println("角色解冻失败");
            re = new Result(400,"角色解冻失败",null );
        }
        return re;
    }

    public Result deleteRole(Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        System.out.println("id：" + id);
        Role role = new Role();
        role.setId(Integer.parseInt(id));

        Result re;
        int temp=roleDaoImpl.deleteRole(role);
        if (temp > 0) {
            System.out.println("角色删除成功");
            re = new Result(200,"角色删除成功",null );
        }else {
            System.out.println("角色删除失败");
            re = new Result(400,"角色删除失败",null );

        }
        return re;
    }

    public Result deleteRoleRights(Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String deleteRoleRights  = map.get("deleteRoleRights").toString();

        System.out.println("id：" + id);
        System.out.println("deleteRoleRights：" + deleteRoleRights);

        if(Objects.equals(deleteRoleRights,"")){
            System.out.println("无任何添加操作");
            System.out.println(Divider);
            return new Result(200,"无任何添加操作",null );
        }

        String[] ObjectArry =deleteRoleRights.split( ",");

        Role role = new Role();
        role.setId(Integer.parseInt(id));

        Result re;
        int temp = 99;
        temp=roleDaoImpl.deleteRoleRights(Integer.parseInt(id),ObjectArry);

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
        return re;
    }

    public Result InsertRoleRights(Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        String InsertRoleRights  = map.get("InsertRoleRights").toString();

        System.out.println("id：" + id);
        System.out.println("InsertRoleRights：" + InsertRoleRights);

        if(Objects.equals(InsertRoleRights,"")){
            System.out.println("无任何添加操作");
            return new Result(200,"无任何添加操作",null );
        }

        String[] ObjectArry =InsertRoleRights.split( ",");

        Role role = new Role();
        role.setId(Integer.parseInt(id));

        Result re;
        int temp = 99;
        temp=roleDaoImpl.InsertRoleRights(Integer.parseInt(id),ObjectArry);

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
        return re;
    }

    public Result deleteAllRole(Map map){
        String id = (map.get("id") !=null?map.get("id").toString():"");
        System.out.println("id：" + id);
        if(Objects.equals(id,"")){
            System.out.println("无任何批量删除操作");
            return new Result(200,"无任何批量删除操作",null );
        }

        String[] ObjectArry =id.split( ",");

        String sql[] = new String[ObjectArry.length] ;
        String strSql = "";
        Result re;
        int[] temp = new int[ObjectArry.length];

        temp=roleDaoImpl.deleteAllRole(Integer.parseInt(id),ObjectArry);
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
        return re;
    }
}
