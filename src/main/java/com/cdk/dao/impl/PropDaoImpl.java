package com.cdk.dao.impl;

import com.cdk.dao.PropDao;
import com.cdk.entity.Prop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Repository
public class PropDaoImpl implements PropDao {
    private static Logger logger = Logger.getLogger(String.valueOf(PropDaoImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> getProp(Prop prop, String isPage, int pageNo, int pageSize, String strPlatform) {
        //如果查询结果有个重复的字段，默认取后边
        String sql = "select a.* , b.platform  from t_prop as a join  t_gameplatform as b on a.platformId = b.platformId where a.platformId IN (" +
                strPlatform + ") and a.isDelete != 1  and b.isDelete != 1  ";
        if (prop.getPlatformId() != 0) {
            sql += " and a.platformId ='" + prop.getPlatformId() + "' ";
        }
        if (prop.getPropName() != "") {
            sql += " and a.propName LIKE '%" + prop.getPropName() + "%'";
        }

        if (prop.getPropTag() != "") {
            sql += " and a.propTag LIKE '%" + prop.getPropTag() + "%'";
        }

        //0：全部，1：冻结，2：未冻结
        if (!Objects.equals(prop.getState(), null) && !Objects.equals(prop.getState(), 0)) {
            if (Objects.equals(prop.getState(), 1)) {
                sql += " and a.state = 1 ";
            } else if (Objects.equals(prop.getState(), 2)) {
                sql += " and a.state != 1 ";
            }
        }
        logger.info("sql：" + sql);
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

    @Override
    public int addProp(Prop prop) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String sql =
                "insert into t_prop (propName,propTag,prop_describe,platformId,propType,addUser,addDatetime,sort,state,isDelete) " + " values ('" +
                        prop.getPropName() + "','" + prop.getPropTag() + "','" + prop.getProp_describe() + "','" + prop.getPlatformId() + "','" +
                        prop.getPropType() + "','" + prop.getAddUser() + "','" + addDatetime + "','0','0','0')";
        int temp = jdbcTemplate.update(sql);
        return temp;
    }


    @Override
    public int editProp(Prop prop) {
        String sql = "UPDATE t_prop as a SET a.propName='" + prop.getPropName() + "',a.prop_describe = '" + prop.getProp_describe() + "'," +
                "a.propTag='" + prop.getPropTag() + "' ,a.addUser = '" + prop.getAddUser() + "' where a.id =" + prop.getId() + "";
        logger.info(sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int deleteProp(Prop prop) {
        String sql = "UPDATE t_prop  SET isDelete = 1 where id =" + prop.getId() + "";
        logger.info(sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToNormal_Game(Prop prop) {
        String sql = "UPDATE t_prop  SET state = 0 where id =" + prop.getId() + "";
        logger.info(sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToFrozen_Game(Prop prop) {
        String sql = "UPDATE t_prop  SET state = 1 where id =" + prop.getId() + "";
        logger.info(sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int[] deleteAllProp(String[] propList) {
        String sql[] = new String[propList.length];
        String strSql = "";
        int[] temp = new int[propList.length];
        for (int i = 0; i < propList.length; i++) {
            sql[i] = "UPDATE  t_prop  set isDelete='1' where id = '" + propList[i] + "';";
            strSql += sql;
        }
        logger.info("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
