package com.cdk.dao.impl;

import com.cdk.dao.RightDao;
import com.cdk.entity.Right;

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
public class RightDaoImpl implements RightDao {
    private static Logger logger = Logger.getLogger(String.valueOf(RightDaoImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addRight(Right right) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳

        String sql =
                "insert into t_right (rightName,rightTag,right_describe,rightSort,rightParentId,addUser,addDatetime,state,isDelete) " + " values ('" +
                        right.getRightName() + "','" + right.getRightTag() + "','" + right.getRight_describe() + "','0','0','" + right.getAddUser() +
                        "','" + addDatetime + "','0','0')";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public Map<String, Object> getRight(Right right, String isPage, int pageNo, int pageSize) {

        String sql = "select * from t_right where isDelete != 1  ";
        if (right.getRightName() != "") {
            sql += " and rightName LIKE '%" + right.getRightName() + "%'";
        }
        if (right.getRight_describe() != "") {
            sql += " and right_describe LIKE '%" + right.getRight_describe() + "%'";
        }
        if (right.getRightTag() != "") {
            sql += " and rightTag LIKE '%" + right.getRightTag() + "%'";
        }
        //0：全部，1：冻结，2：未冻结
        if (!Objects.equals(right.getState(), null) && !Objects.equals(right.getState(), 0)) {
            if (Objects.equals(right.getState(), 1)) {
                sql += " and state = 1 ";
            } else if (Objects.equals(right.getState(), 2)) {
                sql += " and state != 1 ";
            }
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        int total = list.size();
        if (!Objects.equals(isPage, "")) {
            sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        }

        logger.info("sql：" + sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);

        return JsonMap;
    }

    public int editRight(Right right) {
        String sql = "UPDATE t_right as a SET a.rightName='" + right.getRightName() + "',a.right_describe = '" + right.getRight_describe() + "',"
                //+"a.rightParentId=0,a.rightSort=0,"
                + "a.rightTag='" + right.getRightTag() + "' ,a.addUser = '" + right.getAddUser() + "',a.rightSort = " + right.getRightSort() +
                " where a.id =" + right.getId() + "";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToFrozen_Right(Right right) {

        String sql = "UPDATE t_right SET state='1' where id ='" + right.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int changeStateToNormal_Right(Right right) {
        String sql = "UPDATE t_right SET state='0' where id ='" + right.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int deleteRight(Right right) {
        String sql = "UPDATE t_right SET isDelete='1' where id ='" + right.getId() + "'";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    @Override
    public int[] deleteAllRight(String[] rightList) {
        String sql[] = new String[rightList.length];
        String strSql = "";
        int[] temp = new int[rightList.length];

        for (int i = 0; i < rightList.length; i++) {
            //UPDATE user_roles  set  isDelete='1' where
            sql[i] = "UPDATE  t_right  set isDelete='1' where id = '" + rightList[i] + "';";
            strSql += sql;
            //jdbcTemplate.update(sql)只能运行一条语句，不可使用拼接
            //jdbcTemplate.batchUpdate可执行多条语句，同时还能规避执行过程中中断
            //这期间任一条SQL语句出现问题都会回滚[**]会所有语句没有执行前的最初状态
        }
        logger.info("sql：" + sql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
