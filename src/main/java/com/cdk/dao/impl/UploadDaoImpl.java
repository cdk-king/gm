package com.cdk.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Repository
public class UploadDaoImpl {

    private static Logger logger = Logger.getLogger(String.valueOf(UploadDaoImpl.class));
    public static final String Divider = "############################";
    public static final String Split = "----------------";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int fileUpload(MultipartFile file, String newName, String fileSize, String fileDescribe, String addUser) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String addDatetime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String fileName = file.getOriginalFilename().split("\\.")[0];
        String fileType = file.getOriginalFilename().split("\\.")[1];

        String sql = "insert into t_file (fileOldName,fileName,fileSize,addUser,addDatetime,downloadTime,fileDescribe,isDelete,fileType) values('" +
                fileName + "','" + newName + "','" + fileSize + "','" + addUser + "','" + addDatetime + "','0','" + fileDescribe + "','0','" +
                fileType + "')";
        int temp = jdbcTemplate.update(sql);

        return temp;
    }

    public Map<String, Object> getFileList(int pageNo, int pageSize) {

        String sql = "select * from t_file where isDelete!=1 ";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        int total = list.size();
        sql += " limit " + (pageNo - 1) * pageSize + ", " + pageSize;
        logger.info("sql：" + sql);
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

}
