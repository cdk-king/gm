package com.cdk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@RestController注解相当于@ResponseBody ＋ @Controller
//大多数情况我们只需要返回json格式的字符串而不需要返回json对象，所以加上@Responsebody就行了
@RestController
public class DownloadController {
    private static Logger logger = LoggerFactory.getLogger(DownloadController.class);

    @CrossOrigin
    @RequestMapping("/api/file/CDKFileDownload")
    public String CDKFileDownload(HttpServletRequest request, HttpServletResponse response, @RequestBody Map map) {
        Map<String, String[]> requestParams = request.getParameterMap();
        logger.info("requestParams:" + requestParams.toString());
        String fileName = request.getParameter("fileName");
        String filePath = request.getParameter("filePath");

        fileName = map.get("fileName").toString();
        filePath = map.get("filePath").toString();

        logger.info("fileName:" + fileName);
        logger.info("filePath:" + filePath);
        File file = new File(filePath + "/" + fileName);
        if (file.exists()) { //判断文件父目录是否存在
            logger.info("file.exists()");
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                //字节流
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                //从文件中按字节读取内容，到文件尾部时read方法将返回-1
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info("----------file download" + fileName);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    @CrossOrigin
    @RequestMapping("/api/file/fileDownload")
    public String downLoad(HttpServletRequest request, HttpServletResponse response, @RequestBody Map map) {
        Map<String, String[]> requestParams = request.getParameterMap();
        logger.info("requestParams:" + requestParams.toString());
        String fileName = request.getParameter("fileName");
        String filePath = request.getParameter("filePath");

        fileName = map.get("fileName").toString();
        filePath = map.get("filePath").toString();

        logger.info("fileName:" + fileName);
        logger.info("filePath:" + filePath);
        File file = new File(filePath + "/" + fileName);
        if (file.exists()) { //判断文件父目录是否存在
            logger.info("file.exists()");
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                //字节流
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                //从文件中按字节读取内容，到文件尾部时read方法将返回-1
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info("----------file download" + fileName);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 测试接口
     * @param response
     * @return
     */
    @CrossOrigin
    @RequestMapping("download")
    public String downLoad(HttpServletResponse response) {
        String fileName = "img.jpg";
        String filePath = "F:/file";
        File file = new File(filePath + "/" + fileName);
        if (file.exists()) { //判断文件父目录是否存在
            //response.setContentType("application/json");
            //response.setContentType("application/force-download");
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + fileName);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


}
