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
import java.util.logging.Logger;

@Repository
public class ApplyPropDaoImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(ApplyPropDaoImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getPlayerTypeList() {
        String sql = "select * from t_player_type";
        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }

    public Map<String, Object> getMoneyTypeList(int gameId) {
        String sql = "select * from t_prop_money_type where gameId=" + gameId;
        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }

    public Map<String, Object> getPropQualityList(int gameId) {
        String sql = "select * from t_prop_quality where gameId=" + gameId;
        logger.info("sql：" + sql);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        return JsonMap;
    }

    public Map<String, Object> getApplyProp(ApplyProp applyProp, String isPage, int pageNo, int pageSize, String strPlatform) {
        String sql =
                "select a.*,b.platform ,c.server,d.name as userName from t_prop_apply as a  join  t_gameplatform as b on a.platformId = b.platformId join t_gameserver as c on c.serverId = a.serverId join t_user as d on d.id = a.addUser where a.platformId in (" +
                        strPlatform + ") and a.isDelete != 1  and b.isDelete != 1 and c.isDelete != 1 ";
        if (!Objects.equals(applyProp.getPlatformId(), 0)) {
            sql += " and a.platformId ='" + applyProp.getPlatformId() + "' ";
        }
        if (!Objects.equals(applyProp.getServerId(), "") && !Objects.equals(applyProp.getServerId(), 0)) {
            sql += " and a.serverId = '" + applyProp.getServerId() + "' ";
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

    public int changeApplyState(int id, int state) {
        String addDatetime = df.format(new Date());

        String sql = "UPDATE  t_prop_apply  set applyState='" + state + "' , applyDatetime = '" + addDatetime + "' where id = '" + id + "' ";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int addApplyProp(ApplyProp applyProp) {
        String addDatetime = df.format(new Date());
        String sql =
                "insert into t_prop_apply (platformId,serverId,applyType,propList,releaseTitle,releaseContent,playerType,applyUser,applyReason,confirmState,addUser,addDatetime,isDelete,playerAccountList,playerNameList,playerIdList,moneyList) " +
                        " values ('" + applyProp.getPlatformId() + "','" + applyProp.getServerId() + "','" + applyProp.getApplyType() + "','" +
                        applyProp.getPropList() + "','" + applyProp.getReleaseTitle() + "','" + applyProp.getReleaseContent() + "','" +
                        applyProp.getPlayerType() + "','" + applyProp.getApplyUser() + "','" + applyProp.getApplyReason() + "','0','" +
                        applyProp.getAddUser() + "','" + addDatetime + "','0','" + applyProp.getPlayerAccountList() + "','" +
                        applyProp.getPlayerNameList() + "','" + applyProp.getPlayerIdList() + "','" + applyProp.getMoneyList() + "')";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int editApplyProp(ApplyProp applyProp) {
        String addDatetime = df.format(new Date());

        String sql =
                "UPDATE t_prop_apply set platformId = '" + applyProp.getPlatformId() + "' ,serverId = '" + applyProp.getServerId() + "',applyType='" +
                        applyProp.getApplyType() + "',propList='" + applyProp.getPropList() + "',releaseTitle='" + applyProp.getReleaseTitle() +
                        "',releaseContent='" + applyProp.getReleaseContent() + "',playerType='" + applyProp.getPlayerType() + "',applyUser='" +
                        applyProp.getApplyUser() + "',applyReason='" + applyProp.getApplyReason() + "',confirmState='0',addUser='" +
                        applyProp.getAddUser() + "',addDatetime='" + addDatetime + "',isDelete='0',playerAccountList='" +
                        applyProp.getPlayerAccountList() + "',playerNameList='" + applyProp.getPlayerNameList() + "',playerIdList='" +
                        applyProp.getPlayerIdList() + "',moneyList='" + applyProp.getMoneyList() + "' where id='" + applyProp.getId() + "';";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int confirmApplyProp(ApplyProp applyProp) {
        String addDatetime = df.format(new Date());

        String sql =
                "UPDATE  t_prop_apply  set confirmState='1' , confirmUserId='" + applyProp.getAddUser() + "' , confirmDatetime = '" + addDatetime +
                        "' where id = '" + applyProp.getId() + "' ";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int notConfirmApplyProp(ApplyProp applyProp) {
        String addDatetime = df.format(new Date());
        String sql =
                "UPDATE  t_prop_apply  set confirmState='2' , confirmUserId='" + applyProp.getAddUser() + "' , confirmDatetime = '" + addDatetime +
                        "' where id = '" + applyProp.getId() + "' ";
        logger.info("sql：" + sql);
        int temp = jdbcTemplate.update(sql);
        return temp;
    }

    public int deleteApplyProp(ApplyProp applyProp) {
        String sql = "UPDATE  t_prop_apply  set isDelete='1' " + " where id = '" + applyProp.getId() + "' ";
        logger.info("sql：" + sql);
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
        }
        logger.info("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }
}
