package com.cdk.dao.impl;

import com.cdk.entity.ApplyProp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class ApplyPropDaoImpl {

    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getPlayerTypeList() {
        String sql = "select * from t_player_type";
        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }

    public Map<String, Object> getApplyProp(ApplyProp applyProp, String isPage, int pageNo, int pageSize) {
        String sql =
                "select a.*,b.platform ,c.server,d.name as userName from t_prop_apply as a  join  t_gameplatform as b on a.platformId = b.id join t_gameserver as c on c.id = a.serverId join t_user as d on d.id = a.addUser where a.isDelete != 1  and b.isDelete != 1 and c.isDelete != 1 ";
        if (!Objects.equals(applyProp.getPlatformId(), 0)) {
            sql += " and a.platformId ='" + applyProp.getPlatformId() + "' ";
        }
        if (!Objects.equals(applyProp.getServerId(), "") && !Objects.equals(applyProp.getServerId(), 0)) {
            sql += " and a.serverId = '" + applyProp.getServerId() + "' ";
        }
        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }

    public int addApplyProp(ApplyProp applyProp) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String sql =
                "insert into t_prop_apply (platformId,serverId,applyType,propList,releaseTitle,releaseContent,playerType,applyUser,applyReason,sendState,addUser,addDatetime,isDelete,playerAccountList,playerNameList,playerIdList) " +
                        " values ('" + applyProp.getPlatformId() + "','" + applyProp.getServerId() + "','" + applyProp.getApplyType() + "','" +
                        applyProp.getPropList() + "','" + applyProp.getReleaseTitle() + "','" + applyProp.getReleaseContent() + "','" +
                        applyProp.getPlayerType() + "','" + applyProp.getApplyUser() + "','" + applyProp.getApplyReason() + "','0','" +
                        applyProp.getAddUser() + "','" + addDatetime + "','0','" + applyProp.getPlayerAccountList() + "','" +
                        applyProp.getPlayerNameList() + "','" + applyProp.getPlayerIdList() + "')";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int confirmApplyProp(ApplyProp applyProp) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String sql = "UPDATE  t_prop_apply  set sendState='1' , confirmUserId='" + applyProp.getAddUser() + "' , confirmDatetime = '" + addDatetime +
                "' where id = '" + applyProp.getId() + "' ";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int notConfirmApplyProp(ApplyProp applyProp) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String sql = "UPDATE  t_prop_apply  set sendState='2' , confirmUserId='" + applyProp.getAddUser() + "' , confirmDatetime = '" + addDatetime +
                "' where id = '" + applyProp.getId() + "' ";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int deleteApplyProp(ApplyProp applyProp) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String sql = "UPDATE  t_prop_apply  set isDelete='1' " + " where id = '" + applyProp.getId() + "' ";
        System.out.println("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int[] deleteAllApplyProp(String[] idList) {

        String strSql = "";
        String sql[] = new String[idList.length];
        int[] temp = new int[idList.length];

        for (int i = 0; i < idList.length; i++) {
            sql[i] = "UPDATE  t_prop_apply  set isDelete='1' where id = '" + idList[i] + "';";
            strSql += sql;
            //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
            //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
            //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        }
        System.out.println("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
