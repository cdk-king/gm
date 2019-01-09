package com.cdk.controller;

import com.cdk.dao.impl.UploadDaoImpl;
import com.cdk.result.Result;
import com.cdk.service.impl.UploadServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
public class UploadController {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private UploadServiceImpl uploadServiceImpl;

    @Autowired
    public UploadDaoImpl uploadDaoImpl;

    @CrossOrigin
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);//保存文件
            logger.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return "上传失败！";
    }

    /**
     * 实现文件上传
     * */
    @CrossOrigin
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Result fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName,
            @RequestParam("fileSize") String fileSize, @RequestParam("fileDescribe") String fileDescribe, @RequestParam("addUser") String addUser,
            @RequestParam("platformId") String platformId, @RequestParam("gameId") String gameId) {

        if (file.isEmpty()) {
            return new Result(400, "文件为空", "");
        }
        Result re = uploadServiceImpl.fileUpload(file, fileName, fileSize, fileDescribe, addUser, platformId, gameId);
        return re;
    }


    @CrossOrigin
    @RequestMapping("/api/file/getFileList")
    @ResponseBody
    public Result getFileList(@RequestBody Map map) {
        Result re = uploadServiceImpl.getFileList(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/file/addDownloadTime")
    @ResponseBody
    public Result addDownloadTime(@RequestBody Map map) {
        Result re = uploadServiceImpl.addDownloadTime(map);
        return re;
    }

    public void getFile(String path, int deep) {
        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();

        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile())//如果是文件
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                {
                    System.out.print(" ");
                }
                // 只输出文件名字
                logger.info(array[i].getName());
                // 输出当前文件的完整路径
                //logger.info("#####" + array[i]);
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下
                logger.info(array[i].getPath());
            } else if (array[i].isDirectory())//如果是文件夹
            {
                for (int j = 0; j < deep; j++)//输出前置空格
                {
                    System.out.print(" ");
                }

                logger.info(array[i].getName());
                //logger.info(array[i].getPath());
                //文件夹需要调用递归 ，深度+1
                //getFile(array[i].getPath(), deep + 1);
            }
        }
    }

    @CrossOrigin
    @RequestMapping("/api/file/deleteFile")
    public Result deleteFile(@RequestBody Map map) {
        String fileName = map.get("fileName").toString();
        String id = map.get("id").toString();
        String filePath = map.get("filePath").toString();
        deleteFile(filePath, fileName);
        int temp = uploadDaoImpl.deleteFile(id);

        return null;
    }

    @CrossOrigin
    @RequestMapping("/api/file/deleteAllFile")
    public Result deleteAllFile(@RequestBody Map map) {
        String fileName = map.get("fileName").toString();
        String id = map.get("id").toString();
        String filePath = map.get("filePath").toString();
        String[] idList = id.split(",");
        String[] fileNames = fileName.split(",");
        deleteALLFile(filePath, fileNames);
        int[] temp = uploadDaoImpl.deleteAllFile(idList);

        return null;
    }

    /**
     * 删除单个文件
     * @param filePath
     *         文件目录路径
     * @param fileName
     *         文件名称
     */
    public static void deleteFile(String filePath, String fileName) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    if (files[i].getName().equals(fileName)) {
                        files[i].delete();
                        return;
                    }
                }
            }
        }
    }

    /**
     * 删除多个文件
     * @param filePath
     *         文件目录路径
     * @param fileNames
     *         文件名称
     */
    public static void deleteALLFile(String filePath, String[] fileNames) {
        File file = new File(filePath);
        int len = fileNames.length;
        int isDel = 0;
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    for (int j = 0; j < len; j++) {
                        if (files[i].getName().equals(fileNames[j])) {
                            files[i].delete();
                            isDel++;
                            if (isDel >= len) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
