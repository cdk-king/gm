package com.cdk.service.impl;


import com.cdk.dao.impl.UploadDaoImpl;
import com.cdk.result.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class UploadServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
    @Autowired
    public UploadDaoImpl uploadDaoImpl;

    public Result fileUpload(MultipartFile file, String newName, String fileSize, String fileDescribe, String addUser) {
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        logger.debug(fileName + "-->" + size);
        logger.debug(newName);
        File f1 = new File("file");
        if (!f1.exists()) {
            //生成所有目录
            f1.mkdirs();
            logger.debug("文件夹已创建");
        }
        if (!Objects.equals(newName, "")) {
            if (newName.indexOf("\\.") != -1) {
                fileName = newName;
                newName = fileName.split("\\.")[0];
            } else {

                fileName = newName + "." + fileName.split("\\.")[1];
                logger.debug(fileName);
            }
        } else {
            //会转义成反斜杠,反斜杠本身就是转义符,所有就成了“\.”
            newName = fileName.split("\\.")[0];
        }
        File dest = new File(f1.getAbsolutePath() + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
            logger.debug("文件夹已创建");
        }
        logger.debug("文件夹已创建");
        try {

            // file.transferTo(new File(path));
            //file.transferTo 方法调用时，判断如果是相对路径，则使用temp目录，为父目录
            file.transferTo(dest); //保存文件

            int temp = uploadDaoImpl.fileUpload(file, newName, fileSize, fileDescribe, addUser);

            if (temp > 0) {
                return new Result(200, "文件上传成功", "");
            } else {
                return new Result(400, "文件上传失败", "");
            }
            //return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Result(400, "文件上传失败", "");
            //return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Result(400, "文件上传失败", "");
            //return "false";
        }
        //Result re;
    }

    public Result getFileList(Map map) {
        Result re;
        String fileOldName = (map.get("fileOldName") != null ? map.get("fileOldName").toString() : "");
        String fileName = (map.get("fileName") != null ? map.get("fileName").toString() : "");

        //分页查询
        String StrPageNo = (map.get("pageNo") != null ? map.get("pageNo").toString() : "1");
        String StrPageSize = (map.get("pageSize") != null ? map.get("pageSize").toString() : "5");
        //设置缺省值
        int pageNo = 1;
        int pageSize = 5;

        try {
            pageNo = Integer.parseInt(StrPageNo);
            pageSize = Integer.parseInt(StrPageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Map<String, Object> JsonMap = uploadDaoImpl.getFileList(fileOldName, fileName, pageNo, pageSize);
        if (!Objects.equals(JsonMap.get("total"), 0)) {
            re = new Result(200, "文件列表获取成功", JsonMap);
        } else {
            re = new Result(200, "文件列表为空", "");
        }
        return re;
    }


}
