package com.cdk.util.file;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

/**
 * 生成TXT文件
 * @author lizhiyong
 * @version $Id: TxtUtils.java, v 0.1
2014年9月11日 上午8:40:02 Exp $
 */
public class TxtUtils {


    @SuppressWarnings("rawtypes")
    public static File writeTxtFile(String channelCode, List contentList, LinkedHashMap map, String filePath, String readStr, String fileName)
            throws IOException {

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
            System.out.println("文件夹已创建");
        }
        //定义文件名格式并创建
        File txtFile = File.createTempFile(fileName, ".txt", new File(filePath));
        //先读取原有文件内容，然后进行写入操作
        FileWriter writer = null;

        if (Objects.isNull(map)) {
            //写入头信息
            String filein = "";
            for (Iterator tileIterator = map.entrySet().iterator(); tileIterator.hasNext(); ) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) tileIterator.next();
                filein += (String) propertyEntry.getValue() == null ? "" : (String) propertyEntry.getValue();
                if (tileIterator.hasNext()) {
                    filein = filein + "                 ";
                }
            }
            try {
                writer = new FileWriter(txtFile, true);
                writer.write(filein);
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        //写入详细信息
        int num = 1;
        for (Iterator iterator = contentList.iterator(); iterator.hasNext(); ) {
            LinkedHashMap txtMap = (LinkedHashMap) iterator.next();
            String filein1 = "";
            for (Iterator propertyIterator = txtMap.entrySet().iterator(); propertyIterator.hasNext(); ) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                filein1 += (String) propertyEntry.getValue() == null ? "" : (String) propertyEntry.getValue();
                if (propertyIterator.hasNext()) {
                    filein1 = filein1 + readStr;
                }
            }
            try {
                System.out.println("数据filein1：" + filein1);
                filein1 = String.valueOf(num) + filein1;
                writer = new FileWriter(txtFile, true);
                writer.write(filein1);
                writer.write("\r\n");
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return txtFile;
    }

    /**
     * 下载文件
     * @param response
     * @param filePath  文件路径
     * @param file   文件
     * @throws IOException
     */
    public void downLoadFile(HttpServletResponse response, String filePath, File file) throws IOException {
        String fileName = file.getName();
        //下载文件
        FileManageUtils.exportFile(response, filePath + fileName, fileName);
        //删除单个文件
        //FileManageUtils.deleteFile(filePath, fileName);
    }

}
