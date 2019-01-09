package com.cdk.service.impl;


import com.cdk.dao.impl.PlayerLogDaoImpl;
import com.cdk.dao.impl.UtilsDaoImpl;
import com.cdk.entity.Player;
import com.cdk.result.Result;
import com.cdk.util.DomAnalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PlayerLogServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(PlayerLogServiceImpl.class);

    @Autowired
    public PlayerLogDaoImpl playerLogDaoImpl;
    @Autowired
    private UtilsDaoImpl utilsDaoImpl;

    public Result getPlayerProhibitSpeakLog(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String playerName = (map.get("playerName") != null ? map.get("playerName").toString() : "");
        String playerAccount = (map.get("playerAccount") != null ? map.get("playerAccount").toString() : "");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String strIsToProhibitSpeak =
                ((map.get("isToProhibitSpeak") != null && map.get("isToProhibitSpeak") != "") ? map.get("isToProhibitSpeak").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");

        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);
        int isToProhibitSpeak = Integer.parseInt(strIsToProhibitSpeak);
        Player player = new Player();
        player.setPlatformId(platformId);
        player.setServerId(serverId);
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setIsProhibitSpeak(isToProhibitSpeak);

        Result re;
        Map<String, Object> JsonMap = playerLogDaoImpl.getPlayerProhibitSpeakLog(player, isPage, pageNo, pageSize, strPlatform, gameId);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "日志列表获取失败", "");
        } else {
            re = new Result(200, "日志列表获取成功", JsonMap);
        }

        return re;
    }


    public Result getPlayerBan(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String playerName = (map.get("playerName") != null ? map.get("playerName").toString() : "");
        String playerAccount = (map.get("playerAccount") != null ? map.get("playerAccount").toString() : "");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String strIsToBan = ((map.get("isToBan") != null && map.get("isToBan") != "") ? map.get("isToBan").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");

        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int platformId = Integer.parseInt(strPlatformId);
        int serverId = Integer.parseInt(strServerId);
        int isToBan = Integer.parseInt(strIsToBan);
        Player player = new Player();
        player.setPlatformId(platformId);
        player.setServerId(serverId);
        player.setPlayerName(playerName);
        player.setPlayerAccount(playerAccount);
        player.setIsBan(isToBan);

        Result re;
        Map<String, Object> JsonMap = playerLogDaoImpl.getPlayerBan(player, isPage, pageNo, pageSize, strPlatform, gameId);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "日志列表获取失败", "");
        } else {
            re = new Result(200, "日志列表获取成功", JsonMap);
        }

        return re;
    }

    public Result getRoleLoginLog(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");

        String iUin = (map.get("iUin") != null ? map.get("iUin").toString() : "");
        String iRoleId = (map.get("iRoleId") != null ? map.get("iRoleId").toString() : "");
        String vRoleName = (map.get("vRoleName") != null ? map.get("vRoleName").toString() : "");

        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int total = 0;
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> dblist = utilsDaoImpl.getDataSourceForPlatformId(Integer.parseInt(strPlatformId), gameId);
        if (dblist.size() == 0) {
            return new Result(400, "数据源获取失败", "");
        }
        Result re;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = dblist.get(0).get("dataSource_url").toString();
        String USER = dblist.get(0).get("dataSource_name").toString();
        String PASS = dblist.get(0).get("dataSource_password").toString();
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM rolelogin where DATEDIFF(now(),dtEventTime) < 20 ";
            if (!Objects.equals(strServerId, "0")) {
                sql += " and iWorldId IN ('" + strServerId + "') ";
            }
            if (!Objects.equals(iUin, "")) {
                sql += " and iUin ='" + iUin + "' ";
            }
            if (!Objects.equals(iRoleId, "")) {
                sql += " and iRoleId ='" + iRoleId + "' ";
            }
            if (!Objects.equals(vRoleName, "")) {
                sql += " and vRoleName ='" + vRoleName + "' ";
            }
            sql += " order by dtEventTime DESC ";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                total++;
            }
            if (!Objects.equals(isPage, "")) {
                sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
            }
            rs = stmt.executeQuery(sql);
            md = rs.getMetaData(); //获得结果集结构信息,元数据
            columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        re = new Result(200, "日志列表获取成功", JsonMap);
        return re;
    }

    public Result getCreateRoleLog(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");

        String iUin = (map.get("iUin") != null ? map.get("iUin").toString() : "");
        String iRoleId = (map.get("iRoleId") != null ? map.get("iRoleId").toString() : "");
        String vRoleName = (map.get("vRoleName") != null ? map.get("vRoleName").toString() : "");

        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int total = 0;
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> dblist = utilsDaoImpl.getDataSourceForPlatformId(Integer.parseInt(strPlatformId), gameId);

        if (dblist.size() == 0) {
            return new Result(400, "数据源获取失败", "");
        }
        Result re;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = dblist.get(0).get("dataSource_url").toString();
        String USER = dblist.get(0).get("dataSource_name").toString();
        String PASS = dblist.get(0).get("dataSource_password").toString();
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM createrole where DATEDIFF(now(),dtEventTime) < 20 ";
            if (!Objects.equals(strServerId, "0")) {
                sql += " and iWorldId IN ('" + strServerId + "') ";
            }
            if (!Objects.equals(iUin, "")) {
                sql += " and iUin ='" + iUin + "' ";
            }
            if (!Objects.equals(iRoleId, "")) {
                sql += " and iRoleId ='" + iRoleId + "' ";
            }
            if (!Objects.equals(vRoleName, "")) {
                sql += " and vRoleName ='" + vRoleName + "' ";
            }
            sql += " order by dtEventTime DESC ";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                total++;
            }
            if (!Objects.equals(isPage, "")) {
                sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
            }
            rs = stmt.executeQuery(sql);
            md = rs.getMetaData(); //获得结果集结构信息,元数据
            columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {

                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        re = new Result(200, "日志列表获取成功", JsonMap);
        return re;
    }

    public Result getMoneyFlowLog(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String iUin = (map.get("iUin") != null ? map.get("iUin").toString() : "");
        String iRoleId = (map.get("iRoleId") != null ? map.get("iRoleId").toString() : "");
        String vRoleName = (map.get("vRoleName") != null ? map.get("vRoleName").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int total = 0;
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> dblist = utilsDaoImpl.getDataSourceForPlatformId(Integer.parseInt(strPlatformId), gameId);

        if (dblist.size() == 0) {
            return new Result(400, "数据源获取失败", "");
        }
        Result re;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = dblist.get(0).get("dataSource_url").toString();
        String USER = dblist.get(0).get("dataSource_name").toString();
        String PASS = dblist.get(0).get("dataSource_password").toString();
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM moneyflow where DATEDIFF(now(),dtEventTime) < 20 ";
            if (!Objects.equals(strServerId, "0")) {
                sql += " and iWorldId IN ('" + strServerId + "') ";
            }
            if (!Objects.equals(iUin, "")) {
                sql += " and iUin ='" + iUin + "' ";
            }
            if (!Objects.equals(iRoleId, "")) {
                sql += " and iRoleId ='" + iRoleId + "' ";
            }
            if (!Objects.equals(vRoleName, "")) {
                sql += " and vRoleName ='" + vRoleName + "' ";
            }
            sql += " order by dtEventTime DESC ";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                total++;
            }
            if (!Objects.equals(isPage, "")) {
                sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
            }
            rs = stmt.executeQuery(sql);
            md = rs.getMetaData(); //获得结果集结构信息,元数据
            columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {

                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        re = new Result(200, "日志列表获取成功", JsonMap);
        return re;

    }


    public Result getGoodFlowLog(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String iUin = (map.get("iUin") != null ? map.get("iUin").toString() : "");
        String iRoleId = (map.get("iRoleId") != null ? map.get("iRoleId").toString() : "");
        String vRoleName = (map.get("vRoleName") != null ? map.get("vRoleName").toString() : "");
        String iGoodsId = (map.get("iGoodsId") != null ? map.get("iGoodsId").toString() : "");
        String vGoodsName = (map.get("vGoodsName") != null ? map.get("vGoodsName").toString() : "");
        String vOperate = (map.get("vOperate") != null ? map.get("vOperate").toString() : "");

        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int total = 0;
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List<Map<String, Object>> dblist = utilsDaoImpl.getDataSourceForPlatformId(Integer.parseInt(strPlatformId), gameId);
        if (dblist.size() == 0) {
            logger.debug("数据源获取失败");
            return new Result(400, "数据源获取失败", "");
        }
        logger.debug("数据源:" + dblist);
        Result re;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = dblist.get(0).get("dataSource_url").toString();
        String USER = dblist.get(0).get("dataSource_name").toString();
        String PASS = dblist.get(0).get("dataSource_password").toString();
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM goodsflow where DATEDIFF(now(),dtEventTime) < 20";

            if (!Objects.equals(strServerId, "0")) {
                sql += " and iWorldId IN ('" + strServerId + "') ";
            }
            if (!Objects.equals(iUin, "")) {
                sql += " and iUin ='" + iUin + "' ";
            }
            if (!Objects.equals(iRoleId, "")) {
                sql += " and iRoleId ='" + iRoleId + "' ";
            }
            if (!Objects.equals(vRoleName, "")) {
                sql += " and vRoleName ='" + vRoleName + "' ";
            }
            if (!Objects.equals(iGoodsId, "")) {
                sql += " and iGoodsId ='" + iGoodsId + "' ";
            }
            if (!Objects.equals(vGoodsName, "")) {
                sql += " and vGoodsName ='" + vGoodsName + "' ";
            }
            if (!Objects.equals(vOperate, "")) {
                sql += " and vOperate ='" + vOperate + "' ";
            }
            sql += " order by dtEventTime DESC ";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                total++;
            }
            if (!Objects.equals(isPage, "")) {
                sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
            }
            rs = stmt.executeQuery(sql);
            md = rs.getMetaData(); //获得结果集结构信息,元数据
            columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {

                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        re = new Result(200, "日志列表获取成功", JsonMap);
        return re;
    }

    public Result getReChargeLog(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String iUin = (map.get("iUin") != null ? map.get("iUin").toString() : "");
        String iRoleId = (map.get("iRoleId") != null ? map.get("iRoleId").toString() : "");
        String vRoleName = (map.get("vRoleName") != null ? map.get("vRoleName").toString() : "");
        String vSN = (map.get("vSN") != null ? map.get("vSN").toString() : "");
        String minIPayDelta = (map.get("minIPayDelta") != null ? map.get("minIPayDelta").toString() : "");
        String maxIPayDelta = (map.get("maxIPayDelta") != null ? map.get("maxIPayDelta").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int total = 0;
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List<Map<String, Object>> dblist = utilsDaoImpl.getDataSourceForPlatformId(Integer.parseInt(strPlatformId), gameId);

        if (dblist.size() == 0) {
            return new Result(400, "数据源获取失败", "");
        }

        Result re;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = dblist.get(0).get("dataSource_url").toString();
        String USER = dblist.get(0).get("dataSource_name").toString();
        String PASS = dblist.get(0).get("dataSource_password").toString();
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM recharge where DATEDIFF(now(),dtEventTime) < 20 ";

            if (!Objects.equals(strServerId, "0")) {
                sql += " and iWorldId IN ('" + strServerId + "') ";
            }
            if (!Objects.equals(iUin, "")) {
                sql += " and iUin ='" + iUin + "' ";
            }
            if (!Objects.equals(iRoleId, "")) {
                sql += " and iRoleId ='" + iRoleId + "' ";
            }
            if (!Objects.equals(vRoleName, "")) {
                sql += " and vRoleName ='" + vRoleName + "' ";
            }
            if (!Objects.equals(vSN, "")) {
                sql += " and vSN ='" + vSN + "' ";
            }
            if (!Objects.equals(minIPayDelta, "")) {
                sql += " and iPayDelta >'" + minIPayDelta + "' ";
            }
            if (!Objects.equals(maxIPayDelta, "")) {
                sql += " and iPayDelta <'" + maxIPayDelta + "' ";
            }
            sql += " order by dtEventTime DESC ";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                total++;
            }
            if (!Objects.equals(isPage, "")) {
                sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
            }
            rs = stmt.executeQuery(sql);
            md = rs.getMetaData(); //获得结果集结构信息,元数据
            columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {

                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        re = new Result(200, "日志列表获取成功", JsonMap);
        return re;
    }

    public Result getShopLog(Map map) {
        String gameId = ((map.get("gameId") != null && map.get("gameId") != "") ? map.get("gameId").toString() : "0");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String iUin = (map.get("iUin") != null ? map.get("iUin").toString() : "");
        String iRoleId = (map.get("iRoleId") != null ? map.get("iRoleId").toString() : "");
        String vRoleName = (map.get("vRoleName") != null ? map.get("vRoleName").toString() : "");
        String vSN = (map.get("vSN") != null ? map.get("vSN").toString() : "");
        String minIPayDelta = (map.get("minIPayDelta") != null ? map.get("minIPayDelta").toString() : "");
        String maxIPayDelta = (map.get("maxIPayDelta") != null ? map.get("maxIPayDelta").toString() : "");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        int total = 0;
        int pageNo = 1;
        int pageSize = 5;
        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List<Map<String, Object>> dblist = utilsDaoImpl.getDataSourceForPlatformId(Integer.parseInt(strPlatformId), gameId);

        if (dblist.size() == 0) {
            return new Result(400, "数据源获取失败", "");
        }

        Result re;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = dblist.get(0).get("dataSource_url").toString();
        String USER = dblist.get(0).get("dataSource_name").toString();
        String PASS = dblist.get(0).get("dataSource_password").toString();
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM shop where DATEDIFF(now(),dtEventTime) < 20 ";

            if (!Objects.equals(strServerId, "0")) {
                sql += " and iWorldId IN ('" + strServerId + "') ";
            }
            if (!Objects.equals(iUin, "")) {
                sql += " and iUin ='" + iUin + "' ";
            }
            if (!Objects.equals(iRoleId, "")) {
                sql += " and iRoleId ='" + iRoleId + "' ";
            }
            if (!Objects.equals(vRoleName, "")) {
                sql += " and vRoleName ='" + vRoleName + "' ";
            }
            if (!Objects.equals(vSN, "")) {
                sql += " and vSN ='" + vSN + "' ";
            }
            if (!Objects.equals(minIPayDelta, "")) {
                sql += " and iPayDelta >'" + minIPayDelta + "' ";
            }
            if (!Objects.equals(maxIPayDelta, "")) {
                sql += " and iPayDelta <'" + maxIPayDelta + "' ";
            }
            sql += " order by dtEventTime DESC ";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                total++;
            }
            if (!Objects.equals(isPage, "")) {
                sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
            }
            rs = stmt.executeQuery(sql);
            md = rs.getMetaData(); //获得结果集结构信息,元数据
            columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {

                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        } catch (Exception e) {
            e.printStackTrace();
            re = new Result(400, "日志列表获取失败", "");
            return re;
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        re = new Result(200, "日志列表获取成功", JsonMap);
        return re;
    }


    public Result getLogXml(Map map) {
        DomAnalysis domAnalysis = new DomAnalysis();
        String jsonString = domAnalysis.Analysis("tlog.xml");
        Result re = new Result(200, "日志列表获取成功", jsonString);
        return re;
    }


}
