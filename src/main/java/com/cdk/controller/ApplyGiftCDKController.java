package com.cdk.controller;

import com.cdk.dao.impl.CouponDaoImpl;
import com.cdk.result.Result;
import com.cdk.service.impl.ApplyGiftCDK_ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Map;

@RestController
public class ApplyGiftCDKController {
    private static Logger logger = LoggerFactory.getLogger(ApplyGiftCDKController.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ApplyGiftCDK_ServiceImpl applyGiftCDK_ServiceImpl;
    @Autowired
    public CouponDaoImpl couponDaoImpl;

    @CrossOrigin
    @RequestMapping("/generateCDK")
    public Result generateCDK(@RequestBody Map map) {
        Result re = applyGiftCDK_ServiceImpl.generateCDK(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/analyseCDK")
    public Result analyseCDK(@RequestBody Map map) {
        Result re = applyGiftCDK_ServiceImpl.analyseCDK(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getGiftListForPlatformId")
    public Result getGiftListForPlatformId(@RequestBody Map map) {
        Result re = applyGiftCDK_ServiceImpl.getGiftListForPlatformId(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/getNewGiftListForPlatformId")
    public Result getNewGiftListForPlatformId(@RequestBody Map map) {
        Result re = applyGiftCDK_ServiceImpl.getNewGiftListForPlatformId(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/cdk/getCoupon")
    public Result getCoupon(@RequestBody Map map) {
        Result re = applyGiftCDK_ServiceImpl.getCoupon(map);
        return re;
    }

    @CrossOrigin
    @RequestMapping("/api/cdk/deleteCDK")
    public Result deleteCDK(@RequestBody Map map) {
        String fileName = map.get("fileName").toString();
        String id = map.get("id").toString();
        String filePath = map.get("filePath").toString();
        deleteCDKFile(filePath, fileName);
        int temp = couponDaoImpl.deleteCDK(id);
        return null;
    }

    @CrossOrigin
    @RequestMapping("/api/cdk/deleteAllCDK")
    public Result deleteAllCDK(@RequestBody Map map) {
        String fileName = map.get("fileName").toString();
        String id = map.get("id").toString();
        String filePath = map.get("filePath").toString();
        String[] idList = id.split(",");
        String[] fileNames = fileName.split(",");
        deleteALLCDK(filePath, fileNames);
        int[] temp = couponDaoImpl.deleteAllCDK(idList);
        return null;
    }

    /**
     * 删除单个文件
     * @param filePath
     *         文件目录路径
     * @param fileName
     *         文件名称
     */
    public static void deleteCDKFile(String filePath, String fileName) {
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
    public static void deleteALLCDK(String filePath, String[] fileNames) {
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
