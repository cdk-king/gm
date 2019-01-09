package com.cdk.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class UploadDaoImpl {
    private static Logger logger = LoggerFactory.getLogger(UploadDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int fileUpload(MultipartFile file, String newName, String fileSize, String fileDescribe, String addUser, String platformId,
            String gameId) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String fileName = file.getOriginalFilename().split("\\.")[0];
        String fileType = file.getOriginalFilename().split("\\.")[1];
        String sql =
                "insert into t_file (gameId,platformId,fileOldName,fileName,fileSize,addUser,addDatetime,downloadTime,fileDescribe,isDelete,fileType) values('" +
                        gameId + "','" + platformId + "','" + fileName + "','" + newName + "','" + fileSize + "','" + addUser + "','" + addDatetime +
                        "','0','" + fileDescribe + "','0','" + fileType + "')";
        logger.debug("sql：" + sql);
        int temp = jdbcTemplate.update(sql);

        return temp;
    }


    public Map<String, Object> getFileList(String fileOldName, String fileName, String gameId, String platformId, String strPlatform, int pageNo,
            int pageSize) {
        String sql =
                "select a.*,b.platform from t_file as a join t_gameplatform as b on a.platformId = b.platformId join t_game as c on c.id = b.gameId and c.isDelete!=1  where c.id = '" +
                        gameId + "' and a.platformId in (" + strPlatform + ") and a.gameId = '" + gameId + "' and  a.isDelete!=1  and b.isDelete!=1 ";
        if (!Objects.equals(platformId, "0")) {
            sql += " and a.platformId ='" + platformId + "' ";
        }
        if (!Objects.equals(fileOldName, "")) {
            sql += " and a.fileOldName like '%" + fileOldName + "%' ";
        }
        if (!Objects.equals(fileName, "")) {
            sql += " and a.fileName like '%" + fileName + "%' ";
        }
        sql += " order by id desc ";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        int total = list.size();
        sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;

        logger.debug("sql：" + sql);
        list = jdbcTemplate.queryForList(sql);
        Map<String, Object> JsonMap = new HashMap();
        if (list.size() != 0) {
            JsonMap.put("list", list);
        } else {
            JsonMap.put("list", "");
        }
        JsonMap.put("total", total);

        return JsonMap;

    }

    public int deleteFile(String id) {

        String sql = "update t_file set isDelete = 1 where id='" + id + "' ";
        int temp = jdbcTemplate.update(sql);

        return temp;
    }

    public int addDownloadTime(String id) {
        String sql = "select * from t_file where id = '" + id + "' and isDelete!=1 ";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        int time = Integer.parseInt(list.get(0).get("downloadTime").toString());
        time++;
        sql = "update t_file set downloadTime = '" + time + "' where id='" + id + "' ";
        int temp = jdbcTemplate.update(sql);

        return temp;
    }

    public int[] deleteAllFile(String[] idList) {
        String strSql = "";
        String sql[] = new String[idList.length];
        int[] temp = new int[idList.length];
        for (int i = 0; i < idList.length; i++) {
            sql[i] = "UPDATE  t_file  set isDelete='1' where id = '" + idList[i] + "';";
            strSql += sql;
        }
        logger.debug("sql：" + strSql);
        temp = jdbcTemplate.batchUpdate(sql);
        return temp;
    }

}
