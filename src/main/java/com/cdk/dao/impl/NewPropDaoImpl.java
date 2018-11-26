package com.cdk.dao.impl;

import com.cdk.dao.NewPropDao;
import com.cdk.entity.NewProp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class NewPropDaoImpl implements NewPropDao {
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int[] ImportProp(JSONArray jsonArray, int platformId, int gameId) {
        String sql[] = new String[jsonArray.length()];
        String strSql = "";
        //清空数据库表
        strSql = "truncate table t_prop_upload";
        jdbcTemplate.update(strSql);
        int[] temp = new int[jsonArray.length()];
        for (int i = 1; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                sql[i] = "insert into t_prop_upload (id,propName,propType,propDescribe,platformId,gameId) values ('" + jsonObject.get("propId") +
                        "', '" + jsonObject.get("propName") + "','" + jsonObject.get("propType") + "','" + jsonObject.get("prop_describe") + "','" +
                        platformId + "','" + gameId + "' ) ; ";
                strSql += sql;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
            //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
            //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        }
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }

    public Map<String, Object> getPropTypeList(int gameId) {
        //如果查询结果有个重复的字段，默认取后边
        String sql = "select * from t_prop_upload_type where gameId = " + gameId;
        System.out.println("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }

    public Map<String, Object> getPropUplaod(NewProp newProp, String isPage, int pageNo, int pageSize, String strPlatform) {
        //如果查询结果有个重复的字段，默认取后边
        String sql =
                "select a.*, b.platform ,c.propType as propTypeName from t_prop_upload as a join  t_gameplatform as b on a.platformId = b.id join t_prop_upload_type as c on a.propType = c.propTypeId and c.gameId = a.gameId where a.platformId IN (" +
                        strPlatform + ")  and b.isDelete != 1  ";
        if (newProp.getPlatformId() != 0) {
            sql += " and a.platformId ='" + newProp.getPlatformId() + "' ";
        }
        if (newProp.getPropName() != "") {
            sql += " and a.propName LIKE '%" + newProp.getPropName() + "%'";
        }

        if (newProp.getPropType() != "" && !Objects.equals(newProp.getPropType(), "0")) {
            sql += " and a.propType = '" + newProp.getPropType() + "'";
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
}
