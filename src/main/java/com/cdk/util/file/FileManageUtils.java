package com.cdk.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件管理
 * @author lizhiyong
 * @version $Id: FileManageUtils.java, v 0.1
2014年9月11日 上午9:37:47 Exp $
 */
public class FileManageUtils {
    private static Logger logger = LoggerFactory.getLogger(FileManageUtils.class);

    /**
     * 下载文件
     * @param response
     * @param csvFilePath
     *              文件路径
     * @param fileName
     *              文件名称
     * @throws IOException
     */
    public static void exportFile(HttpServletResponse response, String csvFilePath, String fileName) throws IOException {
        response.setContentType("application/csv;charset=GBK");
        response.setHeader("Content-Disposition", "attachment;  filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));

        InputStream in = null;
        try {
            in = new FileInputStream(csvFilePath);
            int len = 0;
            byte[] buffer = new byte[1024];
            response.setCharacterEncoding("GBK");
            OutputStream out = response.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            logger.debug(e + "");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 删除该目录filePath下的所有文件
     * @param filePath
     *            文件目录路径
     */
    public static void deleteFiles(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    files[i].delete();
                }
            }
        }
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
}
