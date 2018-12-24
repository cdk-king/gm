package com.cdk.service.impl;


import com.cdk.dao.impl.PlayerLogDaoImpl;
import com.cdk.entity.Player;
import com.cdk.result.Result;

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
import java.util.logging.Logger;

@Service
public class PlayerLogServiceImpl {
    private static Logger logger = Logger.getLogger(String.valueOf(PlayerLogServiceImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    public PlayerLogDaoImpl playerLogDaoImpl;

    public Result getPlayerProhibitSpeakLog(Map map) {
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
        logger.info("strPlatform：" + strPlatform);

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
        Map<String, Object> JsonMap = playerLogDaoImpl.getPlayerProhibitSpeakLog(player, isPage, pageNo, pageSize, strPlatform);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "日志列表获取失败", "");
        } else {
            re = new Result(200, "日志列表获取成功", JsonMap);
        }

        return re;
    }


    public Result getPlayerBan(Map map) {
        String playerName = (map.get("playerName") != null ? map.get("playerName").toString() : "");
        String playerAccount = (map.get("playerAccount") != null ? map.get("playerAccount").toString() : "");
        String strPlatformId = ((map.get("platformId") != null && map.get("platformId") != "") ? map.get("platformId").toString() : "0");
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");
        String strIsToBan = ((map.get("isToBan") != null && map.get("isToBan") != "") ? map.get("isToBan").toString() : "0");
        String isPage = (map.get("isPage") != null ? map.get("isPage").toString() : "");
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        String strPlatform = (map.get("strPlatform") != null ? map.get("strPlatform").toString() : "");
        logger.info("strPlatform：" + strPlatform);

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
        Map<String, Object> JsonMap = playerLogDaoImpl.getPlayerBan(player, isPage, pageNo, pageSize, strPlatform);
        if (Objects.equals(JsonMap.get("list"), 0)) {
            re = new Result(400, "日志列表获取失败", "");
        } else {
            re = new Result(200, "日志列表获取成功", JsonMap);
        }

        return re;
    }

    public Result getRoleLoginLog(Map map) {
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");

        String iUin = (map.get("iUin") != null ? map.get("iUin").toString() : "");
        String iRoleId = (map.get("iRoleId") != null ? map.get("iRoleId").toString() : "");
        String vRoleName = (map.get("vRoleName") != null ? map.get("vRoleName").toString() : "");
        String vClientIp = (map.get("vClientIp") != null ? map.get("vClientIp").toString() : "");
        String iLoginWay = (map.get("iLoginWay") != null ? map.get("iLoginWay").toString() : "");

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
        Result re;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://192.168.1.16:3306/dbdiablomuzhilog?serverTimezone=Asia/Shanghai";
        String USER = "root";
        String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            logger.info("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM rolelogin where 1=1 ";
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
            if (!Objects.equals(vClientIp, "")) {
                sql += " and vClientIp ='" + vClientIp + "' ";
            }
            if (!Objects.equals(iLoginWay, "")) {
                sql += " and iLoginWay ='" + iLoginWay + "' ";
            }

            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                total++;
            }
            logger.info(total + "");
            if (!Objects.equals(isPage, "")) {
                sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
            }
            logger.info(sql);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        re = new Result(200, "日志列表获取成功", JsonMap);
        return re;
    }

    public Result getCreateRoleLog(Map map) {
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");

        String iUin = (map.get("iUin") != null ? map.get("iUin").toString() : "");
        String iRoleId = (map.get("iRoleId") != null ? map.get("iRoleId").toString() : "");
        String vRoleName = (map.get("vRoleName") != null ? map.get("vRoleName").toString() : "");
        String vClientIp = (map.get("vClientIp") != null ? map.get("vClientIp").toString() : "");
        String iLoginWay = (map.get("iLoginWay") != null ? map.get("iLoginWay").toString() : "");

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
        Result re;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://192.168.1.16:3306/dbdiablomuzhilog?serverTimezone=Asia/Shanghai";
        String USER = "root";
        String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            logger.info("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM createrole where 1=1 ";
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
            if (!Objects.equals(vClientIp, "")) {
                sql += " and vClientIp ='" + vClientIp + "' ";
            }
            if (!Objects.equals(iLoginWay, "")) {
                sql += " and iLoginWay ='" + iLoginWay + "' ";
            }

            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                total++;
            }
            logger.info(total + "");
            if (!Objects.equals(isPage, "")) {
                sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
            }
            logger.info(sql);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        re = new Result(200, "日志列表获取成功", JsonMap);
        return re;
    }

    public Result getMoneyFlowLog(Map map) {
        String strServerId = ((map.get("serverId") != null && map.get("serverId") != "") ? map.get("serverId").toString() : "0");

        String iUin = (map.get("iUin") != null ? map.get("iUin").toString() : "");
        String iRoleId = (map.get("iRoleId") != null ? map.get("iRoleId").toString() : "");
        String vRoleName = (map.get("vRoleName") != null ? map.get("vRoleName").toString() : "");
        String iMoneyType = (map.get("iMoneyType") != null ? map.get("iMoneyType").toString() : "");
        String iAction = (map.get("iAction") != null ? map.get("iAction").toString() : "");

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
        Result re;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://192.168.1.16:3306/dbdiablomuzhilog?serverTimezone=Asia/Shanghai";
        String USER = "root";
        String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            logger.info("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM moneyflow where 1=1 ";
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
            if (!Objects.equals(iMoneyType, "")) {
                sql += " and iMoneyType ='" + iMoneyType + "' ";
            }
            if (!Objects.equals(iAction, "")) {
                sql += " and iAction ='" + iAction + "' ";
            }

            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                total++;
            }
            logger.info(total + "");
            if (!Objects.equals(isPage, "")) {
                sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
            }
            logger.info(sql);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        re = new Result(200, "日志列表获取成功", JsonMap);
        return re;

    }


    public Result getGoodFlowLog(Map map) {
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
        Result re;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://192.168.1.16:3306/dbdiablomuzhilog?serverTimezone=Asia/Shanghai";
        String USER = "root";
        String PASS = "123456";
        Connection conn = null;
        Statement stmt = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            logger.info("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            logger.info(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT *  FROM goodsflow where 1=1 ";
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

            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while (rs.next()) {
                total++;
            }
            logger.info(total + "");
            if (!Objects.equals(isPage, "")) {
                sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
            }
            logger.info(sql);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> JsonMap = new HashMap();
        JsonMap.put("list", list);
        JsonMap.put("total", total);
        re = new Result(200, "日志列表获取成功", JsonMap);
        return re;
    }
}
