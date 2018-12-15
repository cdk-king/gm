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

    public int ImportProp(JSONArray jsonArray, int platformId, int gameId) {
        String sql[] = new String[jsonArray.length()];
        String strSql = "";
        int temp = 1;
        System.out.println("jsonArray.length:" + jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                int index = i;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String sqlSelect =
                        "select * from t_prop_upload where propId = '" + jsonObject.get("propId") + "' and  platformId = '" + platformId + "'";
                List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlSelect);
                if (list.size() > 0) {
                    //存在，更新
                    String sqlUpdate = "UPDATE t_prop_upload as a SET a.propName='" + jsonObject.get("propName") + "',a.propType = '" +
                            jsonObject.get("propType") + "'," + "a.propDescribe='" + jsonObject.get("prop_describe") + "' ";
                    jdbcTemplate.update(sqlUpdate);
                } else {
                    //没有，新增
                    String sqlInsert = "insert into t_prop_upload (propId,propName,propType,propDescribe,platformId,gameId) values ('" +
                            jsonObject.get("propId") + "', '" + jsonObject.get("propName") + "','" + jsonObject.get("propType") + "','" +
                            jsonObject.get("prop_describe") + "','" + platformId + "','" + gameId + "' ) ; ";
                    jdbcTemplate.update(sqlInsert);

                }

            } catch (JSONException e) {
                e.printStackTrace();
                return 0;
            }
        }

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
                "select a.*, b.platform ,c.propType as propTypeName from t_prop_upload as a join  t_gameplatform as b on a.platformId = b.platformId join t_prop_upload_type as c on a.propType = c.propTypeId and c.gameId = a.gameId where a.platformId IN (" +
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
            sql += " order by id limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        return JsonMap;
    }
}
