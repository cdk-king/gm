package com.cdk.util;

import com.alibaba.fastjson.JSON;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DomAnalysis {


    public void Analysis(String fileName) {
        List<Map<String, Object>> list = new ArrayList<>();

        //创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //创建一个DocumentBuilder的对象
        try {
            //创建DocumentBuilder对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            //通过DocumentBuilder对象的parser方法加载tables.xml文件到当前项目下
            Document document = db.parse(fileName);
            //获取所有table节点的集合
            NodeList tableList = document.getElementsByTagName("struct");
            for (int i = 0; i < tableList.getLength(); i++) {
                Map<String, Object> map = new HashMap<>();
                Node table = tableList.item(i);
                NamedNodeMap attrs = table.getAttributes();
                for (int j = 0; j < attrs.getLength(); j++) {
                    Node attr = attrs.item(j);
                    map.put(attr.getNodeName(), attr.getNodeValue());
                }
                NodeList childNodes = table.getChildNodes();
                List<Map<String, Object>> list2 = new ArrayList<>();
                for (int k = 0; k < childNodes.getLength(); k++) {
                    if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        NamedNodeMap attrs2 = childNodes.item(k).getAttributes();
                        Map<String, Object> map2 = new HashMap<>();
                        for (int j = 0; j < attrs2.getLength(); j++) {
                            Node attr2 = attrs2.item(j);
                            map2.put(attr2.getNodeName(), attr2.getNodeValue());
                        }
                        list2.add(map2);
                    }
                }
                map.put("entry", list2);
                list.add(map);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = JSON.toJSONString(list);
    }
}

