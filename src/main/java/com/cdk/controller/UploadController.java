package com.cdk.controller;

import com.cdk.result.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    //    @CrossOrigin
    //    @GetMapping("/upload")
    //    public String upload() {
    //        return "upload";
    //    }

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
            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";
    }

    /**
     * 实现文件上传
     * */
    @CrossOrigin
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public Result fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Result(400, "文件为空", "");
            //return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "f:/file";
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return new Result(200, "文件上传成功", "");
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
    }

}
